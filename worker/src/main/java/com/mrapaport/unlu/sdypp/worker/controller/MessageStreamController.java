package com.mrapaport.unlu.sdypp.worker.controller;

import com.mrapaport.unlu.sdypp.shared.dtos.SerializedTaskDto;
import com.mrapaport.unlu.sdypp.shared.dtos.SolvedTaskDto;
import com.mrapaport.unlu.sdypp.worker.service.OcrService;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.function.Function;

@Configuration
public class MessageStreamController {

    @Autowired
    OcrService service;

    Logger logger = LoggerFactory.getLogger(MessageStreamController.class);

    @Bean
    public Function<SerializedTaskDto, SolvedTaskDto> pendingTasks() {
        return pendingTask -> {
            try {
                return service.applyOcr(pendingTask);
            } catch (TesseractException | IOException e) {
                logger.error("Error trying to apply OCR - {}", e.getMessage());
            }
            return null;
        };
    }
}