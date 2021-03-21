package com.mrapaport.unlu.sdypp.joiner.controller.stream;

import com.mrapaport.unlu.sdypp.joiner.exceptions.JobIdException;
import com.mrapaport.unlu.sdypp.joiner.service.JobService;
import com.mrapaport.unlu.sdypp.shared.dtos.JobCreatedDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class JobStreamController {

    @Autowired
    private JobService service;

    Logger logger = LoggerFactory.getLogger(JobStreamController.class);

    @Bean
    public Consumer<JobCreatedDto> jobCreated() {
        return jobId -> {
            try {
                logger.info("Job created {}", jobId);
                service.saveJob(jobId);
            } catch (JobIdException e) {
                e.printStackTrace();
            }
        };
    }


}
