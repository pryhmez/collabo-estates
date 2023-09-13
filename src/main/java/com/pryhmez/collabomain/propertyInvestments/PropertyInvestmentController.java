package com.pryhmez.collabomain.propertyInvestments;

import com.pryhmez.collabomain.auth.AuthController;
import com.pryhmez.collabomain.properties.PropertyDTO;
import com.pryhmez.collabomain.propertyValues.PropertyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/investment")
public class PropertyInvestmentController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final PropertyInvestmentServices propertyInvestmentServices;

    @Autowired
    public PropertyInvestmentController(PropertyInvestmentServices propertyInvestmentServices) {
        this.propertyInvestmentServices = propertyInvestmentServices;
    }

    @PostMapping("/invest")
    @Transactional
    public ResponseEntity<?> invest (
            @RequestBody PropertyInvestmentDTOs.AddInvestmentDto investment
            ) {

        PropertyInvestment propertyInvestment = propertyInvestmentServices.invest(investment);

        return ResponseEntity.accepted()
                .body(new PropertyDTO.Response(
                        "Investment made successfully", propertyInvestment));
    }
}
