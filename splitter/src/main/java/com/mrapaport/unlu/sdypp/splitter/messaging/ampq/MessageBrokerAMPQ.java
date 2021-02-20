package com.mrapaport.unlu.sdypp.splitter.messaging.ampq;

import com.mrapaport.unlu.sdypp.splitter.entities.SerializedTask;
import com.mrapaport.unlu.sdypp.splitter.entities.Task;
import com.mrapaport.unlu.sdypp.splitter.messaging.MessageBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class MessageBrokerAMPQ implements MessageBroker {

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public void sendTask(SerializedTask task) {
        streamBridge.send("pending_tasks", task);
    }
}
