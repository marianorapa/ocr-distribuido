package com.mrapaport.unlu.sdypp.joiner.controller.rest;

import com.mrapaport.unlu.sdypp.joiner.dto.JobResultDto;
import com.mrapaport.unlu.sdypp.joiner.dto.JobStatusDto;
import com.mrapaport.unlu.sdypp.joiner.exceptions.JobIdException;
import com.mrapaport.unlu.sdypp.joiner.service.JobService;
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

    @GetMapping("/job/:jobId/status")
    public ResponseEntity<Object> jobStatus(@PathVariable String jobId){
        try {
            JobStatusDto status = jobService.getJobStatus(jobId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (JobIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/job/:jobId/result")
    public ResponseEntity<Object> jobResult(@PathVariable String jobId){
        try {
            JobResultDto result = jobService.getJobResult(jobId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (JobIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}