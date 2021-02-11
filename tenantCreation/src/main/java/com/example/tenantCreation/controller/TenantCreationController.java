package com.example.tenantCreation.controller;




import com.example.tenantCreation.DAO.TenantDAO;
import com.example.tenantCreation.connections.RedisClientConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.example.tenantCreation.service.ITenant;
import redis.clients.jedis.Jedis;

@RestController
public class TenantCreationController {

    @Autowired 
    ITenant itenant;



    @GetMapping("/tenant/{name}")
    public ResponseEntity<String> createTenant(@PathVariable("name") String name){

        try{
            Boolean status =  itenant.createTenant(name);

            return new ResponseEntity<String>(String.valueOf(status), HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(String.valueOf(false), HttpStatus.EXPECTATION_FAILED);
        }
    }


}
