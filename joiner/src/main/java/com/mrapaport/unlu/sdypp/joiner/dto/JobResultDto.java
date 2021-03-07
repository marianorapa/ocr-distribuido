package com.mrapaport.unlu.sdypp.joiner.dto;

import lombok.Data;

import java.util.List;

@Data
public class JobResultDto {

    String jobId;

    List<ImageDataDto> images;

    private JobResultDto(String jobId, List<ImageDataDto> images) {
        this.jobId = jobId;
        this.images = images;
    }

    public static JobResultDto from(String rawJobId, List<ImageDataDto> jobResult) {
        return new JobResultDto(rawJobId, jobResult);
    }
}
