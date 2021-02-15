package com.mrapaport.unlu.sdypp;

import com.google.gson.Gson;
import com.mrapaport.unlu.sdypp.broker.MessageBroker;
import com.mrapaport.unlu.sdypp.utils.ConfigManager;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.DeliverCallback;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

/**
 * Main component. Contains logic related to the OCR process.
 */
@Component
public class OCRWorker implements Runnable {

    private final UUID token = UUID.randomUUID();

    private final Logger logger = LoggerFactory.getLogger(OCRWorker.class);

    @Autowired
    private ConfigManager configManager;

    private Tesseract tesseract;

    @Autowired
    MessageBroker messageBroker;

    public void run() {
        setupTesseract();
        registerWithBroker();
        logger.info("Worker configured ok...");
    }

    private void registerWithBroker() {
        try {
            messageBroker.basicConsume(configManager.getInputQueue(), false, 1, false,
                    callbackFunc, cancelCallback);
        } catch (IOException e) {
            logger.error("Error trying to consume message from queue {} - {}", configManager.getInputQueue(), e.getMessage());
            e.printStackTrace();
        }
    }

    private void setupTesseract() {
        tesseract = new Tesseract();
        tesseract.setLanguage("spa");
        tesseract.setDatapath(configManager.getTesseractDataPath());
    }

    private CancelCallback cancelCallback = consumerTag -> {};


    private DeliverCallback callbackFunc = (consumerTag, delivery) -> {

        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        Gson gson = new Gson();

        Task task = gson.fromJson(message, Task.class);

        logger.info("Task {} received.", task.getUUID());

        if (task.getTtl().isBefore(LocalDateTime.now())) {
            messageBroker.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        } else {
            solveTask(task);

            boolean res = queueSolvedTaskInServer(task);
            if (res)
                messageBroker.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            else
                messageBroker.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
        }

        logger.info("Task {} solved.", task.getUUID());
    };


    private void solveTask(Task task) {

        try {
            File file = File.createTempFile("task", task.getOriginalFilename());

            FileOutputStream outputStream = new FileOutputStream(file);

            outputStream.write(Base64.getDecoder().decode(task.getImageEncoded()));
            outputStream.flush();
            outputStream.close();

            String text = tesseract.doOCR(file);

            task.setImageText(text);

        } catch (TesseractException e) {
            logger.error("Error: Tesseract exception probably due to missing train data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean queueSolvedTaskInServer(Task task) {
        Gson gson = new Gson();
        String taskString = gson.toJson(task);
        try {
            messageBroker.basicPublish(configManager.getOutputExchange(), "", null, taskString.getBytes());
            logger.info("Task published to exchange");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
