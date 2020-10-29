package com.carlo.pdc_b;

/**
 * @author Carlo Carbonilla
 */

public class Movie {
    private int id;
    private String title; //title of movie
    private int hourLength; //hour length of movie
    private int minuteLength; //remaining minute length of movie
    
    public Movie(int id, String title, int hourLength, int minuteLength)
    {
        this.id = id;
        this.title = title;
        this.hourLength = hourLength;
        this.minuteLength = minuteLength;
    }
    
    public int getID() {
        return id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public int getHourLength()
    {
        return hourLength;
    }
    
    public int getMinuteLength()
    {
        return minuteLength;
    }
}
