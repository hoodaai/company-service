package com.persistent.repositories;

import com.persistent.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 ** Contains database operations for Company entity.
 */

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(@Param("name") String name);
    Optional<Company> findByEmail(@Param("email") String email);
    Optional<Company> findByCompanyId(@Param("name") Integer id);
}
