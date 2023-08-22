package com.sourav.app.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(value = {"com.sourav.app.rest.config",
        "com.sourav.app.rest.reader",
        "com.sourav.app.rest.model",
        "com.sourav.app.rest.service",
        "com.sourav.app.rest.writer"})
@EnableAsync
public class SpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
}
