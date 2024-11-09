package com.app.jagarv.dto.product;

public class AttributeOptionDTO {

    private Long id;          
    private String value;     

    // Constructor
    public AttributeOptionDTO() {
    }

    public AttributeOptionDTO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
