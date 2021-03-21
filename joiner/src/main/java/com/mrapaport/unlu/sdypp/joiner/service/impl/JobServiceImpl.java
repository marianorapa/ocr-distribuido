package com.mrapaport.unlu.sdypp.joiner.service.impl;

import com.mrapaport.unlu.sdypp.joiner.core.joiner.Joiner;
import com.mrapaport.unlu.sdypp.joiner.dto.JobResultDto;
import com.mrapaport.unlu.sdypp.joiner.dto.JobStatusDto;
import com.mrapaport.unlu.sdypp.joiner.exceptions.JobIdException;
import com.mrapaport.unlu.sdypp.joiner.service.JobIdVerifier;
import com.mrapaport.unlu.sdypp.joiner.service.JobService;
import com.mrapaport.unlu.sdypp.shared.dtos.JobCreatedDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobIdVerifier verifier;

    @Autowired
    Joiner joiner;

    private Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @PostConstruct
    public void init(){
        logger.info("Job service init");
    }

    @Override
    public void saveJob(JobCreatedDto jobCreatedDto) throws JobIdException {
        joiner.saveJob(jobCreatedDto.getRawJobId(), jobCreatedDto.getAmtOfTasks());
    }

    @Override
    public JobStatusDto getJobStatus(String rawJobId) throws JobIdException {
        String jobId = verifier.verify(rawJobId);
        return JobStatusDto.from(rawJobId, joiner.getJobStatus(jobId));
    }

    @Override
    public JobResultDto getJobResult(String rawJobId) throws JobIdException {
        String jobId = verifier.verify(rawJobId);
        return JobResultDto.from(rawJobId, joiner.getJobResult(jobId));
    }
}