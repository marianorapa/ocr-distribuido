package com.mrapaport.unlu.sdypp.splitter.controller;

import com.mrapaport.unlu.sdypp.splitter.dto.ImagesReceivedResponseDTO;
import com.mrapaport.unlu.sdypp.splitter.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.UUID;

@RestController
public class ImageReceiverController {

    @Autowired
    ProcessorService processorService;

    @PostMapping("/process-images")
    public ResponseEntity<ImagesReceivedResponseDTO> receiveImages(@RequestBody List<MultipartFile> files) {
        UUID uuid = processorService.processImages(files);

        return new ResponseEntity<>(ImagesReceivedResponseDTO.from(uuid), HttpStatus.OK);
    }
}
