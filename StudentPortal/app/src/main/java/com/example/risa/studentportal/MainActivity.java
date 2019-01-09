package com.example.risa.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import java.util.List;
import java.util.ArrayList;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
public class MainActivity extends AppCompatActivity implements PortalsAdapter.PortalsClickListener  {

    List<Portals> mPortals;
    private PortalsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    public static final String EXTRA_REMINDER = "Reminder";
    public static final int REQUESTCODE = 1234;
    private int mModifyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mPortals = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);

        mPortals.add(new Portals("VLO", "https://vlo.informatica.hva.nl"));
        mPortals.add(new Portals("Roosters", "https://rooster.hva.nl/"));
        mPortals.add(new Portals("SIS", "https://www.sis.hva.nl:8011/psp/S2PRD/?cmd=login"));

        updateUI();


        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter = new PortalsAdapter(mPortals, this);
        mRecyclerView.setAdapter(mAdapter);



        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        //mPortals.remove(position);
                       mPortals.remove(position);
                       mAdapter.notifyItemRemoved(position);
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }




    public void showList(View view){
        Intent intent = new Intent(MainActivity.this, AddPortals.class);
        startActivityForResult(intent,REQUESTCODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new PortalsAdapter(mPortals, this);
            //(mPortals, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void reminderOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, WebMain.class);
        mModifyPosition = i;
        intent.putExtra("URL", mPortals.get(i).getURLText());
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {


                String URL = data.getStringExtra("URL");
                String titleText = data.getStringExtra("Title");

                mPortals.add(new Portals(titleText, URL));
                updateUI();
            }
        }

    }


}

