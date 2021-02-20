package com.mrapaport.unlu.sdypp.splitter.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ImagesReceivedResponseDTO implements Serializable {

    private String uuid;

    private ImagesReceivedResponseDTO(String uuid){
        this.uuid = uuid;
    }

    public static ImagesReceivedResponseDTO from(UUID uuid) {
        return new ImagesReceivedResponseDTO(uuid.toString());
    }
}
