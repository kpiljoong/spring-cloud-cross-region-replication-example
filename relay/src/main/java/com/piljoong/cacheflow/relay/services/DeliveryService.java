package com.piljoong.cacheflow.relay.services;

import com.piljoong.cacheflow.relay.domain.CacheEvent;
import java.util.concurrent.CompletableFuture;

public interface DeliveryService {

    CompletableFuture<String> delivery(final CacheEvent cv) throws InterruptedException;

}