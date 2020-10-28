package com.carlo.pdc_b;

/**
 * @author Carlo Carbonilla
 */

public class Movie {
    private String title; //title of movie
    private int hourLength; //hour length of movie
    private int minuteLength; //remaining minute length of movie
    
    public Movie(String title, int hourLength, int minuteLength)
    {
        this.title = title;
        this.hourLength = hourLength;
        this.minuteLength = minuteLength;
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
