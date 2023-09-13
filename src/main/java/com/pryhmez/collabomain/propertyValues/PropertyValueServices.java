package com.pryhmez.collabomain.propertyValues;

import com.pryhmez.collabomain.properties.Property;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    public PropertyValue addValue(Property property, BigDecimal newValue) {

        PropertyValue value = new PropertyValue().builder()
                .property(property)
                .propertyValue(newValue)
                .build();

        return propertyValueRepository.save(value);

    }

    public PropertyValue findLatestValueOfProperty (Property property) {

        Optional<PropertyValue> optionalPropertyValue = propertyValueRepository.findTopByPropertyOrderByDateDesc(property);

        PropertyValue lastPropertyValue = optionalPropertyValue.orElse(null);


        return lastPropertyValue;
    }

}
