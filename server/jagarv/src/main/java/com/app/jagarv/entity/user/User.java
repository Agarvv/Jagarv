package com.app.jagarv.entity.user;

import com.app.jagarv.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "app_user") 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The username can not be blank")
    @Size(min = 2, max = 20, message = "The username should be min 2 chars and max 20")
    private String username;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email must be a valid email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password must not be null")
    @Size(min = 6, message = "Password must be min 6 chars")
    private String password;
    
    @Column(name = "profile_picture")
    private String profilePicture;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String joinedAt; 
    private String lastLogin; 
    private String adress; 

    public User() {
        this.role = Role.USER; 
    }

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = Role.USER; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getProfilePicture() 
    {
        return profilePicture; 
    }
    
    public void setProfilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }
    
    public String getJoinedAt() 
    {
        return joinedAt; 
    }
    
    public void setJoinedAt(String joinedAt) 
    {
        this.joinedAt = joinedAt;
    }
    
    public String getLastLogin()
    {
        return lastLogin;
    }
    
    public void setLastLogin(String lastLogin)
    {
        this.lastLogin = lastLogin; 
    }
    
    public String getAdress() {
        return adress; 
    }
    
    public void setAdress(String adress) {
        this.adress = adress; 
    }
}