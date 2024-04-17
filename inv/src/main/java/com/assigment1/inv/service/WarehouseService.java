package com.assigment1.inv.service;

import com.assigment1.inv.model.Warehouse;
import com.assigment1.inv.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        if (warehouseRepository.existsById(id)) {
            warehouse.setId(id);
            return warehouseRepository.save(warehouse);
        }
        return null;
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}
