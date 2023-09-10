package com.pryhmez.collabomain.properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
public class PropertyController {
    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createProperty (@RequestBody Property property) {

        Property newProperty = propertyRepository.save(property);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PropertyDTO.Response("property added successfully", newProperty));
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
