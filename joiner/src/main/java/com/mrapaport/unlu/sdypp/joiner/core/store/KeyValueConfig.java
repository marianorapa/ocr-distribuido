package com.mrapaport.unlu.sdypp.joiner.core.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("application.properties")
public class KeyValueConfig {

    @Value("${redis.host}")
    private String host;

    @Bean
    public JedisPool jedis(){
        return new JedisPool(new JedisPoolConfig(), host);
    }

}
