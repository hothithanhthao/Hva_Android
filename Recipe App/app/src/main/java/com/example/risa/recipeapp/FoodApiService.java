package com.example.risa.recipeapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodApiService {
    String BASE_URL = "https://www.food2fork.com/";

    String API_KEY = "04af4847930ca49d05cf08b803451db8";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

   // String SEARCH_TOP_THREE ="https://www.food2fork.com/api/search?key=04af4847930ca49d05cf08b803451db8&sort=r&count=3";

    // The string in the GET annotation is added to the BASE_URL
    @GET("api/search?key=04af4847930ca49d05cf08b803451db8&sort=r&count=3")
    // The query is added after ?json, for example it could look like '?json&max=999'
    Call<RecipeRespond>getFoodnumber();
}
