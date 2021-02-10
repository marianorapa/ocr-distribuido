package com.mrapaport.unlu.sdypp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Task {

    public Task() {
        this.ttl = LocalDateTime.now().plus(5, ChronoUnit.MINUTES);
    }

    private UUID uuid;

    private int order;

    private boolean done;

    private String imageText;

    private LocalDateTime ttl;

    private String imageEncoded;

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    private String originalFilename;

    public LocalDateTime getTtl() {
        return ttl;
    }


    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public int getOrder() {
        return order;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded = imageEncoded;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void taskCompleted(String imageText) {
        this.imageText = imageText;
        this.setDone(true);
    }
}
