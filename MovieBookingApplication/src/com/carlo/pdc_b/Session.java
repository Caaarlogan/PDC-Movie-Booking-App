package com.carlo.pdc_b;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Carlo Carbonilla
 */

public class Session {
    private Location location; //location of session
    private LocalDate date; //date of session
    private Movie movie; //movie session is playing
    private Cinema cinema; //cinema the session is in
    private LocalTime timeFrom; //start time of session
    private LocalTime timeTo; //end time of session
    private boolean[][] bookings; //2d array of seat bookings of session
    private int freeSeats; //number of free seats in session

    public Session(Location location, LocalDate date, Movie movie, Cinema cinema, LocalTime timeFrom, LocalTime timeTo)
    {
        this.location = location;
        this.date = date;
        this.movie = movie;
        this.cinema = cinema;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;

        int numSides = cinema.getSize()[1];
        int sideWidth = cinema.getSize()[2];
        int x = numSides * sideWidth; //total width of seats of cinema

        int y = cinema.getSize()[0]; //height of seats of cinema

        this.bookings = new boolean[x][y];

        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < y; j++)
            {
                bookings[i][j] = false;
            }
        }
        
        freeSeats = x*y;
    }
    
    //book a seat for this session
    public boolean book(int x, int y)
    {
        if(!bookings[x][y])
        {
            //if seat not booked, book it and return true
            bookings[x][y] = true;
            freeSeats--;
            return true;
        }
        else
        {
            //if seat already booked, return false
            return false;
        }
    }
    
    public LocalTime getTimeFrom()
    {
        return timeFrom;
    }
    
    public LocalTime getTimeTo()
    {
        return timeTo;
    }
    
    public int getCinemaNum()
    {
        return cinema.getNum();
    }
    
    public Cinema getCinema()
    {
        return cinema;
    }
    
    public int getFreeSeats()
    {
        return freeSeats;
    }
}
