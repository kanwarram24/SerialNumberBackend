package com.example.SerialNumberScanner.Repositories;

import com.example.SerialNumberScanner.Models.SerialNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SerialNumberRepository extends JpaRepository<SerialNumber, Long> {
    Optional<SerialNumber> findBySerialNumber(String serialNumber);
}
