package com.carlo.pdc_b;

import javax.swing.JFrame;

/**
 * Template of the two controller classes
 * @author Carlo Carbonilla
 */
public abstract class SessionController extends JFrame{
    protected MovieBookingModel model; //The model this controller will update
    
    public SessionController() {
        model = new MovieBookingModel();
        Thread t = new Thread(model); //start model thread to notify observers
        t.start();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
    }
}
