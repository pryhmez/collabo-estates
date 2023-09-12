package com.pryhmez.collabomain.propertyValues;

import com.pryhmez.collabomain.properties.Property;
import org.springframework.stereotype.Service;

@Service
public class PropertyValueServices {
    private final PropertyValueRepository propertyValueRepository;

    public PropertyValueServices(PropertyValueRepository propertyValueRepository) {
        this.propertyValueRepository = propertyValueRepository;
    }

    public PropertyValue addValue(Property property) {

        PropertyValue value = new PropertyValue().builder()
                .property(property)
                .propertyValue(property.getPrice())
                .build();

        return propertyValueRepository.save(value);

    }
}
