package com.carlo.pdc_b;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Carlo Carbonilla
 */

public class Session {
    private int id;
    private Location location; //location of session
    private LocalDate date; //date of session
    private Movie movie; //movie session is playing
    private Cinema cinema; //cinema the session is in
    private LocalTime timeFrom; //start time of session
    private LocalTime timeTo; //end time of session
    private boolean[][] bookings; //2d array of seat bookings of session
    private int freeSeats; //number of free seats in session
    private DateTimeFormatter timeFormat;

    public Session(int id, Location location, LocalDate date, Movie movie, Cinema cinema, LocalTime timeFrom, LocalTime timeTo)
    {
        this.id = id;
        this.location = location;
        this.date = date;
        this.movie = movie;
        this.cinema = cinema;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;

        int x = cinema.getHeight();
        int y = cinema.getWidth();

        this.bookings = new boolean[x][y];

        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                bookings[i][j] = false;
            }
        }
        
        freeSeats = x*y;
        
        timeFormat = DateTimeFormatter.ofPattern("HH:mm");
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
    
    public boolean[][] getBookings() {
        return bookings;
    }
    
    public String toString() {
        return "Session ID: " + id + ", Cinema: " + cinema.getNum() +
               ", Time: " + timeFrom.format(timeFormat) + " - " +
               timeTo.format(timeFormat);
    }
}
