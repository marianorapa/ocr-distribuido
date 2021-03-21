package com.mrapaport.unlu.sdypp.joiner.dto;

import com.mrapaport.unlu.sdypp.shared.dtos.SolvedTaskDto;
import lombok.Data;

import java.util.List;

@Data
public class JobResultDto {

    String jobId;

    List<SolvedTaskDto> images;

    private JobResultDto(String jobId, List<SolvedTaskDto> images) {
        this.jobId = jobId;
        this.images = images;
    }

    public static JobResultDto from(String rawJobId, List<SolvedTaskDto> jobResult) {
        return new JobResultDto(rawJobId, jobResult);
    }
}
