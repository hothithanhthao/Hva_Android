package com.example.risa.gamebacklog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.text.TextUtils;
import android.content.Intent;
import android.app.Activity;
import android.support.design.widget.Snackbar;


import java.util.List;

public class AddLoggers extends AppCompatActivity {
    EditText mTitile;
    EditText mPlatform;
    EditText mNotes;
    Spinner mStatus;
    private List<Logger> mLoggers;
    private LoggerAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public final static int TASK_GET_ALL_REMINDERS = 0;
    public final static int TASK_DELETE_REMINDER = 1;
    public final static int TASK_UPDATE_REMINDER = 2;
    public final static int TASK_INSERT_REMINDER = 3;
    static AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mTitile = findViewById(R.id.title_Text);
        mPlatform = findViewById(R.id.platform_Text);
        mNotes = findViewById(R.id.notes_Text);
        mStatus =findViewById(R.id.spinner);

        //Initialize the local variables
        db = AppDatabase.getInstance(this);

       //new LoggerAsyncTask(TASK_GET_ALL_REMINDERS).execute();

        ArrayAdapter<CharSequence> myStatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.statuses, android.R.layout.simple_spinner_item);

        myStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatus.setAdapter(myStatusAdapter);

        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = mTitile.getText().toString();
                String text2 = mPlatform.getText().toString();
                String text3 = mNotes.getText().toString();
                String text4 = mStatus.getSelectedItem().toString();

                Logger newLogger = new Logger(text1,text2,text3,text4);
                if (!TextUtils.isEmpty(text1)) {

                    //new LoggerAsyncTask(TASK_INSERT_REMINDER).execute(newLogger);
                    Intent resultIntent = new Intent(AddLoggers.this,MainActivity.class);
                    resultIntent.putExtra(MainActivity.EXTRA_REMINDER, newLogger);
                    setResult(Activity.RESULT_OK, resultIntent);

                    finish();



                } else {
                    Snackbar.make(view, "Enter some data", Snackbar.LENGTH_LONG);
                }

            }
        });

    }



}
