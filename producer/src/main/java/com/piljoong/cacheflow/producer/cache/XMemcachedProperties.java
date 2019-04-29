package com.piljoong.cacheflow.producer.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "memcached", ignoreInvalidFields = true)
public class XMemcachedProperties {
    private String servers;
    private int poolSize;
    private int connectionTimeout;
    private int opTimeout;

    public String getServers() {
        return this.servers;
    }
    public void setServers(String servers) {  this.servers = servers; }

    public int getPoolSize() {
        return this.poolSize;
    }
    public void setPoolSize(int poolSize) { this.poolSize = poolSize; }

    public int getConnectionTimeout() {  return this.connectionTimeout; }
    public void setConnectionTimeout(int timeout) { this.connectionTimeout = timeout; }

    public int getOpTimeout() {
        return this.opTimeout;
    }
    public void setOpTimeout(int timeout) { this.opTimeout = timeout; }
}
