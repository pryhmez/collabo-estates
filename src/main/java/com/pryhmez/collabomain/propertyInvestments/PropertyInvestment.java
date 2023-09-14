package com.pryhmez.collabomain.propertyInvestments;

import com.pryhmez.collabomain.properties.Property;
import com.pryhmez.collabomain.propertyValues.PropertyValue;
import com.pryhmez.collabomain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "property_investments")
public class PropertyInvestment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long InvestmentId;

    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", referencedColumnName = "propertyId")
    private Property property;

    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "investor_id", referencedColumnName = "userId")
    private User investor;

    private BigDecimal amount;

    private BigDecimal percentage;

    private Long sharesVolume;

    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "property_value_id", referencedColumnName = "propertyValueId")
    private PropertyValue snapshotPropertyValue;

    private Date time;


    @PrePersist
    public void setDateCreated() {
        this.time = new Date();
    }
}
