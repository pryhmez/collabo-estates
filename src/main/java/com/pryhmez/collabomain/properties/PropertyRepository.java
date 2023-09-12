package com.pryhmez.collabomain.properties;

import com.pryhmez.collabomain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    // Custom query to retrieve properties by user
    @Query("SELECT p FROM Property p WHERE p.creator = :user")
    Page<Property> findPropertiesByUser(User user, Pageable pageable);
}
