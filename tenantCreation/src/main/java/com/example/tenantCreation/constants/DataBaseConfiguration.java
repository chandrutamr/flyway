package com.example.tenantCreation.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class DataBaseConfiguration {
    @Value("${application.database.url}")
    private String url;

    @Value("${application.database.user}")
    private String user;

    @Value("${application.database.password}")
    private String password;

    @Value("${application.database.driver}")
    private String driver;

    @Value("${application.hibernate.dialect}")
    private String dialect;

    @Value("${application.hibernate.hbm2ddl.auto}")
    private String hbm2ddl_auto;

    @Value("${application.hibernate.show_sql}")
    private String show_sql;
}