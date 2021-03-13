package com.mrapaport.unlu.sdypp.worker.dto;

import lombok.Getter;

@Getter
public class PendingTaskDto {

    private String jobId;

    private int taskNumber;

    private String encodedImage;

    public static PendingTaskDto from(String pendingTask) {
        return null;
    }

}