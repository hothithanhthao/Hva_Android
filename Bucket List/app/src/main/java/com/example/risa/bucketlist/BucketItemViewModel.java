package com.example.risa.bucketlist;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

public class BucketItemViewModel extends ViewModel {

    public final LiveData<PagedList<BucketItem>> bucketItemList;

    public BucketItemViewModel(BucketItemDao bucketItemDao) {
        bucketItemList = new LivePagedListBuilder<>( bucketItemDao.getAll(), 20 ).build();
    }
}
