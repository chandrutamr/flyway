package com.example.tenantCreation.service;

import org.springframework.stereotype.Component;



public interface ITenant {

     Boolean createTenant(String name) throws Exception;
}
