package com.mrapaport.unlu.sdypp.worker.service.tesseract;

import com.mrapaport.unlu.sdypp.shared.dtos.SerializedTaskDto;
import com.mrapaport.unlu.sdypp.shared.dtos.SolvedTaskDto;
import com.mrapaport.unlu.sdypp.worker.service.OcrService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.Locale;

@Service
@PropertySource("application.properties")
public class TesseractOcrService implements OcrService {

    private final Logger logger = LoggerFactory.getLogger(TesseractOcrService.class);

    private Tesseract tesseract;

    @Value("${app.env}")
    private String appEnv;

    @PostConstruct
    private void init() {
        setupTesseract();
    }

    private void setupTesseract() {
        tesseract = new Tesseract();
        tesseract.setLanguage("spa");

        logger.info("Tesseract initialised");
    }

    @Override
    public SolvedTaskDto applyOcr(SerializedTaskDto pendingTask) throws TesseractException, IOException {
        String ocrResult = this.applyOcr(pendingTask.getEncodedFile(), pendingTask.getFilename());
        return SolvedTaskDto.from(pendingTask.getJobId(), pendingTask.getTaskNumber(), pendingTask.getFilename(), ocrResult);
    }

    private String applyOcr(String encodedImage, String filename) throws TesseractException, IOException {

        try {
            File file = File.createTempFile(Instant.now().toString(), filename);

            FileOutputStream outputStream = new FileOutputStream(file);

            outputStream.write(Base64.getDecoder().decode(encodedImage));
            outputStream.flush();
            outputStream.close();

            return tesseract.doOCR(file);

        } catch (TesseractException e) {
            logger.error("Error: Tesseract exception probably due to missing training data");
            throw e;
        } catch (Exception e) {
            logger.error("Fatal error - {}", e.getMessage());
            throw e;
        }
    }
}