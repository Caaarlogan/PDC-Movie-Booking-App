package com.carlo.pdc_b;

import java.util.List;
import java.util.HashMap;

/**
 * Class that represents cinema location
 * @author Carlo Carbonilla
 */

public class Location
{
    private String name; //name of location
    private HashMap<Integer, Cinema> cinemas; //id key, cinema value
    
    public Location(String name)
    {
        this.name = name;
        this.cinemas = new HashMap();
    }
    
    public void addCinema(int id, Cinema cinema) {
        cinemas.put(id, cinema);
    }
    
    public String getName()
    {
        return name;
    }
    
    public HashMap<Integer, Cinema> getCinemas() {
        return cinemas;
    }
    
    public String toString() {
        return name;
    }
}
