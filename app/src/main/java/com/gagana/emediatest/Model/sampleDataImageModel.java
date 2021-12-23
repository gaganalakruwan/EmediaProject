package com.gagana.emediatest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sampleDataImageModel {

    @SerializedName("small")
    @Expose
    String small;
    @SerializedName("medium")
    @Expose
    String medium;
    @SerializedName("large")
    @Expose
    String large;

    public sampleDataImageModel(String small, String medium, String large) {
        this.small = small;
        this.medium = medium;
        this.large = large;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
