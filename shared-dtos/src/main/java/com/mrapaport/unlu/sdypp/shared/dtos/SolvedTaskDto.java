package com.mrapaport.unlu.sdypp.shared.dtos;

public class SolvedTaskDto {

    private String jobId;

    private String taskNumber;

    private String imageText;

    public SolvedTaskDto(String jobId, String taskNumber, String imageText) {
        this.jobId = jobId;
        this.taskNumber = taskNumber;
        this.imageText = imageText;
    }

    public static SolvedTaskDto from(String jobId, String taskNumber, String ocrResult) {
        return new SolvedTaskDto(jobId, taskNumber, ocrResult);
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

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }
}