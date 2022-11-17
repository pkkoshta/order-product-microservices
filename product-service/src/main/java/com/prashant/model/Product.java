package com.prashant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pid;
    private String name;
    private String description;
    private int amount;
    private int qty;

    public Product(String name, String description, int amount, int qty) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.qty = qty;
    }
}