package com.piljoong.cacheflow.producer.controllers;

import com.piljoong.cacheflow.producer.services.ProducerService;
import com.piljoong.cacheflow.producer.domain.ResponseMessage;
import com.piljoong.cacheflow.producer.utils.SizedStack;
import com.piljoong.cacheflow.producer.utils.Utils;
import com.piljoong.cacheflow.producer.domain.CacheEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Stack;

@RestController
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    ProducerService producerService;

    private SizedStack<CacheEvent> events = new SizedStack<CacheEvent>(10);

    @CrossOrigin(origins = "*")
    @PostMapping("/set")
    public ResponseMessage set() {
        CacheEvent ce = CacheEvent.generate();
        ce.setId(Utils.currentDateTime());
        logger.info("CacheEvent... {}/{}", ce.getId(), ce.getValue());

        // Put data into cache
        events.push(ce);

        this.producerService.setData(ce);
        // Put a message into message bus
        this.producerService.send(ce);

        return new ResponseMessage(0, ce.getId());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/put")
    public ResponseMessage put(@RequestBody Map<String, String> payload) {
        CacheEvent ce = CacheEvent.generate();
        ce.setValue(payload.get("value"));
        ce.setId(Utils.currentDateTime());
        logger.info("CacheEvent... {}/{}", ce.getId(), ce.getValue());

        // Put data into cache
        events.push(ce);

        // Put data into cache
        this.producerService.setData(ce);
        // Put a message into message bus
        this.producerService.send(ce);

        return new ResponseMessage(0, ce.getId());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/recent")
    private ResponseEntity<Stack<CacheEvent>> fetchRecentUpdatedCacheEvent() {
        logger.info("==== size " + events.size());
        logger.info("==== " + events);
        return new ResponseEntity<>(this.events, HttpStatus.OK) ;
    }
}
