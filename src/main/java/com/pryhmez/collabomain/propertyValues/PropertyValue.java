package com.pryhmez.collabomain.propertyValues;

import com.pryhmez.collabomain.properties.Property;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "property_values")
public class PropertyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", referencedColumnName = "userId")
    @NonNull
    private Property Property;

    private BigDecimal propertyValue;
    private Date date;

    @PrePersist
    public void setDateCreated() {
        this.date = new Date();
    }
}
