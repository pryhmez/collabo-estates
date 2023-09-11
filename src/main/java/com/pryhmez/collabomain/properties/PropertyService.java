package com.pryhmez.collabomain.properties;

import com.pryhmez.collabomain.auth.AuthController;
import com.pryhmez.collabomain.user.User;
import com.pryhmez.collabomain.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserService userService;

    public Property createProperty (PropertyDTO.AddPropertyDto property) {

        User user = userService.getUser();

        Property newProperty = new Property().builder()
                .creator(user)
                .propertyType(property.getPropertyType())
                .city(property.getCity())
                .price(property.getPrice())
                .state(property.getState())
                .description(property.getDescription())
                .address(property.getAddress())
                .size(property.getSize())
                .tags(property.getTags())
                .build();



        return propertyRepository.save(newProperty);
    }
}
