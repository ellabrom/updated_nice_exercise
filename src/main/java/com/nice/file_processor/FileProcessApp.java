package com.nice.file_processor;

import com.nice.file_processor.controllers.FlowController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileProcessApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FileProcessApp.class);
        context.getBean(FlowController.class).controlFlow();

    }
}