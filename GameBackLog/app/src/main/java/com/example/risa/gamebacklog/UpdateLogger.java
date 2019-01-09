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

public class UpdateLogger extends AppCompatActivity{
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
        setContentView(R.layout.activitiyupdate_main);

        mTitile = findViewById(R.id.title_Text1);
        mPlatform = findViewById(R.id.platform_Text1);
        mNotes = findViewById(R.id.notes_Text1);
        mStatus = findViewById(R.id.spinner1);

        //Initialize the local variables

       db = AppDatabase.getInstance(this);

        //new LoggerAsyncTask(TASK_GET_ALL_REMINDERS).execute();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(UpdateLogger.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.statuses));

       dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatus.setAdapter(dataAdapter);

        final Logger loggerUpdate = getIntent().getParcelableExtra(MainActivity.EXTRA_REMINDER);
        mTitile.setText(loggerUpdate.getGameText());
        mPlatform.setText(loggerUpdate.getPlatText());
        mNotes.setText(loggerUpdate.getNoteText());
        mStatus.setSelection(0);


        FloatingActionButton fab1 = findViewById(R.id.fab2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = mTitile.getText().toString();
                String text2 = mPlatform.getText().toString();
                String text3 = mNotes.getText().toString();
                String text4 = mStatus.getSelectedItem().toString();

                Logger newLogger = new Logger(text1,text2,text3,text4);

//(reminderUpdate.setmReminderText(updatedReminderText)));
                if (!TextUtils.isEmpty(text1)) {
                    loggerUpdate.setGameText(text1);
                    loggerUpdate.setPlatText(text2);
                    loggerUpdate.setNoteText(text3);
                    loggerUpdate.setStatusText(text4);

                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent(UpdateLogger.this,MainActivity.class);
                    resultIntent.putExtra(MainActivity.EXTRA_REMINDER, loggerUpdate);
                    setResult(Activity.RESULT_OK, resultIntent);

                    finish();

                } else {
                    Snackbar.make(view, "Enter some data", Snackbar.LENGTH_LONG);
                }

            }
        });
    }



}
