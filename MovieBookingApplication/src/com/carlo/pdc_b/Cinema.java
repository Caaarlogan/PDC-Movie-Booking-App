package com.carlo.pdc_b;

/**
 * Class representing a cinema which stores the cinema number, and the dimensions of the cinema seats
 * @author Carlo Carbonilla
 */
public class Cinema
{
    private int cinemaNum; //cinema number
    private int[] size; //dimensions of cinema seats

    public Cinema(int cinemaNum, int width, int height)
    {
        this.cinemaNum = cinemaNum;
        
        //width and height of cinema
        int[] input = {width, height};
        size = input;
    }
    
    public int[] getSize()
    {
        return size;
    }
    
    public int getNum()
    {
        return cinemaNum;
    }
}