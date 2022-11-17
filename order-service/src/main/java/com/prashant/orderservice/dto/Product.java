package com.prashant.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

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
