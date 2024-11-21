package com.app.jagarv.dto.user;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
    private String joinedAt; 
    private String lastLogin; 
    
    public UserDTO() {} 
    
    public UserDTO(Long id, String username, String email, String profilePicture) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
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
    
    public String getProfilePicture() {
        return profilePicture;
    }
    
    public void setProfilePicture(String profilePicture) {
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
}
