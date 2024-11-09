package com.app.jagarv.dto.product;

import java.util.List;

public class AttributeDTO {

    private Long id;
    private String name;
    private List<AttributeOptionDTO> options;

    // Getters y Setters
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

    public List<AttributeOptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<AttributeOptionDTO> options) {
        this.options = options;
    }
}
