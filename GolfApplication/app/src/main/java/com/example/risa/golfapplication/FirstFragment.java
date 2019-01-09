package com.example.risa.golfapplication;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView.OnItemClickListener;

public class FirstFragment extends Fragment {

    private List<Place> mPlaces;
    private PlaceAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int mModifyPosition;
    public static final int REQUESTCODE = 1234;

    public static final String EXTRA_REMINDER = "Reminder";


    public  FirstFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPlaces = new ArrayList<>();
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_first, container, false);
      createExampleList();
      mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
      mAdapter = new PlaceAdapter(getContext(),mPlaces);
      mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

      mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new PlaceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity().getBaseContext(), CourseView.class);
                intent.putExtra("Example Item", mPlaces.get(position));

                startActivity(intent);
            }
        });



     // updateUI();
     // PlaceAdapter placeAdapter = new PlaceAdapter(mPlaces,this);
        return view;
    }


    public void createExampleList() {
        mPlaces = new ArrayList<>();
        mPlaces.add(new Place(R.drawable.image1, "The Complete Golf Course for Beginners","xxi4zG0Mig0"));
        mPlaces.add(new Place(R.drawable.image02, "Golf Course: Advanced Concepts","ZiTTHzsgbgs"));
        mPlaces.add(new Place(R.drawable.image3, "Learning Professional Golf Player","8yOxfcq7zuw"));
    }


}

