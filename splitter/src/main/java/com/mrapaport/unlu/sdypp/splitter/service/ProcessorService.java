package com.mrapaport.unlu.sdypp.splitter.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProcessorService {

    UUID processImages(List<MultipartFile> files);

}
