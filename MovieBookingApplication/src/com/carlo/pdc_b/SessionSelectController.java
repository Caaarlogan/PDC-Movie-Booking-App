package com.carlo.pdc_b;

import javax.swing.JFrame;

/**
 * @author Carlo Carbonilla
 */
public class SessionSelectController extends SessionController {
    private SessionSelectView view; //The view that shows the state of the model
    
    public SessionSelectController() {
        super();
        view = new SessionSelectView(model);
        add(view);
    }
}
