package com.example.risa.bucketlist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Objects;

public class EditAcitvity extends AppCompatActivity {

    private TextInputEditText inputName;
    private TextInputEditText inputDescription;
    private CheckBox checkBoxCompleted;
    private Button buttonRemove;

    private BucketItem bucketItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Init the view
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        inputName = findViewById( R.id.input_name );
        inputDescription = findViewById( R.id.input_description );
        checkBoxCompleted = findViewById( R.id.checkbox_completed );
        buttonRemove = findViewById( R.id.button_remove );

        if (Objects.equals(getIntent().getAction(), Intent.ACTION_INSERT)) {
            fab.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveBucketlistItem();
                }
            });
        } else {
            final long bucketItemId = getIntent().getLongExtra("bucketItemId", 0 );

            if( bucketItemId > 0 ) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        bucketItem = AppDatabase.getInstance( getApplicationContext() ).bucketItemDao().get( bucketItemId );

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setFieldsByBucketItem( bucketItem );
                            }
                        });
                    }
                });

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editBucketlistItem();
                    }
                });

                buttonRemove.setVisibility( View.VISIBLE );
                buttonRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteBucketItem( bucketItem );
                    }
                });
            }
        }
    }

    private void saveBucketlistItem() {
        String name = inputName.getText().toString();
        String description = inputDescription.getText().toString();
        boolean completed = checkBoxCompleted.isChecked();

        if( name.isEmpty() ) {
            inputName.setError( getString( R.string.form_error ) );
        } else if( description.isEmpty() ) {
            inputDescription.setError( getString( R.string.form_error ) );
        } else {
            final BucketItem bucketItem = new BucketItem( name, description, completed );

            // Insert the homework object in the database
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    AppDatabase.getInstance( getApplicationContext() ).bucketItemDao().insertAll( bucketItem );
                }
            });

            Toast.makeText(this, R.string.saved_bucketitem, Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    private void editBucketlistItem() {
        String name = inputName.getText().toString();
        String description = inputDescription.getText().toString();
        boolean completed = checkBoxCompleted.isChecked();

        if( name.isEmpty() ) {
            inputName.setError( getString( R.string.form_error ) );
        } else if( description.isEmpty() ) {
            inputDescription.setError( getString( R.string.form_error ) );
        } else {
            bucketItem.setName( name );
            bucketItem.setDescription( description );
            bucketItem.setCompleted( completed ? 1 : 0 );

            // Insert the homework object in the database
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    AppDatabase.getInstance( getApplicationContext() ).bucketItemDao().update( bucketItem );
                }
            });

            Toast.makeText(this, R.string.saved_bucketitem, Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    private void setFieldsByBucketItem( BucketItem bucketItem ) {
        inputName.setText( bucketItem.getName() );
        inputDescription.setText( bucketItem.getDescription() );
        checkBoxCompleted.setChecked( bucketItem.getCompleted() == 1 );
    }

    private void deleteBucketItem( final BucketItem bucketItem ) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance( getApplicationContext() ).bucketItemDao().delete( bucketItem );
            }
        });

        Toast.makeText(this, R.string.bucket_item_deleted, Toast.LENGTH_LONG).show();

        finish();
    }
}

