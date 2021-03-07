package com.mrapaport.unlu.sdypp.joiner.service;

import com.mrapaport.unlu.sdypp.joiner.dto.JobResultDto;
import com.mrapaport.unlu.sdypp.joiner.dto.JobStatusDto;
import com.mrapaport.unlu.sdypp.joiner.exceptions.JobIdException;

public interface JobService {
    JobStatusDto getJobStatus(String jobId) throws JobIdException;

    JobResultDto getJobResult(String jobId) throws JobIdException;
}
