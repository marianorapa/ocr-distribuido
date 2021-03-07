package com.mrapaport.unlu.sdypp.shared.dtos;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.UUID;

public class SerializedTaskDto implements Serializable {

    String jobId;

    String taskNumber;

    String encodedFile;

    String filename;

    public static SerializedTaskDto from(UUID jobId, int taskNumber, byte[] file, String filename) throws IOException {
        SerializedTaskDto task = new SerializedTaskDto();
        task.jobId = jobId.toString();
        task.taskNumber = Integer.toString(taskNumber);
        task.encodedFile = Base64.getEncoder().encodeToString(file);
        task.filename = filename;
        return task;
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

    public String getEncodedFile() {
        return encodedFile;
    }

    public void setEncodedFile(String encodedFile) {
        this.encodedFile = encodedFile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}