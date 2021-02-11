package com.example.product.connections;

import redis.clients.jedis.Jedis;


public class RedisClientConnection {


    private static Jedis jedis;

    private RedisClientConnection(){
        if(jedis == null){
            jedis = new Jedis("127.0.0.1",6379);
        }
    }

    public static Jedis getInstance(){
        new RedisClientConnection();
        return jedis;
    }
}
