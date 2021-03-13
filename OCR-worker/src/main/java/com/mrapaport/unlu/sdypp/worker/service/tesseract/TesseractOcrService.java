package com.mrapaport.unlu.sdypp.worker.service.tesseract;

import com.mrapaport.unlu.sdypp.worker.dto.PendingTaskDto;
import com.mrapaport.unlu.sdypp.worker.dto.SolvedTaskDto;
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
import java.time.Instant;
import java.util.Base64;

@Service
@PropertySource("application.properties")
public class TesseractOcrService implements OcrService {

    private final Logger logger = LoggerFactory.getLogger(TesseractOcrService.class);

    private Tesseract tesseract;

    @Value("${tesseract.data.path}")
    private String tesseractDataPath;

    @PostConstruct
    private void init() {
        setupTesseract();
    }

    private void setupTesseract() {
        tesseract = new Tesseract();
        tesseract.setLanguage("spa");
        tesseract.setDatapath(tesseractDataPath);
    }

    @Override
    public SolvedTaskDto applyOcr(PendingTaskDto pendingTask) {
        String ocrResult = this.applyOcr(pendingTask.getEncodedImage());
        return SolvedTaskDto.from(pendingTask.getJobId(), pendingTask.getTaskNumber(), ocrResult);
    }

    private String applyOcr(String encodedImage) {

        try {
            File file = File.createTempFile("task", Instant.now().toString());

            FileOutputStream outputStream = new FileOutputStream(file);

            outputStream.write(Base64.getDecoder().decode(encodedImage));
            outputStream.flush();
            outputStream.close();

            return tesseract.doOCR(file);

        } catch (TesseractException e) {
            logger.error("Error: Tesseract exception probably due to missing training data");
        } catch (Exception e) {
            logger.error("Fatal error - {}", e.getMessage());
            e.printStackTrace();
        }

        return "Error applying OCR";
    }
}
