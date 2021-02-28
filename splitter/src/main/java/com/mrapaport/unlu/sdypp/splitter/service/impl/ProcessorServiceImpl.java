package com.mrapaport.unlu.sdypp.splitter.service.impl;

import com.mrapaport.unlu.sdypp.splitter.entities.Task;
import com.mrapaport.unlu.sdypp.splitter.messaging.MessageBroker;
import com.mrapaport.unlu.sdypp.splitter.service.ProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProcessorServiceImpl implements ProcessorService {

    @Autowired
    MessageBroker messageBroker;

    Logger logger = LoggerFactory.getLogger(ProcessorServiceImpl.class);

    @Override
    public UUID processImages(List<MultipartFile> files) throws IllegalArgumentException{

        if (files.isEmpty()) {
            throw new IllegalArgumentException("The list must contain at least one file");
        }

        UUID uuid = generateUUID();

        AtomicInteger taskId = new AtomicInteger(0);
        files.forEach(file -> {
            Task task = Task.from(uuid, taskId.getAndIncrement(), file);
            try {
                messageBroker.sendTask(task.serialize());
            } catch (IOException e) {
                logger.error("There was an error trying to serialize Task {} - {}", task.getUniqueId(), e.getMessage());
                e.printStackTrace();
            }
        });

        return uuid;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}