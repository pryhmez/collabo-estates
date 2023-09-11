package com.pryhmez.collabomain.properties;

import com.pryhmez.collabomain.user.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<?> getAllProperties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> propertyPage = propertyService.getAllProperties(pageable);
        return ResponseEntity.ok(new PropertyDTO.Response("propeties retrieved successfully", propertyPage));
    }

    @GetMapping("/allbyuser/{username}")
    public ResponseEntity<?> getPropertiesByUser(
            @PathVariable String username,
            Pageable pageable
    ) {
        String state;
        if(username == null) {
            state = "nothing";
        } else {
            state = username;
        }
//        User user = userService.getUserById(userId); // Fetch the user based on userId
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }

//        Page<Property> properties = propertyService.getPropertiesByUser(user, pageable);
        return ResponseEntity.ok(state);
    }

    @DeleteMapping("/delete")
    public void deleteProperty() {

    }

    @PatchMapping("/update")
    public void updateProperty() {

    }
}
