package com.example.risa.bucketlist;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BucketItemDao {

    @Insert
    void insertAll(BucketItem... bucketItems);

    @Insert
    long insert(BucketItem bucketItem);

    @Update(onConflict = REPLACE)
    int update(BucketItem bucketItem);

    @Delete
    void delete(BucketItem bucketItem);

    @Query("SELECT * FROM "+ BucketItem.TABLE_NAME +" ORDER BY "+ BucketItem.COLUMN_ID +" ASC")
    DataSource.Factory<Integer, BucketItem> getAll();

    @Query("SELECT * FROM "+BucketItem.TABLE_NAME+" WHERE "+BucketItem.COLUMN_ID+" LIKE :id LIMIT 1")
    BucketItem get( long id );
}
