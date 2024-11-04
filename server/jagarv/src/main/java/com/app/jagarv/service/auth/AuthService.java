package com.app.jagarv.service.auth;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager; 

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;
    
    @Autowired 
    private SendMail sendMail;

    @Autowired
    private JwtOutil jwtOutil;

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
    }

    // Handles user login
    public String loginUser(LoginUserDTO loginUserDTO, HttpServletResponse response) {
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
        response.addCookie(jwtCookie);

        return jwtToken;

    } catch (BadCredentialsException e) {
        throw new InvalidCredentialsException("Password Does Not Match...");
    }
}


    public String sendResetCode(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> 
        new EmailNotFoundException("Email not exists"));

    String resetToken = GenerateResetPasswordToken.generate();
    ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
    resetPasswordToken.setUserEmail(user.getEmail());
    resetPasswordToken.setToken(resetToken);
    Date expireDate = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1));
    resetPasswordToken.setExpireDate(expireDate);
    resetPasswordTokenRepository.save(resetPasswordToken);

    String resetLink = String.format("https://jagarv.vercel.app/reset-password/%s/%s", user.getEmail(), resetToken);
    
    sendMail.sendMail(email, 
        "PASSWORD RESET AT JAGARV", 
        "To reset your password, please click on the following link:\n" + 
        resetLink + "\n" + 
        "This link will expire in 1 hour.\n" + 
        "Thank you!"
    );

    return "ok";
    
    }
    
    
    public String resetPassword(String newPassword, String userEmail, String resetToken) {
    User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new EmailNotFoundException("User not found"));

    ResetPasswordToken token = resetPasswordTokenRepository
        .findByUserEmailAndToken(userEmail, resetToken)
        .orElseThrow(() -> new InvalidResetTokenOrEmail("Invalid reset token or email"));

    if (token.getExpireDate().before(new Date())) {
        throw new InvalidResetTokenOrEmail("Reset token has expired.");
    }

    user.setPassword(passwordEncoder.encode(newPassword));  
    userRepository.save(user);
    resetPasswordTokenRepository.delete(token);

    return "Password reset successfully!";
}
 
public String loginWithSocialMedia(Payload payload, HttpServletResponse response) {
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
    response.addCookie(jwtCookie);
    
    return jwtToken; 
}

public void checkIfAuthenticated(String jwtToken) {
    if(jwtToken == null) {
        throw new UserSessionNotValidException("Please Log In.");
    }
    
    boolean isTokenValid = jwtOutil.validateToken(jwtToken);
    
    if(!isTokenValid) {
        throw new UserSessionNotValidException("Your Session Is Not Valid Or Has Expired, Please Log In.");
    }
    
    
}

    
}