package com.gagana.emediatest;

import com.gagana.emediatest.Model.mainModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface Service {

    @Headers({"Content-Type: application/json"})
    @GET
    Call<mainModel> getSampleData(@Url String dataUrl);
}
