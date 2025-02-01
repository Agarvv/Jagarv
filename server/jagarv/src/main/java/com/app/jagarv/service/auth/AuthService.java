package com.app.jagarv.service.auth;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.app.jagarv.dto.user.RegisterUserDTO;
import com.app.jagarv.dto.user.LoginUserDTO;
import com.app.jagarv.exception.exceptions.users.EmailAlreadyExistsException;
import com.app.jagarv.exception.exceptions.users.UsernameAlreadyExistsException;
import com.app.jagarv.exception.exceptions.users.EmailNotFoundException;
import com.app.jagarv.exception.exceptions.users.InvalidCredentialsException;
import com.app.jagarv.exception.exceptions.users.InvalidResetTokenOrEmail;
import com.app.jagarv.exception.exceptions.users.UserSessionNotValidException;
import com.app.jagarv.outil.GenerateResetPasswordToken;
import com.app.jagarv.outil.SendMail;
import com.app.jagarv.outil.JwtOutil;
import com.app.jagarv.outil.CookieOutil;
import jakarta.servlet.http.Cookie;

import com.app.jagarv.dto.user.SendResetCodeDTO;
import com.app.jagarv.dto.user.ResetPasswordDTO;

import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.entity.user.ResetPasswordToken;
import com.app.jagarv.entity.user.User;
import com.app.jagarv.repository.cart.CartRepository;
import com.app.jagarv.repository.user.ResetPasswordTokenRepository;
import com.app.jagarv.repository.user.UserRepository;

import java.time.LocalDate;

// handles auth logic
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; 
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final SendMail sendMail;
    private final JwtOutil jwtOutil;
    private final CartRepository cartRepository;

    public AuthService(
         UserRepository userRepository,
         PasswordEncoder passwordEncoder, 
         AuthenticationManager authenticationManager,
         ResetPasswordTokenRepository resetPasswordTokenRepository,
         SendMail sendMail, JwtOutil jwtOutil,
         CartRepository cartRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
        this.sendMail = sendMail;
        this.jwtOutil = jwtOutil;
        this.cartRepository = cartRepository;
 }

    // Registers a user in the system
    public void registerUser(RegisterUserDTO newUser) {
        // Check if the username is unique
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new UsernameAlreadyExistsException("That Username Already Exists, Try With Another.");
        }
        
        // Check if the email is unique
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailAlreadyExistsException("That Email Already Exists, Try With Another.");
        }

        // Create a new User entity and encode the password for security
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword())); // pass encoded
        user.setJoinedAt(LocalDate.now().toString()); 
        
        // Save the user in the repository
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

    }

    // Handles user login
    public Cookie loginUser(LoginUserDTO loginUserDTO) {
        try {
            // if the user not exists, throw exception
            User dbUser = userRepository.findByEmail(loginUserDTO.getEmail()).orElseThrow(()
            -> new EmailNotFoundException("That Email Does Not Exist")
            ); 
            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginUserDTO.getEmail(), loginUserDTO.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = customUserDetails.getUser();
            
            // generate jwt token
            String jwtToken = jwtOutil.generateToken(user.getId(), user.getRole().name());
            // put jwt in a cookie
            Cookie jwtCookie = CookieOutil.generateJwtCookie(jwtToken);
            
            dbUser.setLastLogin(LocalDate.now().toString());
            
            userRepository.save(dbUser);
            
            
            // return cookie with jwt to controller and controller sets cookie in response
            return jwtCookie;

        } catch (BadCredentialsException e) {
            // if bad credentials, that means password is not valid, throw exception
            throw new InvalidCredentialsException("Password Does Not Match...");
        }
    }
    
    // sends reset password instructions to email
    public void sendResetCode(SendResetCodeDTO sendResetCodeDTO) {
        // if email not exists throw exception
        User user = userRepository.findByEmail(sendResetCodeDTO.getEmail()).orElseThrow(() -> 
            new EmailNotFoundException("Email not exists"));
        
        // generate new reset token
        String resetToken = GenerateResetPasswordToken.generate();
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        resetPasswordToken.setUserEmail(user.getEmail());
        resetPasswordToken.setToken(resetToken);
        // 1 hour expire time
        Date expireDate = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1));
        resetPasswordToken.setExpireDate(expireDate);
        resetPasswordTokenRepository.save(resetPasswordToken);

        // reset link string
        String resetLink = String.format("https://jagarv.vercel.app/reset-password/%s/%s", user.getEmail(), resetToken);
        
        // sends that mail with the link 
        sendMail.sendMail(sendResetCodeDTO.getEmail(), 
            "PASSWORD RESET AT JAGARV", 
            "To reset your password, please click on the following link:\n" + 
            resetLink + "\n" + 
            "This link will expire in 1 hour.\n" + 
            "Thank you!"
        );
    }
    
    // resets user password
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        // if user email not exist, throw exception
        User user = userRepository.findByEmail(resetPasswordDTO.getEmail())
            .orElseThrow(() -> new EmailNotFoundException("User not found"));
        
        // searches a reset password token in te db, if not found throw exceptio,
        ResetPasswordToken token = resetPasswordTokenRepository
            .findByUserEmailAndToken(resetPasswordDTO.getEmail(), resetPasswordDTO.getToken())
            .orElseThrow(() -> new InvalidResetTokenOrEmail("Invalid reset token or email"));
        
        // if expired throw exception
        if (token.hasExpired()) {
            throw new InvalidResetTokenOrEmail("Reset token has expired."); // token expired
        }
        
        // encoded new password
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));  
        userRepository.save(user);
        resetPasswordTokenRepository.delete(token);
    }
     
    // login with google social media
    public Cookie loginWithSocialMedia(Payload payload) {
        String email = (String) payload.get("email");

        User user = userRepository.findByEmail(email).orElse(null);
        
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setUsername((String) payload.get("name"));
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString())); // "placeholder" for social media users
            
            userRepository.save(user);
        }
        
        String jwtToken = jwtOutil.generateToken(user.getId(), user.getRole().name());
        
        Cookie jwtCookie = CookieOutil.generateJwtCookie(jwtToken);
        // will be setted on the response by the controller
        return jwtCookie; 
    }
     
    // check if the user is authenticated
    public void checkIfAuthenticated(String jwtToken) {
        if (jwtToken == null) {
            throw new UserSessionNotValidException("Please Log In.");
        }
        
        boolean isTokenValid = jwtOutil.validateToken(jwtToken);
        
        if (!isTokenValid) {
            throw new UserSessionNotValidException("Your Session Is Not Valid Or Has Expired, Please Log In.");
        }
    }
}