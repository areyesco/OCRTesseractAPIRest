package com.areyes.service;

import java.nio.file.Paths;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OcrService {
    private final TessBaseAPI tessInstance;
    private final String imagesPaths = "C:\\Datos\\dev\\projects\\python\\automationRMMICI\\image\\ocr";
    private final String imagesPathsTESTING = "C:\\Datos\\dev\\projects\\python\\automationRMMICI\\image\\ocr\\test\\zoomTest";

    @Autowired
    public OcrService() {
        tessInstance = new TessBaseAPI();
        //tessInstance.Init("C:\\Datos\\dev\\projects\\java\\ocr-api-rest\\src\\main\\resources\\", "spa");
        tessInstance.Init("C:\\Datos\\dev\\projects\\java\\ocr-api-rest\\src\\main\\resources\\", "eng");
        System.out.println("Tesseract initialized");
    }

    public String executeOcr(String imgPath) {
        return this.ocrGetText(imgPath);
    }

    public void deleteOcr(Long OcrId) {
        //ocrRepository.deleteById(OcrId);
    }

    public String ocrGetText(String imgFileName)
    {
        String extractedText = "";
        String imgPath = Paths.get(imagesPaths, imgFileName).toString();
        PIX image = lept.pixRead(imgPath);
        if (image != null) 
        {
            tessInstance.SetImage(image);
            BytePointer bytePointer = tessInstance.GetUTF8Text();
            if (bytePointer != null)
            {
                extractedText = bytePointer.getString();
                if (extractedText != null)
                {
                    extractedText = extractedText.trim();
                }
            }
        }
        System.out.println(String.format("Extracted text: >%s<", extractedText));
        return extractedText;
    }

}
