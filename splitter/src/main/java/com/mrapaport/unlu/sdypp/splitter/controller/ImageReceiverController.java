package com.mrapaport.unlu.sdypp.splitter.controller;

import com.mrapaport.unlu.sdypp.splitter.dto.ImagesReceivedResponseDTO;
import com.mrapaport.unlu.sdypp.splitter.service.JWTProvider;
import com.mrapaport.unlu.sdypp.splitter.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class ImageReceiverController {

    @Autowired
    ProcessorService processorService;

    @Autowired
    JWTProvider jwtProvider;

    @PostMapping("/process-images")
    public ResponseEntity<ImagesReceivedResponseDTO> receiveImages(@RequestParam List<MultipartFile> images)
            throws IOException, ServletException {

        UUID uuid = processorService.processImages(images);

        String token = jwtProvider.createToken(Map.of("jobId", uuid.toString()));

        return new ResponseEntity<>(ImagesReceivedResponseDTO.from(token), HttpStatus.OK);
    }
}
