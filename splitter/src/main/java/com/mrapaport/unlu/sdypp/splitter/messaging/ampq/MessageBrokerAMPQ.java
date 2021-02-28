package com.mrapaport.unlu.sdypp.splitter.messaging.ampq;

import com.mrapaport.unlu.sdypp.splitter.entities.SerializedTask;
import com.mrapaport.unlu.sdypp.splitter.messaging.MessageBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class MessageBrokerAMPQ implements MessageBroker {

    @Autowired
    private StreamBridge streamBridge;

    @Value("${spring.cloud.stream.bindings.pending_tasks.destination}")
    private String outputBindingName;

    @Override
    public void sendTask(SerializedTask task) {
        streamBridge.send(outputBindingName, task);
    }
}