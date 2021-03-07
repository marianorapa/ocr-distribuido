package com.mrapaport.unlu.sdypp.joiner.core.store.redis;

import com.mrapaport.unlu.sdypp.joiner.core.store.KeyValueStore;
import com.mrapaport.unlu.sdypp.joiner.core.store.exceptions.KeyAlreadyExistsException;
import com.mrapaport.unlu.sdypp.joiner.core.store.exceptions.KeyValueStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;

@Repository
public class RedisKeyValueStore implements KeyValueStore {

    @Autowired
    JedisPool jedisPool;

    @Override
    public void save(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        if (jedis.exists(key))
            throw new KeyAlreadyExistsException(key);
        String statusCode = jedis.set(key, value);
        if (Objects.isNull(statusCode))
            throw new KeyValueStoreException(String.format("Value for key %s couldn't be persisted.", key));
    }

    @Override
    public String get(String key) {
        return jedisPool.getResource().get(key);
    }

    @Override
    public boolean exists(String key) {
        return jedisPool.getResource().exists(key);
    }

}