package com.example.myquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt=findViewById(R.id.b);
        //Retrofit builder
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.quotable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Instance for interface
        MyAPICall MyAPICall=retrofit.create(MyAPICall.class);
        Call<DataModel> call=MyAPICall.getData();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                //checking for the response
                if(response.code()!=200)
                {
                    txt.setText("Check the connection");
                    return;
                }
                //get the data into textview
                ArrayList<DataModel> s=response.body().getResults();
                String s1="";
                for(DataModel data: s) {
                    s1+=s.toString();
                }
                Log.d("Cool", s1);
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });





    }
}