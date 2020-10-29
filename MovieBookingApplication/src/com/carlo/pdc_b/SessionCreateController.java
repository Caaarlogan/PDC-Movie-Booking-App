package com.carlo.pdc_b;

import javax.swing.JFrame;

/**
 * @author Carlo Carbonilla
 */
public class SessionCreateController extends SessionController {
    private SessionCreateView view; //The view that shows the state of the model
    
    public SessionCreateController() {
        super();
        view = new SessionCreateView(model);
        add(view);
    }
}
