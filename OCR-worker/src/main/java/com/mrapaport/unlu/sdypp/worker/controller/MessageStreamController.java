package com.mrapaport.unlu.sdypp.worker.controller;

import com.mrapaport.unlu.sdypp.worker.dto.PendingTaskDto;
import com.mrapaport.unlu.sdypp.worker.service.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageStreamController {

    @Autowired
    OcrService service;

    @Bean
    public Function<String, String> pendingTasks() {
        return pendingTask -> service.applyOcr(PendingTaskDto.from(pendingTask)).toString();
    }

}
