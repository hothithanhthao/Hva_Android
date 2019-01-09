package com.example.risa.recipeapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Recipe> recipes = new ArrayList<>();

    public PagerAdapter(FragmentManager fm, ArrayList<Recipe> recipes) {
        super(fm);
        this.recipes = recipes;
    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return FoodFragment.newInstance(recipes.get(position));
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return recipes.size();
    }

}
