package com.zeluciojr;

import com.zeluciojr.cae_framework_example.assemblers.loggers.LoggerBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        LoggerBootstrap.startupLoggerSettings();
        SpringApplication.run(Main.class);
    }
}