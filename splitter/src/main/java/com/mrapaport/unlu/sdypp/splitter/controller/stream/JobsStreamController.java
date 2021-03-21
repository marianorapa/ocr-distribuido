package com.mrapaport.unlu.sdypp.splitter.controller.stream;

import com.mrapaport.unlu.sdypp.shared.dtos.JobCreatedDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Controller;

@Controller
public class JobsStreamController {

    @Autowired
    private StreamBridge streamBridge;

    Logger logger = LoggerFactory.getLogger(JobsStreamController.class);

    public void newJobCreated(JobCreatedDto job) {
        logger.info("Job created {} with {} tasks", job.getRawJobId(), job.getAmtOfTasks());
        streamBridge.send("job-created", job);
    }
}