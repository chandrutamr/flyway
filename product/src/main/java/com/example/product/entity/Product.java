package com.example.product.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Product() {
    }
}
