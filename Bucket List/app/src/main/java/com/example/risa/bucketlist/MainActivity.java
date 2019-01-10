package com.example.risa.bucketlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements BucketItemAdapter.ClickListener {

    private RecyclerView recyclerView;
    private BucketItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertBucketItem();
            }
        });

        BucketItemViewModelFactory bucketItemViewModelFactory = new BucketItemViewModelFactory( AppDatabase.getInstance( getApplicationContext() ).bucketItemDao() );
        BucketItemViewModel viewModel = ViewModelProviders.of( this, bucketItemViewModelFactory ).get( BucketItemViewModel.class );

        recyclerView = findViewById( R.id.recyclerView );
        adapter = new BucketItemAdapter( this );
        viewModel.bucketItemList.observe(this, new Observer<PagedList<BucketItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<BucketItem> bucketItems) {
                adapter.submitList( bucketItems );
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnLongClick(BucketItem bucketItem) {
        editBucketItem( bucketItem );
    }

    @Override
    public void OnClick(BucketItem bucketItem) {
        setBucketItemCompleted( bucketItem, bucketItem.getCompleted() != 1);
    }

    private void insertBucketItem() {
        Intent intent = new Intent(this, EditAcitvity.class);
        intent.setAction(Intent.ACTION_INSERT);
        startActivity(intent);
    }

    private void editBucketItem( BucketItem bucketItem ) {
        Intent intent = new Intent(this, EditAcitvity.class);
        intent.putExtra("bucketItemId", bucketItem.getId() );
        intent.setAction(Intent.ACTION_EDIT);
        startActivity(intent);
    }

    private void setBucketItemCompleted( final BucketItem bucketItem, boolean completed ) {
        bucketItem.setCompleted( completed ? 1 : 0 );

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance( getApplicationContext() ).bucketItemDao().update( bucketItem );
            }
        });
    }
}
