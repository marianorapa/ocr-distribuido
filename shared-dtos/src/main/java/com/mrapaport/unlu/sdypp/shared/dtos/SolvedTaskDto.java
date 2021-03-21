package com.mrapaport.unlu.sdypp.shared.dtos;

import com.google.gson.Gson;

public class SolvedTaskDto {

    private String jobId;

    private String taskNumber;

    private String filename;

    private String imageText;

    public SolvedTaskDto(String jobId, String taskNumber, String filename, String imageText) {
        this.jobId = jobId;
        this.taskNumber = taskNumber;
        this.filename = filename;
        this.imageText = imageText;
    }

    public static SolvedTaskDto from(String jobId, String taskNumber, String filename, String ocrResult) {
        return new SolvedTaskDto(jobId, taskNumber, filename, ocrResult);
    }

    public SolvedTaskDto() {
    }

    public static SolvedTaskDto fromJson(String json) {
        return new Gson().fromJson(json, SolvedTaskDto.class);
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

}