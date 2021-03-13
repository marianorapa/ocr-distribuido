package com.mrapaport.unlu.sdypp.worker.broker;

import com.mrapaport.unlu.sdypp.worker.utils.ConfigManager;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

/**
 * Encapsulates logic related to the message broker: channels, queues, connections, etc.
 */
//@Component
public class MessageBroker {

    private final Logger logger = LoggerFactory.getLogger(MessageBroker.class);

    private ConfigManager configManager;

    private Channel channel;

    private Connection connection;

    /**
     * How long it will wait until next attempt to establish connection.
     */
    private long delay;

    private Queue<BasicConsumeParameters> pendingSubscriptions;

    public MessageBroker(ConfigManager configManager){
        this.configManager = configManager;
        this.pendingSubscriptions = new ArrayDeque<>();
        initializeBroker();
    }

    /**
     * Tries to initialize this component. If it doesn't succeed, retries with an increasing delay
     */
    private void initializeBroker() {
        try {
            this.setupBrokerConnection();
            this.setupPendingSubscriptions();
        } catch (IOException | TimeoutException e) {
            logger.error("Error trying to setup broker connection. Worker won't start until connection is ready - {}",
                    e.getMessage());
            this.retryConnection();
        }
    }

    /**
     * Consumes each pending requests that couldn't be fulfilled before due to the missing broker channel or connection.
     */
    private void setupPendingSubscriptions() {
        pendingSubscriptions.forEach(params -> {
            try {
                this.basicConsume(params.getQueue(), params.isAutoAck(), params.getPrefetchCount(), params.isGlobal(),
                        params.getCallbackFunc(), params.getCancelCallback());
            } catch (IOException e) {
                logger.error("Error trying to subscribe to queue {}", params.getQueue());
            }
        });
    }

    private void retryConnection() {
        Timer t = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                initializeBroker();
            }
        };

        long nextDelay = calculateDelay();
        logger.info("Retrying connection in {} ms", nextDelay);
        t.schedule(task, nextDelay);
    }

    private long calculateDelay() {
        if (delay == 0) {
            delay = 500;
            return delay;
        }

        delay = delay * 2;
        return delay;
    }

    /**
     * Attempts to connect to the message broker with the parameters obtained from the configManager
     * @throws IOException
     * @throws TimeoutException
     */
    private void setupBrokerConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(configManager.getBrokerHost());
        factory.setUsername(configManager.getBrokerUsername());
        factory.setPassword(configManager.getBrokerPassword());

        connection = factory.newConnection();
        logger.info("Message broker connection established.");

        channel = connection.createChannel();
    }

    public void basicConsume(String exchange, boolean autoAck, int prefetchCount, boolean global,
                             DeliverCallback callbackFunc, CancelCallback cancelCallback) throws IOException {
        if (channel != null) {
            channel.basicQos(prefetchCount, global);
            AMQP.Queue.DeclareOk declareOk = channel.queueDeclare();
            String queue = declareOk.getQueue();
            channel.queueBind(queue, exchange, "*");
            channel.basicConsume(queue, autoAck, callbackFunc, cancelCallback);
        }
        else {
            preserveSubscriptionUntilChannelIsReady(exchange, autoAck, prefetchCount, global, callbackFunc, cancelCallback);
        }
    }

    private void preserveSubscriptionUntilChannelIsReady(String queue, boolean autoAck, int prefetchCount,
                                                         boolean global, DeliverCallback callbackFunc,
                                                         CancelCallback cancelCallback)
    {
        this.pendingSubscriptions.add(new BasicConsumeParameters(queue, autoAck, prefetchCount, global, callbackFunc, cancelCallback));
    }

    public void basicAck(long deliveryTag, boolean multiple) throws IOException {
        channel.basicAck(deliveryTag, multiple);
    }

    public void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException {
        channel.basicNack(deliveryTag, multiple, requeue);
    }

    public void basicPublish(String outputExchange, String s, AMQP.BasicProperties properties, byte[] bytes) throws IOException {
        channel.basicPublish(outputExchange, s, properties, bytes);
    }
}
