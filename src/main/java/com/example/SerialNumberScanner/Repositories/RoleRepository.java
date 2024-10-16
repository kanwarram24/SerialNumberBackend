package com.example.SerialNumberScanner.Repositories;

import com.example.SerialNumberScanner.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
