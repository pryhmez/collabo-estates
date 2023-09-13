package com.pryhmez.collabomain.propertyInvestments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyInvestmentRepository extends JpaRepository<PropertyInvestment, Long> {

}
