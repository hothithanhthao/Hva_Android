package com.example.risa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import java.util.List;
import java.util.ArrayList;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listView;
    private ImageListAdapter mAdapter;
    private List<GeoObject> mGeoObjects;
    // swipe down and up values
    private String correct = "Yeah! You're correct";
    private String incorrect = "Oh no! Try again";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (RecyclerView) findViewById(R.id.recyclerView);

        mGeoObjects = new ArrayList<>();
        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES.length; i++) {

            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i],
                    GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));


            RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

            updateUI();
            listView.setLayoutManager(mLayoutManager);

        }
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =

                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                        return true;

                    }


                    //Called when a user swipes left or right on a ViewHolder

                    @Override

                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        int position = (viewHolder.getAdapterPosition());
                        mAdapter.notifyItemChanged(position);
                        String showText = "";

                        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS.length; i++) {

                            //left to right
                            if (position == 0 || position == 3 || position == 5 || position == 6) {
                                if (swipeDir == ItemTouchHelper.LEFT) {
                                    showText = correct;
                                } else {
                                    showText = incorrect;
                                }
                            } else if (position == 1 || position == 2 || position == 4 || position == 7) {
                                if (swipeDir == ItemTouchHelper.RIGHT) {
                                    showText = correct;
                                } else {
                                    showText = incorrect;
                                }
                            }

                        }

                        //Get the index corresponding to the selected position
                        Toast.makeText(getApplicationContext(), showText, Toast.LENGTH_LONG).show();

                    }
                };


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(listView);

    }

    private void updateUI() {

        if (mAdapter == null) {

            mAdapter = new ImageListAdapter(this,mGeoObjects);

            listView.setAdapter(mAdapter);

        } else {

            mAdapter.notifyDataSetChanged();

        }

    }


}
