package com.example.SerialNumberScanner.Repositories;

import com.example.SerialNumberScanner.Models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}

