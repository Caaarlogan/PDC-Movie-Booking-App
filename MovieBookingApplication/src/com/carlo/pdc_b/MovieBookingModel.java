package com.carlo.pdc_b;

import java.util.HashMap;
import java.util.List;

/**
 * @author Carlo Carbonilla
 */

public class MovieBookingModel {
    private HashMap<Integer,Location> locations;
    private HashMap<Integer,Movie> movies;
    private HashMap<Integer,Session> sessions;
    private static int sessionCounter;
    
    public void addSession(int id, Movie movie) {
        
    }
    
    public int getSessionCounter() {
        return sessionCounter++ ;
    }
}
