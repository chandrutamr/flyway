package com.example.product.connections;

import lombok.Data;

@Data
public class Tenant {


    private Integer id;
    private String name;

    Tenant(Integer id, String name) {
        this.id = id;
        this.name = name;

    }


}
