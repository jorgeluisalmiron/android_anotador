package com.example.jorgeluis.anotador.Model;

import java.io.Serializable;

/**
 * Created by Jorge Luis on 14/09/2016.
 */
public class Note implements Serializable{

    private String name;
    private String content;
    private String dateTime;
    private int backgroundColor;



    public Note(String n, String content, String d, int b){
        this.name=n;
        this.content = content;
        this.dateTime=d;
        this.backgroundColor=b;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
