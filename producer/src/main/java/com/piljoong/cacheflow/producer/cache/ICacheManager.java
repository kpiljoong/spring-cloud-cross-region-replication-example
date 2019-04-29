package com.piljoong.cacheflow.producer.cache;

public interface ICacheManager {
    String get(String key);

    void set(String key, String value);
}
