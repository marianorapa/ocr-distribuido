package com.mrapaport.unlu.sdypp.joiner.core.joiner.impl;

import com.mrapaport.unlu.sdypp.joiner.core.joiner.Joiner;
import com.mrapaport.unlu.sdypp.joiner.core.store.KeyValueStore;
import com.mrapaport.unlu.sdypp.joiner.dto.ImageDataDto;
import com.mrapaport.unlu.sdypp.shared.dtos.SolvedTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class JoinerImpl implements Joiner {

    @Autowired
    KeyValueStore keyValueStore;

    @Override
    public void join(SolvedTaskDto imageData) {
        String key = buildKey(imageData.getJobId(), imageData.getTaskNumber());
        keyValueStore.save(key, imageData.getImageText());
    }

    private String buildKey(String jobId, String taskNumber) {
        return jobId + "-" + taskNumber;
    }

    @Override
    public double getJobStatus(String jobId) {

        /*
            Get the amt of tasks expected and verify which ones already exist in the key value store.
         */
        AtomicInteger doneTasks = new AtomicInteger();

        List<String> jobTasksIds = getJobTasksIds(jobId);
        jobTasksIds.forEach(taskId -> {
            if (keyValueStore.exists(taskId)) {
                doneTasks.getAndIncrement();
            }
        });

        return (double) doneTasks.get() / jobTasksIds.size();
    }

    @Override
    public List<ImageDataDto> getJobResult(String jobId) {
        List<ImageDataDto> result = new LinkedList<>();

        List<String> jobTasksIds = getJobTasksIds(jobId);
        jobTasksIds.forEach(taskId -> {
            String imageData = keyValueStore.get(taskId);
            if (Objects.nonNull(imageData))
                result.add(ImageDataDto.from(imageData));
        });

        return result;
    }


    public List<String> getJobTasksIds(String jobId) {
        List<String> result = new LinkedList<>();

        double totalTasks = Integer.parseInt(keyValueStore.get(jobId));

        for (int taskNumber = 1; taskNumber <= totalTasks; taskNumber++) {
            result.add(buildKey(jobId, Integer.toString(taskNumber)));
        }

        return result;
    }
}
