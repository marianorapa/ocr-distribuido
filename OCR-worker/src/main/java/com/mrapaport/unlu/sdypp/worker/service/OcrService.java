package com.mrapaport.unlu.sdypp.worker.service;

import com.mrapaport.unlu.sdypp.worker.dto.PendingTaskDto;
import com.mrapaport.unlu.sdypp.worker.dto.SolvedTaskDto;

public interface OcrService {

    SolvedTaskDto applyOcr(PendingTaskDto from);

}