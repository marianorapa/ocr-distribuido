package com.mrapaport.unlu.sdypp.joiner.controller.rest;

import com.mrapaport.unlu.sdypp.joiner.dto.JobResultDto;
import com.mrapaport.unlu.sdypp.joiner.dto.JobStatusDto;
import com.mrapaport.unlu.sdypp.joiner.exceptions.JobIdException;
import com.mrapaport.unlu.sdypp.joiner.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestController {

    @Autowired
    JobService jobService;

    private Logger logger = LoggerFactory.getLogger(JobRestController.class);

    @GetMapping("/job/{jobId}/status")
    public ResponseEntity<Object> jobStatus(@PathVariable String jobId){
        try {
            JobStatusDto status = jobService.getJobStatus(jobId);
            logger.info("Job status request for {} returning {}", jobId, status.getStatus());
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (JobIdException e) {
            logger.info("Job status request for {}, returning {}", jobId, e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.info("Job status request for {}, returning {}", jobId, e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/job/{jobId}/result")
    public ResponseEntity<Object> jobResult(@PathVariable String jobId){
        try {
            logger.info("Job status request for {}", jobId);
            JobResultDto result = jobService.getJobResult(jobId);
            logger.info("Job status request for {} returning", jobId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (JobIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}