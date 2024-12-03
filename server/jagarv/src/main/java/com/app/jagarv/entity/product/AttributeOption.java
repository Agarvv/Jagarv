package com.app.jagarv.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "attribute_option")
public class AttributeOption {
    
    // id of the attribute option
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // value, like if 'attribute' is equal to "color", then value can be "red" or "green" or "blue".
    @NotBlank
    private String value;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    @JsonManagedReference
    private Attribute attribute;

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

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
