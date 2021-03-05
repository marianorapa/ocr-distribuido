package com.mrapaport.unlu.sdypp.joiner.input;

import com.mrapaport.unlu.sdypp.joiner.core.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class InputConfig {

    @Autowired
    Joiner joiner;

    @Bean
    public Consumer<String> processedImages(){
        return image -> joiner.join(image);
    }

}
