package com.piljoong.cacheflow.producer.domain;

import com.piljoong.cacheflow.producer.utils.Utils;

import java.util.Random;

public class CacheEvent {
    private String id;
    private String value;

    public CacheEvent() {
    }

    public CacheEvent(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static CacheEvent generate() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);

        String id = Utils.randomString(7);
        String value = Utils.randomString(5);

        return new CacheEvent(id, value);
    }
}
