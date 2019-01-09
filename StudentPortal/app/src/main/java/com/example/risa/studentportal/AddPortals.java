package com.example.risa.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddPortals extends AppCompatActivity{
     EditText mTitle;
     EditText mURLText;

    public static final String EXTRA_PORTAL = "Portal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addportal_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mTitle = (EditText)findViewById(R.id.title);
        mURLText = (EditText)findViewById(R.id.URL);

        Button addbt = (Button) findViewById(R.id.AddBtn);

        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = "https://" + mURLText.getText().toString();
                String Title = mTitle.getText().toString();

                if (!TextUtils.isEmpty(Title)) {

                    Intent myIntent = new Intent(AddPortals.this, MainActivity.class);
                    myIntent.putExtra("URL", URL);
                    myIntent.putExtra("Title", Title);
                    setResult(Activity.RESULT_OK, myIntent);
                    finish();

                } else {

                    Toast.makeText(getApplicationContext(), "Enter your required fileds", Toast.LENGTH_LONG).show();
                }


            }

        });
    }
}
