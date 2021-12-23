package com.gagana.emediatest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class mainModel {
    @SerializedName("state")
    @Expose
    String state;
    @SerializedName("data")
    @Expose
    ArrayList<sampleDataModel> data;

    public mainModel(String state, ArrayList<sampleDataModel> data) {
        this.state = state;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<sampleDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<sampleDataModel> data) {
        this.data = data;
    }
}
