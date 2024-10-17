package com.example.SerialNumberScanner.Controllers;

import com.example.SerialNumberScanner.Models.SerialNumber;
import com.example.SerialNumberScanner.Repositories.SerialNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/serialnumbers")
public class SerialNumberController {

    @Autowired
    private SerialNumberRepository serialNumberRepository;

    // Admin and Advanced User can view all serial numbers
    @PreAuthorize("hasAnyRole('ADMIN', 'ADVANCED_USER', 'BASIC_USER')")
    @GetMapping
    public List<SerialNumber> getAllSerialNumbers() {
        return serialNumberRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADVANCED_USER')")
    @PostMapping
    public ResponseEntity<SerialNumber> addSerialNumber(@RequestBody SerialNumber serialNumber) {
        SerialNumber savedSerialNumber = serialNumberRepository.save(serialNumber);
        return new ResponseEntity<>(savedSerialNumber, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADVANCED_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<SerialNumber> updateSerialNumber(
            @PathVariable Long id, @RequestBody SerialNumber updatedSerialNumber) {

        Optional<SerialNumber> existing = serialNumberRepository.findById(id);
        if (existing.isPresent()) {
            SerialNumber serialNumber = existing.get();
            serialNumber.setSerialNumber(updatedSerialNumber.getSerialNumber());
            serialNumberRepository.save(serialNumber);
            return ResponseEntity.ok(serialNumber);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSerialNumber(@PathVariable Long id) {
        serialNumberRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADVANCED_USER', 'BASIC_USER')")
    @GetMapping("/search")
    public ResponseEntity<SerialNumber> searchSerialNumber(@RequestParam String serialNumber) {
        Optional<SerialNumber> found = serialNumberRepository.findBySerialNumber(serialNumber);
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
