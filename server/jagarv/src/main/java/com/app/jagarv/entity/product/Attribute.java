package com.app.jagarv.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "attribute")
public class Attribute {
    
    // the attribute id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // the attribute name, like: 'Color', 'Size' etc.
    @NotBlank
    private String name;
    
    // the product category this attribute belongs to
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;
    
    // the options of this attribute, like: 'Red', 'Blue', 'Green' etc.
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AttributeOption> options;
    
    // getters and setters
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<AttributeOption> getOptions() {
        return options;
    }

    public void setOptions(List<AttributeOption> options) {
        this.options = options;
    }
}
