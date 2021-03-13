package com.mrapaport.unlu.sdypp.worker.dto;

import com.google.gson.Gson;

public class SolvedTaskDto {

    private String jobId;

    private int taskNumber;

    private String imageText;

    public SolvedTaskDto(String jobId, int taskNumber, String imageText) {
        this.jobId = jobId;
        this.taskNumber = taskNumber;
        this.imageText = imageText;
    }

    public static SolvedTaskDto from(String jobId, int taskNumber, String ocrResult) {
        return new SolvedTaskDto(jobId, taskNumber, ocrResult);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
