package com.mrapaport.unlu.sdypp.joiner.core.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;

@Configuration
@PropertySource("classpath:application.properties")
public class KeyValueConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.user}")
    private String user;

    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisPool jedis(){
        return new JedisPool(host, port, user, password);
    }
}