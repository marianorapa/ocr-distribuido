package com.mrapaport.unlu.sdypp.splitter.messaging;

import com.mrapaport.unlu.sdypp.splitter.entities.SerializedTask;
import com.mrapaport.unlu.sdypp.splitter.entities.Task;

public interface MessageBroker {

    void sendTask(SerializedTask task);

}
