package com.mrapaport.unlu.sdypp.joiner.service;

import com.mrapaport.unlu.sdypp.joiner.exceptions.JobIdException;

public interface JobIdVerifier {

    /**
     * Verifies a raw job id. If it's ok, returns the job id to access the job.
     * @param jobId
     * @return The job id to be used for accessing the actual job.
     * @throws JobIdException if there's something wrong with the given job id.
     */
    String verify(String jobId) throws JobIdException;

}
