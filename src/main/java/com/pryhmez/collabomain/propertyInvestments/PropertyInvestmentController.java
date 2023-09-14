package com.pryhmez.collabomain.propertyInvestments;

import com.pryhmez.collabomain.auth.AuthController;
import com.pryhmez.collabomain.properties.PropertyDTO;
import com.pryhmez.collabomain.propertyValues.PropertyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getallbyuser")
    public ResponseEntity<?> getInvestmentsByUser (
            @PageableDefault(size = 10, page = 0) Pageable pageable
            ) {

        Page<PropertyInvestment> investments = propertyInvestmentServices.getInvestmentsByUser(pageable);

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new PropertyInvestmentDTOs.Response(
                        "Investments retrieved successfully", investments));
    }
}
