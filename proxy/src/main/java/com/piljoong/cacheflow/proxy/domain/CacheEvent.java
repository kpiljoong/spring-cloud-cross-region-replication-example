package com.piljoong.cacheflow.proxy.domain;

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
}
