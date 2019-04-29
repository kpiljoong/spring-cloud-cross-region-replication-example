package com.piljoong.cacheflow.producer.cache;

import net.rubyeye.xmemcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;

public class XMemcachedManager implements ICacheManager {
    private static final Logger logger = LoggerFactory.getLogger(XMemcachedManager.class);

    private MemcachedClient client;

    public XMemcachedManager(MemcachedClient client) {
        this.client = client;
    }

    @PreDestroy
    private void closeClient() {
        if (client != null) {
            try {
                if (!client.isShutdown()) {
                    client.shutdown();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public String get(String key) {
        String res = null;
        try {
            res = client.get(key);
        } catch (Exception e) {
            logger.error("[Memcached] Get Error: key={}, e={}", key, e.getMessage());
        }
        return res;
    }

    @Override
    public void set(String key, String value) {
        try {
            client.set(key, 0, value);
        } catch (Exception e) {
            logger.error("[Memcached] Set Error: key={}, value={}, e={}", key, value, e.getMessage());
        }
    }
}
