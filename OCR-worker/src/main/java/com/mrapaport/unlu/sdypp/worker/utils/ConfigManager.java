package com.mrapaport.unlu.sdypp.worker.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
@PropertySource("application.properties")
@Getter
public class ConfigManager {

    @Value("${broker.tasks.incoming}")
    private String inputExchange;
    @Value("${broker.tasks.outgoing}")
    private String outputExchange;

    @Value("${broker.url}")
    private String brokerHost;
    @Value("${broker.username}")
    private String brokerUsername;
    @Value("${broker.password}")
    private String brokerPassword;

    @Value("${tesseract.data.path}")
    private String tesseractDataPath;
}