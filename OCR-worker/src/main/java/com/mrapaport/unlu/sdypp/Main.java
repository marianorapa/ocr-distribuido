package com.mrapaport.unlu.sdypp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Main.class);

    @Autowired
    private OCRWorker OCRWorker;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            OCRWorker.run();
        }
        catch (Exception e) {
            logger.error("There was an exception trying to start Worker - {}", e.getMessage());
            e.printStackTrace();
        }
    }
}