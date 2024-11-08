package com.app.jagarv.entity.product;

import jakarta.persistence.*;

@Entity
@Table(name = "variant_attribute_option")
public class VariantAttributeOption {
    
    // id of the attribute option
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // the variant to which this option belongs to
    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariant variant;
    
    // the attribute option this variant option belongs to
    @ManyToOne
    @JoinColumn(name = "attribute_option_id")
    private AttributeOption attributeOption;

    // getters and setterssw
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }

    public AttributeOption getAttributeOption() {
        return attributeOption;
    }

    public void setAttributeOption(AttributeOption attributeOption) {
        this.attributeOption = attributeOption;
    }
}
