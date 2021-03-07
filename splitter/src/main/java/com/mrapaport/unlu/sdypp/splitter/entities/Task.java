package com.mrapaport.unlu.sdypp.splitter.entities;

import com.mrapaport.unlu.sdypp.shared.dtos.SerializedTaskDto;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Getter
public class Task {

   private UUID jobId;

   private int taskNumber;

   private MultipartFile file;

    public static Task from(UUID jobId, int taskNumber, MultipartFile file) {
        Task t = new Task();
        t.jobId = jobId;
        t.taskNumber = taskNumber;
        t.file = file;
        return t;
    }

    public SerializedTaskDto serialize() throws IOException {
        return SerializedTaskDto.from(jobId, taskNumber, file.getBytes(), file.getOriginalFilename());
    }

    public String getUniqueId() {
        return String.format("%s - %d", jobId, taskNumber);
    }
}