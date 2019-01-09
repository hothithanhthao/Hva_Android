package com.example.risa.dicesapp;

public class Throw {
    private String mThrowText;

    public String getmReminderText() {
        return mThrowText;
    }

    public void setmReminderText(String mThrowText) {
        this.mThrowText = mThrowText;
    }

    public Throw(String mThrowText) {

        this.mThrowText = mThrowText;
    }

    @Override
    public String toString() {
        return mThrowText.toString();
    }
}
