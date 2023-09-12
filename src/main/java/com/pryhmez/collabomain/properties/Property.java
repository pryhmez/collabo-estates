package com.pryhmez.collabomain.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pryhmez.collabomain.propertyInvestments.PropertyInvestment;
import com.pryhmez.collabomain.propertyValues.PropertyValue;
import com.pryhmez.collabomain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;
    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    @NonNull
    private User creator;
    private String description;
    private BigDecimal price;
    private String address;
    private String city;
    private String state;
    private String propertyType;
    private BigInteger size;
    private String status;
    private Date dateCreated;
    private Date lastUpdated;
    private String tags;

    @JsonIgnore
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PropertyInvestment> propertyInvestments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PropertyValue> propertyValues = new ArrayList<>();

    @PreUpdate
    public void setLastUpdated() {
        this.lastUpdated = new Date();
    }

    @PrePersist
    public void setDateCreated() {
        this.dateCreated = new Date();
        this.lastUpdated = new Date();
    }
}
