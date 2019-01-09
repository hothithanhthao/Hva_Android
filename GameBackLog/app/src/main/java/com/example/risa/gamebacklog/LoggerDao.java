package com.example.risa.gamebacklog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface LoggerDao {

    @Query("SELECT * FROM logger")
    public List<Logger> getAllLoggers();

    @Insert
    public void insertLoggers(Logger loggers);


    @Delete
    public void deleteLoggers(Logger loggers);

    @Update
    public void updateLoggers(Logger loggers);


}
