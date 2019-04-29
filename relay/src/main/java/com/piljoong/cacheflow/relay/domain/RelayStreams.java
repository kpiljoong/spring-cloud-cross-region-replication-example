package com.piljoong.cacheflow.relay.domain;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RelayStreams {
    String CVBROKER = "cvbroker";

    @Input(CVBROKER)
    SubscribableChannel cvbroker();
}