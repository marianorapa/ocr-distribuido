package com.mrapaport.unlu.sdypp.joiner.core.store;

public interface KeyValueStore {

    void save(String key, String value);

    String get(String key);

    boolean exists(String key);

}
