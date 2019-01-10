package com.example.risa.bucketlist;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class BucketItemViewModelFactory implements ViewModelProvider.Factory {

    private BucketItemDao bucketItemDao;

    public BucketItemViewModelFactory(BucketItemDao bucketItemDao) {
        this.bucketItemDao = bucketItemDao;
    }

    @NonNull
    @Override
    public BucketItemViewModel create(@NonNull Class modelClass) {
        return new BucketItemViewModel( bucketItemDao );
    }
}

