package com.pryhmez.collabomain.propertyInvestments;

import com.pryhmez.collabomain.properties.Property;
import com.pryhmez.collabomain.properties.PropertyRepository;
import com.pryhmez.collabomain.properties.PropertyService;
import com.pryhmez.collabomain.propertyValues.PropertyValue;
import com.pryhmez.collabomain.propertyValues.PropertyValueServices;
import com.pryhmez.collabomain.user.User;
import com.pryhmez.collabomain.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PropertyInvestmentServices {
    private static final Logger log = LoggerFactory.getLogger(PropertyInvestmentServices.class);

    private final PropertyService propertyService;
    private final UserService userService;
    private final PropertyValueServices propertyValueServices;
    private final PropertyInvestmentRepository propertyInvestmentRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyInvestmentServices(PropertyService propertyService,
                                      UserService userService,
                                      PropertyValueServices propertyValueServices, PropertyInvestmentRepository propertyInvestmentRepository,
                                      PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.userService = userService;
        this.propertyValueServices = propertyValueServices;
        this.propertyInvestmentRepository = propertyInvestmentRepository;
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public PropertyInvestment invest (PropertyInvestmentDTOs.AddInvestmentDto investmentDto) {

        Property property = propertyService.getPropertyById(investmentDto.getPropertyId());
        User investor =  userService.getUser();
        PropertyValue propertyValue = propertyValueServices.findLatestValueOfProperty(property);

        BigDecimal percentage = (
                investmentDto.getAmount()
                        .divide(propertyValue.getPropertyValue(), 4, RoundingMode.HALF_UP))
                .multiply(new BigDecimal("100"));

        BigDecimal shares = percentage.multiply(new BigDecimal("10000")).setScale(0, RoundingMode.HALF_DOWN);
        log.info(percentage.toString());

        PropertyInvestment investment = new PropertyInvestment().builder()
                .amount(investmentDto.getAmount())
                .investor(investor)
                .sharesVolume(shares.longValue())
                .property(property)
                .percentage(percentage)
                .snapshotPropertyValue(propertyValue)
                        .build();

        propertyValueServices.addValue(property, propertyValue.getPropertyValue().add(investmentDto.getAmount()));

        return propertyInvestmentRepository.save(investment);
    }

    public Page<PropertyInvestment> getInvestmentsByUser (Pageable pageable) {

        User user = userService.getUser();

        return propertyInvestmentRepository.findAllByInvestor(user, pageable);
    }
}
