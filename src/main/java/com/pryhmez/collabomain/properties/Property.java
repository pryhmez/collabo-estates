package com.pryhmez.collabomain.properties;

import com.pryhmez.collabomain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    @NonNull
    private User creator;
    private String description;
    private Long price;
    private String address;
    private String city;
    private String state;
    private String propertyType;
    private BigInteger size;
    private String status;
    private Date dateCreated;
    private Date lastUpdated;
    private String tags;

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
