package com.pryhmez.collabomain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pryhmez.collabomain.properties.Property;
import com.pryhmez.collabomain.propertyInvestments.PropertyInvestment;
import com.pryhmez.collabomain.propertyValues.PropertyValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(name = "user_name")
    @NonNull
    private String username;

    @Email
    @NonNull
    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @NonNull
    @JsonIgnore
    private String password;

    @NonNull
//    @ElementCollection(targetClass = UserEnums.UserRoles.class)
    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEnums.UserRoles> roles = new HashSet<>();

    @Column(name = "reg_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Property> properties = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PropertyInvestment> propertyInvestments = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        regDate = new Date();
    }
}