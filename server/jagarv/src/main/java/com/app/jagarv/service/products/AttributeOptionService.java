package com.app.jagarv.service.products;

import com.app.jagarv.dto.product.create.CreateAttributeOptionDTO;
import com.app.jagarv.entity.product.Attribute;
import com.app.jagarv.entity.product.AttributeOption;
import com.app.jagarv.repository.product.AttributeOptionRepository;
import com.app.jagarv.repository.product.AttributeRepository;
import com.app.jagarv.exception.exceptions.products.AttributeNotFoundException;


import org.springframework.stereotype.Service;

@Service
public class AttributeOptionService {

    private final AttributeOptionRepository optionRepository;
    private final AttributeRepository attributeRepository;

    public AttributeOptionService(AttributeOptionRepository optionRepository, AttributeRepository attributeRepository) {
        this.optionRepository = optionRepository;
        this.attributeRepository = attributeRepository;
    }

    public void createOption(CreateAttributeOptionDTO optionDTO) {
        Attribute attribute = attributeRepository.findById(optionDTO.getAttributeId())
                .orElseThrow(() -> new AttributeNotFoundException("Attribute not found with id: " + optionDTO.getAttributeId()));

        AttributeOption option = new AttributeOption();
        option.setValue(optionDTO.getValue());
        option.setAttribute(attribute); 

        optionRepository.save(option);
    }
}