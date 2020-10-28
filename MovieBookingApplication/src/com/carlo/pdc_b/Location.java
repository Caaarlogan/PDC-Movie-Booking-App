package com.carlo.pdc_b;

import java.util.List;
import java.util.ArrayList;

/**
 * Class that represents cinema location
 * @author Carlo Carbonilla
 */

public class Location
{
    private String name; //name of location
    private List<Cinema> cinemas; //list of cinemas of location
    
    public Location(String name)
    {
        this.name = name;
        this.cinemas = new ArrayList();
    }
    
    public String getName()
    {
        return name;
    }
}
