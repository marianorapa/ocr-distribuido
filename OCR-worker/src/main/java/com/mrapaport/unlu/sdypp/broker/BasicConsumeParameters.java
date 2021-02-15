package com.mrapaport.unlu.sdypp.broker;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.DeliverCallback;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasicConsumeParameters {
   private String queue;
   private boolean autoAck;
   private int prefetchCount;
   private boolean global;
   private DeliverCallback callbackFunc;
   private CancelCallback cancelCallback;
}
