package com.mrapaport.unlu.sdypp.joiner.core.joiner;

import com.mrapaport.unlu.sdypp.joiner.dto.ImageDataDto;
import com.mrapaport.unlu.sdypp.shared.dtos.SolvedTaskDto;

import java.util.List;

public interface Joiner {

    /**
     * Verifies that this part hasn't already been processed and adds it to the job.
     * @param imageData
     */
    void join(SolvedTaskDto imageData);

    /**
     * Calculates the job status based on pending tasks.
     * @param jobId
     * @return
     */
    double getJobStatus(String jobId);

    /**
     * Gets all parts of a job
     * @param jobId
     * @return A list with each task of the job as an {@link ImageDataDto}
     */
    List<ImageDataDto> getJobResult(String jobId);
}
