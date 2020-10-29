package com.carlo.pdc_b;

import javax.swing.JFrame;

/**
 * @author Carlo Carbonilla
 */
public class SessionBookController extends SessionController {
    private SessionBookView view; //The view that shows the state of the model
    
    public SessionBookController() {
        super();
        view = new SessionBookView(model);
        add(view);
    }
}
