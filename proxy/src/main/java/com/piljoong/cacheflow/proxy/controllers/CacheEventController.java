package com.piljoong.cacheflow.proxy.controllers;

import com.piljoong.cacheflow.proxy.domain.CacheEvent;
import com.piljoong.cacheflow.proxy.domain.ResponseMessage;
import com.piljoong.cacheflow.proxy.utils.SizedStack;
import com.piljoong.cacheflow.proxy.cache.XMemcachedManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Stack;


@RestController
public class CacheEventController {
    private static final Logger logger = LoggerFactory.getLogger(CacheEventController.class);

    @Autowired
    private XMemcachedManager memcachedManager;

    private SizedStack<CacheEvent> events = new SizedStack<CacheEvent>(10);

    @CrossOrigin(origins = "*")
    @GetMapping(path="/recent")
    private ResponseEntity<Stack<CacheEvent>> fetchRecentUpdatedCacheEvent() {
        return new ResponseEntity<>(this.events, HttpStatus.OK) ;
    }

    @PostMapping(path="/event", consumes="application/json", produces="application/json")
    public ResponseMessage handleEvent(@RequestBody CacheEvent cv) {
        logger.info("CacheEvent received: {}", cv);
        logger.info("{}/{}", cv.getId(), cv.getValue());

        events.push(cv);

        // Appending "_NEW" is intended for local demo
        memcachedManager.set(cv.getId()+"_NEW", cv.getValue());

        return new ResponseMessage(0, "ACK");
    }
}
