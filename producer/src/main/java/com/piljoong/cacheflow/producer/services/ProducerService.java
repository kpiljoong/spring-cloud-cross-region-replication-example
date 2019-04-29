package com.piljoong.cacheflow.producer.services;

import com.piljoong.cacheflow.producer.domain.RelayStreams;
import com.piljoong.cacheflow.producer.cache.XMemcachedManager;
import com.piljoong.cacheflow.producer.domain.CacheEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Random;


@EnableBinding(RelayStreams.class)
public class ProducerService {
    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final String[] tags = new String[] {
            "sb1", "sb2", "sb3",
            "sb4", "sb5", "sb6"
    };

    @Autowired
    private RelayStreams relayStreams;

    @Autowired
    private XMemcachedManager memcachedManager;

    @SendTo(RelayStreams.CVBROKER)
    public void send(CacheEvent ce) {
        logger.info("Sending: {}", ce);
        String key = tags[RANDOM.nextInt(tags.length)];

        relayStreams.cvbroker().send(MessageBuilder.withPayload(ce).setHeader("partitionKey", key).build());
    }

    public void setData(CacheEvent ce) {
        logger.info("Cached: {}/{}", ce.getId(), ce.getValue());
        memcachedManager.set(ce.getId(), ce.getValue());
    }
}
