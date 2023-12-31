package com.pryhmez.collabomain.properties;

import com.pryhmez.collabomain.propertyValues.PropertyValue;
import com.pryhmez.collabomain.propertyValues.PropertyValueServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyRepository propertyRepository;
    private final PropertyService propertyService;
    private final PropertyValueServices propertyValueServices;

    public PropertyController(PropertyRepository propertyRepository,
                              PropertyService propertyService,
                              PropertyValueServices propertyValueServices) {
        this.propertyRepository = propertyRepository;
        this.propertyService = propertyService;
        this.propertyValueServices = propertyValueServices;
    }


    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createProperty (
            @RequestBody @Valid
            PropertyDTO.AddPropertyDto property) {

        Property createdProperty = propertyService.createProperty(property);

        PropertyValue newValue = propertyValueServices.addValue(createdProperty);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PropertyDTO.Response("property added successfully", createdProperty));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<?> getAllProperties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> propertyPage = propertyService.getAllProperties(pageable);
        return ResponseEntity.ok(new PropertyDTO.Response("propeties retrieved successfully", propertyPage));
    }

    @GetMapping("/all/me")
    public ResponseEntity<?> getAllMyProperties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Principal principal
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> propertyPage = propertyService.getAllProperties(pageable, principal.getName());
        return ResponseEntity.ok(new PropertyDTO.Response("propeties retrieved successfully", propertyPage));
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<?> getPropertiesByUser(
            @PageableDefault(size = 10, page = 0) Pageable pageable,
            @PathVariable String username
    ) {

//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }

        Page<Property> propertyPage = propertyService.getAllProperties(pageable, username);
        return ResponseEntity.ok(new PropertyDTO.Response("propeties retrieved successfully", propertyPage));
    }

    @DeleteMapping("/delete")
    public void deleteProperty() {

    }

    @PatchMapping("/update")
    public void updateProperty() {

    }
}
