package com.assigment1.inv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")  // Use @Table to specify the table name
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "int default 0")
    private String name;

    @Column(name = "description", columnDefinition = "int default 0")
    private String description;

    @Column(name = "price", columnDefinition = "int default 0")
    private double price;

    @Column(name = "reorderLevel", columnDefinition = "int default 0")
    private int reorderLevel;

    @Column(name = "inventory_level", columnDefinition = "int default 0")
    private int inventoryLevel;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

}
