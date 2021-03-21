package com.mrapaport.unlu.sdypp.shared.dtos;


public class JobCreatedDto {

    private String rawJobId;

    private int amtOfTasks;

    public JobCreatedDto(String rawJobId, int amtOfTasks) {
        this.rawJobId = rawJobId;
        this.amtOfTasks = amtOfTasks;
    }

    public JobCreatedDto() {
    }

    public String getRawJobId() {
        return rawJobId;
    }

    public void setRawJobId(String rawJobId) {
        this.rawJobId = rawJobId;
    }

    public int getAmtOfTasks() {
        return amtOfTasks;
    }

    public void setAmtOfTasks(int amtOfTasks) {
        this.amtOfTasks = amtOfTasks;
    }
}
