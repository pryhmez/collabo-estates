package com.pryhmez.collabomain.properties;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @PostMapping("/create")
    public void createProperty () {

    }

    @GetMapping("/getall")
    public void getAllProperties() {

    }

    @DeleteMapping("/delete")
    public void deleteProperty() {

    }
}
