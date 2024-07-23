package com.example.asyncdemoapplication.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AsyncController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @GetMapping("/async")
    public CompletableFuture<String> getValueAsyncUsingCompletableFuture() {
        logger.info("Request received");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::processRequest);
        logger.info("Servlet thread released");
        return completableFuture;
    }

    private String processRequest() {
        Long delayTime = 10000L;
        logger.info("Start processing request");
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Completed processing request");
        return "Processing done after " + delayTime + " ms";
    }
}
