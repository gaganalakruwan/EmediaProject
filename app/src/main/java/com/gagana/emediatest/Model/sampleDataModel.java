package com.gagana.emediatest.Model;

import com.gagana.emediatest.Model.sampleDataImageModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class sampleDataModel implements Serializable {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("postcode")
    @Expose
    String postcode;
    @SerializedName("phoneNumber")
    @Expose
    String phoneNumber;
    @SerializedName("latitude")
    @Expose
    String latitude;
    @SerializedName("longitude")
    @Expose
    String longitude;
    @SerializedName("imageModel")
    @Expose
    sampleDataImageModel imageModel;

    public sampleDataModel(String id, String title, String description, String address, String postcode, String phoneNumber, String latitude, String longitude, sampleDataImageModel imageModel) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageModel = imageModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public sampleDataImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(sampleDataImageModel imageModel) {
        this.imageModel = imageModel;
    }
}
