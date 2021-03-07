package com.mrapaport.unlu.sdypp.joiner.core.store.exceptions;

public class KeyAlreadyExistsException extends KeyValueStoreException {
    public KeyAlreadyExistsException(String key) {
        super(String.format("Key %s already exists in key value store.", key));
    }
}
