package com.app.jagarv.service.auth;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.jagarv.entity.User;
import com.app.jagarv.dto.user.RegisterUserDTO;
import com.app.jagarv.dto.user.LoginUserDTO; 
import com.app.jagarv.repository.UserRepository;
import com.app.jagarv.exception.exceptions.users.EmailAlreadyExistsException;
import com.app.jagarv.exception.exceptions.users.UsernameAlreadyExistsException;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager; 

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
    public String loginUser(LoginUserDTO loginUserDTO) {
        try {
            // Create authentication token with provided credentials
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginUserDTO.getEmail(), loginUserDTO.getPassword()
            );

            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "Welcome back"; // Return a success message or a token as needed
            
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials"); // this will be in the future a "CredentialsNotValidException" that will have a message like:
            // "Your Credentials are wrong."
        }
    }
}