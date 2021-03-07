package com.mrapaport.unlu.sdypp.splitter.controller.stream;

import com.mrapaport.unlu.sdypp.shared.dtos.SerializedTaskDto;
import com.mrapaport.unlu.sdypp.splitter.entities.SerializedTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

@Component
@PropertySource("classpath:application.properties")
public class PendingTasksStreamController{

    @Autowired
    private StreamBridge streamBridge;

    public void pendingTasks(SerializedTaskDto task) {
        streamBridge.send("pending-tasks", task);
    }
}