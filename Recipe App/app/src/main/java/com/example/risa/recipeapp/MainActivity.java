package com.example.risa.recipeapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private PagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestData();


        mSectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager(),recipes);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Set up the ViewPager with the sections adapter.


    }
    private void requestData() {
        FoodApiService service = FoodApiService.retrofit.create(FoodApiService.class);
        Call<RecipeRespond> call = service.getFoodnumber();

        call.enqueue(new Callback<RecipeRespond>() {
            @Override
            public void onResponse(Call<RecipeRespond> call, Response<RecipeRespond> response) {
                // Recipe recipe = response.body();
                if (response.body() != null) {
                  //  Log.i(TAG, "text: " + recipe.getName());
                    //Log.i(TAG," "+dayQuoteItem);
                    //String name = recipe.getName();
                    //String image = recipe.getImage();


                    //recipes.add(new Recipe(name, image));
                    Log.i(TAG, "Response: " + response.toString());
                    recipes.addAll(Arrays.asList(response.body().recipes));
                    mSectionsPagerAdapter.notifyDataSetChanged();


                    // updateUI();
                   // mSectionsPagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<RecipeRespond> call, Throwable t) {
                Log.i(TAG," "+t.getMessage());

            }
        });


    }
}
