package com.example.tenantCreation.DAO;

import com.example.tenantCreation.constants.DataBaseConfiguration;
import com.example.tenantCreation.constants.Modules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

@Repository
public class TenantDAO {

    @Autowired
    private DataBaseConfiguration databaseConfig;

    private Connection con = null;

    public void createDatabaseForTenant(String name) throws Exception {
        createSchema(name);
    }


    private Connection openConnection(String name) {
        if (con != null)
            closeConnection();
        try {
            Class.forName(databaseConfig.getDriver())
                    .newInstance();
            con = DriverManager.getConnection(
                    databaseConfig.getUrl(),
                    databaseConfig.getUser(),
                    databaseConfig.getPassword());
        }catch(ClassNotFoundException | InstantiationException |
            IllegalAccessException | SQLException e){
                System.err.println(e.getMessage());
            }
            return con;
        }



    private void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void createSchema(String name) throws Exception {
        Statement stmt = openConnection(name).createStatement();
        for(Modules value : Modules.values()){
            stmt.executeUpdate("CREATE DATABASE "+name+"_"+value.toString());
        }
        System.out.println("Schema created "+name);
        closeConnection();
    }
}