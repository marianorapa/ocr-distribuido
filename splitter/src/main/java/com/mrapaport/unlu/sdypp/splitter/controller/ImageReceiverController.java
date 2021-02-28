package com.mrapaport.unlu.sdypp.splitter.controller;

import com.mrapaport.unlu.sdypp.splitter.dto.ImagesReceivedResponseDTO;
import com.mrapaport.unlu.sdypp.splitter.service.JWTProvider;
import com.mrapaport.unlu.sdypp.splitter.service.ProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@PropertySource("classpath:application.properties")
//@CrossOrigin(origins = {"http://web-ui-service", "http://localhost:3000"})
@CrossOrigin(origins = "*")
public class ImageReceiverController {

    @Autowired
    ProcessorService processorService;

    @Autowired
    JWTProvider jwtProvider;

    Logger logger = LoggerFactory.getLogger(ImageReceiverController.class);

    @PostMapping("/process-images")
    public ResponseEntity<ImagesReceivedResponseDTO> receiveImages(@RequestPart("images") List<MultipartFile> images)
            throws IOException, ServletException {

        logger.info("Process images request: {}", images);

        UUID uuid = processorService.processImages(images);

        String token = jwtProvider.createToken(Map.of("jobId", uuid.toString()));

        return new ResponseEntity<>(ImagesReceivedResponseDTO.from(token), HttpStatus.OK);
    }
}