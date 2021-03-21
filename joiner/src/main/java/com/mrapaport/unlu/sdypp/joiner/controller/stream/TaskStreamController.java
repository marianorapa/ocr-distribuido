package com.mrapaport.unlu.sdypp.joiner.controller.stream;

import com.mrapaport.unlu.sdypp.joiner.core.joiner.Joiner;
import com.mrapaport.unlu.sdypp.joiner.dto.ImageDataDto;
import com.mrapaport.unlu.sdypp.shared.dtos.SolvedTaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.GenericMessage;

import java.util.function.Consumer;

@Configuration
public class TaskStreamController {

    @Autowired
    Joiner joiner;
    private Logger logger = LoggerFactory.getLogger(TaskStreamController.class);

    /**
     * On message received from broker, calls the Joiner to process result.
     * @return
     */
    @Bean
    public Consumer<SolvedTaskDto> processedImages(){
        return solvedTask -> {
            joiner.join(solvedTask);
            logger.info("Image {}-{} joined correctly.", solvedTask.getJobId(), solvedTask.getTaskNumber());
        };
    }

}