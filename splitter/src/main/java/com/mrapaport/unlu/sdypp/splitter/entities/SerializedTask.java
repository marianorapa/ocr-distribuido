package com.mrapaport.unlu.sdypp.splitter.entities;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

public class SerializedTask implements Serializable {

    String jobId;

    String taskNumber;

    byte[] encodedFile;

    public static SerializedTask from(UUID jobId, int taskNumber, MultipartFile file) throws IOException {
        SerializedTask task = new SerializedTask();
        task.jobId = jobId.toString();
        task.taskNumber = Integer.toString(taskNumber);
        task.encodedFile = file.getBytes();
        return task;
    }
}
