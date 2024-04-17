package com.assigment1.inv.controller;

import com.assigment1.inv.exeptions.BadRequestException;
import com.assigment1.inv.model.Supplier;
import com.assigment1.inv.service.SupplierService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);


    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
//        Supplier createdSupplier = supplierService.createSupplier(supplier);
//        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
        if (supplier.getId() != null) {
            // Log error and throw exception if ID is not null
            log.error("Cannot have an ID {}", supplier);
            throw new BadRequestException(SupplierController.class.getSimpleName(), "Id");
        }
        if (supplier.getName() == null || supplier.getName().isEmpty()) {
            // Log error and throw exception if name is null or empty
            log.error("Cannot have an empty name {}", supplier);
            throw new BadRequestException(SupplierController.class.getSimpleName(), "Name");
        }
        // Create the supplier
        Supplier createdSupplier = supplierService.createSupplier(supplier);
        // Return the created supplier
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        Supplier updatedSupplier = supplierService.updateSupplier(id, supplier);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
