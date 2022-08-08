package com.example.myquotes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPICall {
    @GET("quotes")
    Call<DataModel> getData();
}
