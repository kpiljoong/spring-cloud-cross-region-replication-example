package com.piljoong.cacheflow.relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RelayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelayApplication.class, args);
    }

}
