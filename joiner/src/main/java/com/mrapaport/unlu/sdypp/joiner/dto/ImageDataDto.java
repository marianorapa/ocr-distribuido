package com.mrapaport.unlu.sdypp.joiner.dto;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class ImageDataDto {

    private String jobId;

    private int taskNumber;

    private String imageText;

    public static ImageDataDto from(String imageData) {
        Gson gson = new Gson();
        return gson.fromJson(imageData, ImageDataDto.class);
    }
}
