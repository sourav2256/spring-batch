package com.sourav.app.batchMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(value = {"com.sourav.app.json.config",
        "com.sourav.app.json.reader",
        "com.sourav.app.json.model",
        "com.sourav.app.json.processor",
        "com.sourav.app.rest.service",
        "com.sourav.app.listener",
        "com.sourav.app.json.writer"})
@EnableAsync
public class SpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
}
