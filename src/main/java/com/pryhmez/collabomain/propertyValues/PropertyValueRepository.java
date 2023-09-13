package com.pryhmez.collabomain.propertyValues;

import com.pryhmez.collabomain.properties.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long> {

    Optional<PropertyValue> findTopByPropertyOrderByDateDesc(Property property);
}
