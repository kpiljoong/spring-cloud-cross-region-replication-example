package com.piljoong.cacheflow.relay.listeners;

import com.piljoong.cacheflow.relay.services.DeliveryService;
import com.piljoong.cacheflow.relay.services.DeliveryServiceImpl;
import com.piljoong.cacheflow.relay.domain.CacheEvent;
import com.piljoong.cacheflow.relay.domain.RelayStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;

@EnableBinding(RelayStreams.class)
public class CacheEventListener {
    private static final Logger logger = LoggerFactory.getLogger(CacheEventListener.class);

    @Autowired
    private final DeliveryService deliveryService;

    public CacheEventListener(DeliveryServiceImpl deliveryService) {
        this.deliveryService = deliveryService;
    }

    @StreamListener(RelayStreams.CVBROKER)
    @Async
    public void handleMessage(@Payload CacheEvent ce) {
        logger.info("Received {} : {}", ce.getId(), ce.getValue());
        try {
            this.deliveryService.delivery(ce);
        } catch (InterruptedException e) {
            logger.error("ERR {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
