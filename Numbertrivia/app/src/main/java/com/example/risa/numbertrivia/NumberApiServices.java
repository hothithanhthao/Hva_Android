package com.example.risa.numbertrivia;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NumberApiServices {

    String BASE_URL = "http://numbersapi.com/";


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // The string in the GET annotation is added to the BASE_URL
    @GET("/random/trivia?json&max=999")
    // The query is added after ?json, for example it could look like '?json&max=999'
    Call<NumberTriviaItem> getRandomnumber();
}