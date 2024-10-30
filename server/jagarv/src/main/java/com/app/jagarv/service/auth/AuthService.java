package com.app.jagarv.service.auth;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.jagarv.entity.User;
import com.app.jagarv.dto.user.RegisterUserDTO;
import com.app.jagarv.repository.UserRepository;
import com.app.jagarv.exception.exceptions.users.EmailAlreadyExistsException;
import com.app.jagarv.exception.exceptions.users.UsernameAlreadyExistsException;

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
      // usernames should be unique for each user.
      if(userRepository.existsByUsername(newUser.getUsername())) {
        throw new UsernameAlreadyExistsException("That Username Already Exists, Try With Another.");
      }
      
      // emails should be also unique for each user.
      if(userRepository.existsByEmail(newUser.getEmail())) {
        throw new EmailAlreadyExistsException("That Email Already Exists, Try With Another.");
      }

      User user = new User();
      user.setUsername(newUser.getUsername());
      user.setEmail(newUser.getEmail());
      user.setPassword(passwordEncoder.encode(newUser.getPassword())); // encoded password for security
      userRepository.save(user);

  }
}