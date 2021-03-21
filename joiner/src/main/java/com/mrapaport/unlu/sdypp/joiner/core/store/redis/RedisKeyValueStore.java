package com.mrapaport.unlu.sdypp.joiner.core.store.redis;

import com.mrapaport.unlu.sdypp.joiner.core.store.KeyValueStore;
import com.mrapaport.unlu.sdypp.joiner.core.store.exceptions.KeyAlreadyExistsException;
import com.mrapaport.unlu.sdypp.joiner.core.store.exceptions.KeyValueStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Repository
public class RedisKeyValueStore implements KeyValueStore {

    @Autowired
    JedisPool jedisPool;

    Jedis jedisClient;

    Logger logger = LoggerFactory.getLogger(RedisKeyValueStore.class);

    @PostConstruct
    private void init() {
        this.jedisClient = jedisPool.getResource();
    }

    @Override
    public void save(String key, String value) {
        logger.info("Saving key {}", key);
//        Jedis jedis = jedisPool.getResource();

        if (jedisClient.exists(key))
            throw new KeyAlreadyExistsException(key);
        String statusCode = jedisClient.set(key, value);
        if (Objects.isNull(statusCode))
            throw new KeyValueStoreException(String.format("Value for key %s couldn't be persisted.", key));
    }

    @Override
    public String get(String key) {
//        return jedisPool.getResource().get(key);
        return jedisClient.get(key);
    }

    @Override
    public boolean exists(String key) {
        return jedisClient.exists(key);
    }

}