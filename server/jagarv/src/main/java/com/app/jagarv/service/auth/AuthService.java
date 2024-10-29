package com.app.jagarv.service.auth;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.jagarv.entity.User;
import com.app.jagarv.dto.user.RegisterUserDTO;
import com.app.jagarv.repository.UserRepository;

// this service will handle all the login, register, password resets logic.
// the auth with google wil be implemented on another file of the auth package.
@Service
public class AuthService {
  // injections
  @Autowired
  private UserRepository userRepository;

  @Autowired 
  private PasswordEncoder passwordEncoder;
  
  // registers a user to the system
  public void registerUser(RegisterUserDTO newUser) {
      if(userRepository.existsByUsername(newUser.getUsername())) {
        // here will go UsernameAlreadyExistsException
      }

      if(userRepository.existsByEmail(newUser.getEmail())) {
        // here will go EmailAlreadyExistsException
      }

      User user = new User();
      user.setUsername(newUser.getUsername());
      user.setEmail(newUser.getEmail());
      user.setPassword(passwordEncoder.encode(newUser.getPassword())); // encoded password for security
      userRepository.save(user);

  }
}