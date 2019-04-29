package com.piljoong.cacheflow.relay.services;

import com.piljoong.cacheflow.relay.config.RelayProperties;
import com.piljoong.cacheflow.relay.domain.CacheEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    private RelayProperties config;

    private final RestTemplate restTemplate;

    public DeliveryServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    @Async
    public CompletableFuture<String> delivery(final CacheEvent cv) throws InterruptedException {
        logger.info("Deliverying... {} to {}", cv, config.getTarget());

        String results = restTemplate.postForObject(config.getTarget(), cv, String.class);
        return CompletableFuture.completedFuture(results);
    }
}
