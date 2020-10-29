package com.carlo.pdc_b;

/**
 * Class representing a cinema which stores the cinema number, and the dimensions of the cinema seats
 * @author Carlo Carbonilla
 */
public class Cinema
{
    private int id;
    private int cinemaNum; //cinema number
    private int width; //dimensions of cinema seats
    private int height;

    public Cinema(int id, int cinemaNum, int width, int height)
    {
        this.id = id;
        this.cinemaNum = cinemaNum;
        this.width = width;
        this.height = height;
    }
    
    public int getID() {
        return id;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getNum()
    {
        return cinemaNum;
    }
}