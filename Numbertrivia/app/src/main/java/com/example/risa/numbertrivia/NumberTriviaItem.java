package com.example.risa.numbertrivia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumberTriviaItem {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("number")
    @Expose
    private Integer number;


    public NumberTriviaItem(String text, Integer number){
        super();
        this.text = text;
        this.number = number;

    }

    public String getText() {

        return text;

    }


    public void setText(String text) {

        this.text = text;

    }


    public Integer getNumber() {

        return number;

    }


    public void setNumber(Integer number) {

        this.number = number;

    }


}
