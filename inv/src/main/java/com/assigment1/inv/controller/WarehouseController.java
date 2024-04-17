package com.assigment1.inv.controller;

import com.assigment1.inv.exeptions.BadRequestException;
import com.assigment1.inv.exeptions.ResourceNotFoundException;
import com.assigment1.inv.model.Product;
import com.assigment1.inv.model.Warehouse;
import com.assigment1.inv.service.WarehouseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
//        Warehouse createdWarehouse = warehouseService.createWarehouse(warehouse);
//        return new ResponseEntity<>(createdWarehouse, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody Warehouse warehouse) {
        if (warehouse.getId() != null) {
            // Log error and throw exception if ID is not null
            log.error("Cannot have an ID {}", warehouse);
            throw new BadRequestException(WarehouseController.class.getSimpleName(), "Id");
        }
        if (warehouse.getName() == null || warehouse.getName().isEmpty()) {
            // Log error and throw exception if name is null or empty
            log.error("Cannot have an empty name {}", warehouse);
            throw new BadRequestException(WarehouseController.class.getSimpleName(), "Name");
        }
        // Create the warehouse
        Warehouse createdWarehouse = warehouseService.createWarehouse(warehouse);
        // Return the created warehouse
        return new ResponseEntity<>(createdWarehouse, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse) {
        Warehouse updatedWarehouse = warehouseService.updateWarehouse(id, warehouse);
        return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
//        // Get the existing warehouse
//        Warehouse existingWarehouse = warehouseService.getWarehouseById(id);
//        if (existingWarehouse == null) {
//            throw new ResourceNotFoundException("Warehouse", "id", id);
//        }
//
//        // Update the warehouse with the provided fields
//        for (Map.Entry<String, Object> entry : updates.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//
//            // Perform update based on the field name
//            switch (key) {
//                case "name":
//                    existingWarehouse.setName((String) value);
//                    break;
//                case "location":
//                    existingWarehouse.setLocation((String) value);
//                    break;
//                // Add cases for other fields as needed
//                default:
//                    break;
//            }
//        }
//
//        // Save the updated warehouse
//        Warehouse updatedWarehouse = warehouseService.updateWarehouse(existingWarehouse);
//
//        // Return the updated warehouse
//        return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<Warehouse> Warehouse(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
//        Warehouse warehouse = WarehouseService;
//        if (product == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        for (Map.Entry<String, Object> entry : updates.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//
//            switch (key) {
//                case "name":
//                    product.setName((String) value);
//                    break;
//                case "description":
//                    product.setDescription((String) value);
//                    break;
//                case "price":
//                    product.setPrice((Double) value);
//                    break;
//                case "reorderLevel":
//                    product.setReorderLevel((Integer) value);
//                    break;
//                case "inventoryLevel":
//                    product.setInventoryLevel((Integer) value);
//                    break;
//                default:
//                    // Ignore unknown properties
//                    break;
//            }
//        }
//
//        Product updatedProduct = productService.updateProduct(id, product);
//        return ResponseEntity.ok(updatedProduct);
//    }




}
