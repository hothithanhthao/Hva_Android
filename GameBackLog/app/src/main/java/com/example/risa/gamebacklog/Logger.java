package com.example.risa.gamebacklog;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "logger")
public class Logger implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo (name = "game")
    public String mgameText;

    @ColumnInfo (name = "platform")
    public String mplatText;

    @ColumnInfo (name = "status")
     public String mstatusText;

   // @ColumnInfo (name = "date")
   // private String mDateText;

    @ColumnInfo (name = "note")
    public String mNoteText;


    public Logger(String mgameText, String mplatText, String mNoteText, String mstatusText) {
        this.mgameText = mgameText;
        this.mplatText = mplatText;
        this.mNoteText = mNoteText;
        this.mstatusText = mstatusText;
    }

    public String getGameText() {
        return mgameText;
    }

    public void setGameText(String mgameText) {
        this.mgameText = mgameText;
    }

    public String getPlatText() {
        return mplatText;
    }

    public void setPlatText(String mplatText) {
        this.mplatText = mplatText;
    }

    public String getStatusText() {
        return mstatusText;
    }

    public void setStatusText(String mstatusText) {
        this.mstatusText = mstatusText;
    }

    //public String getDateText() {
       // return mDateText;
    //}

    //public void setDateText(String mDateText) {
       // this.mDateText = mDateText;
   // }

    public String getNoteText() {
        return mNoteText;
    }

    public void setNoteText(String mNoteText) {
        this.mNoteText = mNoteText;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.mgameText);
        dest.writeString(this.mplatText);
        dest.writeString(this.mstatusText);
        dest.writeString(this.mNoteText);
        //dest.writeString(this.mDateText);
    }

    protected Logger(Parcel in) {
        this.id = in.readLong();
        this.mgameText = in.readString();
        this.mplatText = in.readString();
        this.mstatusText = in.readString();
        this.mNoteText = in.readString();
        //this.mDateText = in.readString();
    }

    public static final Creator<Logger> CREATOR = new Creator<Logger>() {
        @Override
        public Logger createFromParcel(Parcel source) {
            return new Logger(source);
        }

        @Override
        public Logger[] newArray(int size) {
            return new Logger[size];
        }
    };


}
