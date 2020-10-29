package com.carlo.pdc_b;

import javax.swing.JFrame;

/**
 * @author Carlo Carbonilla
 */
public class SessionCreateController extends JFrame {
    private MovieBookingModel model; //The model this controller will update
    private SessionCreateView view; //The view that shows the state of the model
    
    public SessionCreateController() {
        model = new MovieBookingModel();
        view = new SessionCreateView();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        add(view);
    }
}
