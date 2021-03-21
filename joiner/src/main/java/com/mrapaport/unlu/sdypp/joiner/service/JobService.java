package com.mrapaport.unlu.sdypp.joiner.service;

import com.mrapaport.unlu.sdypp.joiner.dto.JobResultDto;
import com.mrapaport.unlu.sdypp.joiner.dto.JobStatusDto;
import com.mrapaport.unlu.sdypp.joiner.exceptions.JobIdException;
import com.mrapaport.unlu.sdypp.shared.dtos.JobCreatedDto;

public interface JobService {
    JobStatusDto getJobStatus(String jobId) throws JobIdException;

    JobResultDto getJobResult(String jobId) throws JobIdException;

    void saveJob(JobCreatedDto job) throws JobIdException;

}
