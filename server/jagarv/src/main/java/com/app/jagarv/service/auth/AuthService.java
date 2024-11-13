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

import com.app.jagarv.entity.User;
import com.app.jagarv.dto.user.RegisterUserDTO;
import com.app.jagarv.dto.user.LoginUserDTO; 
import com.app.jagarv.repository.UserRepository;
import com.app.jagarv.exception.exceptions.users.EmailAlreadyExistsException;
import com.app.jagarv.exception.exceptions.users.UsernameAlreadyExistsException;
import com.app.jagarv.exception.exceptions.users.EmailNotFoundException;
import com.app.jagarv.exception.exceptions.users.InvalidCredentialsException;
import com.app.jagarv.exception.exceptions.users.InvalidResetTokenOrEmail;
import com.app.jagarv.exception.exceptions.users.UserSessionNotValidException;
import com.app.jagarv.entity.ResetPasswordToken;
import com.app.jagarv.outil.GenerateResetPasswordToken;
import com.app.jagarv.repository.ResetPasswordTokenRepository;
import com.app.jagarv.outil.SendMail;
import com.app.jagarv.outil.JwtOutil;
import com.app.jagarv.outil.CookieOutil;
import jakarta.servlet.http.Cookie;

import com.app.jagarv.dto.user.SendResetCodeDTO;
import com.app.jagarv.dto.user.ResetPasswordDTO;

import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.repository.cart.CartRepository;

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
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        
        // Save the user in the repository
        userRepository.save(user);

        // create a cart for the user that registers 
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

    }

    // Handles user login
    public Cookie loginUser(LoginUserDTO loginUserDTO) {
        try {
            Boolean userExists = userRepository.existsByEmail(loginUserDTO.getEmail()); 
            if(!userExists) {
                throw new EmailNotFoundException("Your Account Does not Exist...");
            }
            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginUserDTO.getEmail(), loginUserDTO.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = customUserDetails.getUser();

            String jwtToken = jwtOutil.generateToken(user.getId(), user.getRole().name());

            Cookie jwtCookie = CookieOutil.generateJwtCookie(jwtToken);
            
            return jwtCookie;

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Password Does Not Match...");
        }
    }

    public void sendResetCode(SendResetCodeDTO sendResetCodeDTO) {
        User user = userRepository.findByEmail(sendResetCodeDTO.getEmail()).orElseThrow(() -> 
            new EmailNotFoundException("Email not exists"));

        String resetToken = GenerateResetPasswordToken.generate();
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        resetPasswordToken.setUserEmail(user.getEmail());
        resetPasswordToken.setToken(resetToken);
        Date expireDate = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1));
        resetPasswordToken.setExpireDate(expireDate);
        resetPasswordTokenRepository.save(resetPasswordToken);

        String resetLink = String.format("https://jagarv.vercel.app/reset-password/%s/%s", user.getEmail(), resetToken);
        
        sendMail.sendMail(sendResetCodeDTO.getEmail(), 
            "PASSWORD RESET AT JAGARV", 
            "To reset your password, please click on the following link:\n" + 
            resetLink + "\n" + 
            "This link will expire in 1 hour.\n" + 
            "Thank you!"
        );
    }
    
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        User user = userRepository.findByEmail(resetPasswordDTO.getEmail())
            .orElseThrow(() -> new EmailNotFoundException("User not found"));

        ResetPasswordToken token = resetPasswordTokenRepository
            .findByUserEmailAndToken(resetPasswordDTO.getEmail(), resetPasswordDTO.getToken())
            .orElseThrow(() -> new InvalidResetTokenOrEmail("Invalid reset token or email"));

        if (token.hasExpired()) {
            throw new InvalidResetTokenOrEmail("Reset token has expired."); // token expired
        }

        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));  
        userRepository.save(user);
        resetPasswordTokenRepository.delete(token);
    }
 
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
        
        return jwtCookie; 
    }

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