package com.example.risa.bucketlist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = BucketItem.TABLE_NAME )
public class BucketItem {

    public static final String TABLE_NAME = "bucket_item";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COMPLETED = "completed";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_NAME)
    public String name;

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    private String description;

    @ColumnInfo(name = COLUMN_COMPLETED)
    private int completed;

    public BucketItem() {}

    public BucketItem(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed ? 1 : 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}