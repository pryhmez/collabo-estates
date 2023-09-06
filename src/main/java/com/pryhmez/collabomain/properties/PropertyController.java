package com.pryhmez.collabomain.properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @PostMapping("/create")
    public void createProperty () {

    }

    @GetMapping("/getall")
    public void getAllProperties() {

    }
}
