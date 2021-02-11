package com.example.tenantCreation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})

public class TenantCreationApplication {

    private static ApplicationContext applicationContext;



    public static void main(String[] args) {

        SpringApplication.run(TenantCreationApplication.class, args);



    }

}





