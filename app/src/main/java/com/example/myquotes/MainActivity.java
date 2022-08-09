package com.example.myquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataModel> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        dataList=new ArrayList<>();
        //Retrofit builder
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.quotable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Instance for interface
        MovieAPI MovieAPI=retrofit.create(MovieAPI.class);
        Call<JSONResponse> call=MovieAPI.getData();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse= response.body();
                dataList=new ArrayList<>(Arrays.asList(jsonResponse.getResults()));
                putDataIntoRecyclerView(dataList);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }
    private void putDataIntoRecyclerView(List<DataModel> dataList){
        Adaptery adaptery=new Adaptery(this,dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }
}