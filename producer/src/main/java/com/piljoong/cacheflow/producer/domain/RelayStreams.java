package com.piljoong.cacheflow.producer.domain;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RelayStreams {
    String CVBROKER = "cvbroker";

    @Output(CVBROKER)
    MessageChannel cvbroker();
}