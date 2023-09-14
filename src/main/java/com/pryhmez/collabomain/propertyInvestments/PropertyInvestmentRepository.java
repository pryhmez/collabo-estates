package com.pryhmez.collabomain.propertyInvestments;

import com.pryhmez.collabomain.properties.Property;
import com.pryhmez.collabomain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyInvestmentRepository extends JpaRepository<PropertyInvestment, Long> {

    Page<PropertyInvestment> findAllByInvestor(User user, Pageable pageable);

    Page<PropertyInvestment> findByProperty(Property property, Pageable pageable);
    Page<PropertyInvestment> findByPropertyPropertyId(Long propertyId, Pageable pageable);
}
