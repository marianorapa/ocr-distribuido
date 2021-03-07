package com.mrapaport.unlu.sdypp.joiner.dto;

import lombok.Data;

@Data
public class JobStatusDto {

    String jobId;

    double status;

    private JobStatusDto(String jobId, double status) {
        this.jobId = jobId;
        this.status = status;
    }

    public static JobStatusDto from(String rawJobId, double jobStatus) {
        return new JobStatusDto(rawJobId, jobStatus);
    }
}
