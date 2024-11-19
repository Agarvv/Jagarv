package com.app.jagarv.dto.product.read;

import com.app.jagarv.dto.user.UserDTO;

public class ProductOpinionDTO {

    private Long id;
    private String content;
    private UserDTO user; 
    
    
    public ProductOpinionDTO(Long id, String content, UserDTO user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUserId(UserDTO user) {
        this.user = user;
    }
}