package com.app.jagarv.dto.product;

import java.util.List;

public class ProductCategoryDTO {
    private Long id;  
    private String name;
    private List<AttributeDTO> attributes;

    public ProductCategoryDTO() {
    }

    public ProductCategoryDTO(Long id, String name, List<AttributeDTO> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDTO> attributes) {
        this.attributes = attributes;
    }
}