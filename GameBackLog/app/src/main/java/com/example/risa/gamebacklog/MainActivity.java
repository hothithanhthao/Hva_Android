package com.example.risa.gamebacklog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import android.os.AsyncTask;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements LoggerAdapter.LoggerClickListener{

    private List<Logger> mLoggers;
    private LoggerAdapter mAdapter;

    private RecyclerView mRecyclerView;

    //Constants used when calling the update activity
    public static final String EXTRA_REMINDER = "Reminder";
    public static final int REQUESTCODE_ADDING = 1234;
    public static final int REQUESTCODE_EDITING= 5678;
    private int mModifyPosition;

    public final static int TASK_GET_ALL_REMINDERS = 0;
    public final static int TASK_DELETE_REMINDER = 1;
    public final static int TASK_UPDATE_REMINDER = 2;
    public final static int TASK_INSERT_REMINDER = 3;

    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLoggers = new ArrayList<>();

        //Initialize the local variables

       db = AppDatabase.getInstance(this);
       new LoggerAsyncTask(TASK_GET_ALL_REMINDERS).execute();




        mRecyclerView = findViewById(R.id.recyclerView);
        updateUI();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



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
                        new LoggerAsyncTask(TASK_DELETE_REMINDER).execute(mLoggers.get(position));
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

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



    public void AddList(View view){

        Intent intent = new Intent(MainActivity.this, AddLoggers.class);
        startActivityForResult(intent,REQUESTCODE_ADDING);


    }

    public void onReminderDbUpdated(List list) {

        mLoggers = list;
        updateUI();

    }

    public void updateUI() {
        if (mAdapter == null) {
            mAdapter = new LoggerAdapter(mLoggers, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(mLoggers);
        }
    }

    @Override
    public void reminderOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, UpdateLogger.class);
        mModifyPosition = i;
        intent.putExtra(EXTRA_REMINDER, mLoggers.get(i));
        startActivityForResult(intent, REQUESTCODE_EDITING);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE_ADDING) {
            if (resultCode == RESULT_OK) {
               Logger addedReminder = data.getParcelableExtra(MainActivity.EXTRA_REMINDER);
                // New timestamp: timestamp of update
                // mLoggers.set(mModifyPosition, updatedReminder);
                new LoggerAsyncTask(TASK_INSERT_REMINDER).execute(addedReminder);

                updateUI();
            }
        }
        else if(requestCode == REQUESTCODE_EDITING){
            if (resultCode == RESULT_OK) {
                Logger updatedReminder = data.getParcelableExtra(MainActivity.EXTRA_REMINDER);
                // New timestamp: timestamp of update
                // mLoggers.set(mModifyPosition, updatedReminder);
                new LoggerAsyncTask(TASK_UPDATE_REMINDER).execute(updatedReminder);

                updateUI();
            }
        }
    }

    public class LoggerAsyncTask extends AsyncTask<Logger, Void, List> {

        private int taskCode;

        public LoggerAsyncTask(int taskCode) {
            this.taskCode = taskCode;
        }


        @Override
        protected List doInBackground(Logger... loggers) {
            switch (taskCode) {
                case TASK_DELETE_REMINDER:
                    db.loggerDao().deleteLoggers(loggers[0]);
                    break;

                case TASK_UPDATE_REMINDER:
                    db.loggerDao().updateLoggers(loggers[0]);
                    break;

                case TASK_INSERT_REMINDER:
                    db.loggerDao().insertLoggers(loggers[0]);
                    break;
            }


            //To return a new list with the updated data, we get all the data from the database again.
            return db.loggerDao().getAllLoggers();
        }


        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            onReminderDbUpdated(list);
        }

    }
}
