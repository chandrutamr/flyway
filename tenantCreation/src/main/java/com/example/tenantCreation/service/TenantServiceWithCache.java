package com.example.tenantCreation.service;


import com.example.tenantCreation.DAO.TenantDAO;
import com.example.tenantCreation.connections.RedisClientConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import com.example.tenantCreation.constants.Modules;

@Service
class TenantServiceWithCache implements  ITenant {

    @Autowired
    TenantDAO tenantDAO;

    private final String redisTenantValue = "tenant_list";

    @Override
    public Boolean createTenant(String name) throws Exception {
        Jedis jedis = RedisClientConnection.getInstance();
        if(!jedis.exists(name)){
            Map<String,String> moduleMap = new HashMap<String,String>();
            for(Modules module : Modules.values()){
                String moduleName = module.name();
                moduleMap.put(moduleName,"false");
            }
            jedis.hmset(name,moduleMap);

            createDatabaseForNewTenant(name);

            updateRedisKey(name);
            return true;
        }else{
            throw new Exception("Tenant already present");
        }
    }


    private void updateRedisKey(String name) {
        Jedis jedis = RedisClientConnection.getInstance();
        if(jedis.exists(redisTenantValue)){
            Map<String,String> tenantMap = jedis.hgetAll(redisTenantValue);
            String keyToInsert = tenantMap.size()+1+"";
            tenantMap.put(keyToInsert,name);
            jedis.hmset(redisTenantValue,tenantMap);
        }else{
            Map<String,String> tenantMap = new HashMap<String,String>();
            tenantMap.put("1",name);
            jedis.hmset(redisTenantValue,tenantMap);
        }

    }

    private void createDatabaseForNewTenant(String name) throws Exception{

        tenantDAO.createDatabaseForTenant(name);
    }
}
