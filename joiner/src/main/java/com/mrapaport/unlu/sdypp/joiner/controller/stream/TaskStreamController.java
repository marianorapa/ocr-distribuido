package com.mrapaport.unlu.sdypp.joiner.controller.stream;

import com.mrapaport.unlu.sdypp.joiner.core.joiner.Joiner;
import com.mrapaport.unlu.sdypp.joiner.dto.ImageDataDto;
import com.mrapaport.unlu.sdypp.shared.dtos.SolvedTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class TaskStreamController {

    @Autowired
    Joiner joiner;

    /**
     * On message received from broker, calls the Joiner to process result.
     * @return
     */
    @Bean
    public Consumer<SolvedTaskDto> processedImages(){
        return solvedTask -> joiner.join(solvedTask);
    }

}