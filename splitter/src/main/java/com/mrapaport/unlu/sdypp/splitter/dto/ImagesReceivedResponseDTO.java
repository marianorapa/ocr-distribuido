package com.mrapaport.unlu.sdypp.splitter.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImagesReceivedResponseDTO implements Serializable {

    private String jobId;

    private ImagesReceivedResponseDTO(String jobId){
        this.jobId = jobId;
    }

    public static ImagesReceivedResponseDTO from(String jwt) {
        return new ImagesReceivedResponseDTO(jwt);
    }
}
