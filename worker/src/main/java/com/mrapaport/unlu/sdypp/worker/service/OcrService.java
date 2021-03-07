package com.mrapaport.unlu.sdypp.worker.service;

import com.mrapaport.unlu.sdypp.shared.dtos.SerializedTaskDto;
import com.mrapaport.unlu.sdypp.shared.dtos.SolvedTaskDto;
import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;


public interface OcrService {

    SolvedTaskDto applyOcr(SerializedTaskDto from) throws TesseractException, IOException;

}