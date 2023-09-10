package com.pryhmez.collabomain.properties;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyRepository propertyRepository;
    private final PropertyService propertyService;

    public PropertyController(PropertyRepository propertyRepository, PropertyService propertyService) {
        this.propertyRepository = propertyRepository;
        this.propertyService = propertyService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createProperty (
            @RequestBody @Valid
            PropertyDTO.AddPropertyDto property) {

        Property createdProperty = propertyService.createProperty(property);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PropertyDTO.Response("property added successfully", createdProperty));
    }

    @GetMapping("/getall")
    public void getAllProperties() {

    }

    @DeleteMapping("/delete")
    public void deleteProperty() {

    }

    @PatchMapping("/update")
    public void updateProperty() {

    }
}
