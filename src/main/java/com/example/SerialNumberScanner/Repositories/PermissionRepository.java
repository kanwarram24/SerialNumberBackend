package com.example.SerialNumberScanner.Repositories;

import com.example.SerialNumberScanner.Models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
