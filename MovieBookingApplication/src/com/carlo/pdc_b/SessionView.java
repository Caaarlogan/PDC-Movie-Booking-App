package com.carlo.pdc_b;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Carlo Carbonilla
 */

public class SessionView extends JPanel{
    private JLabel location;
    private JLabel movie;
    private JComboBox locationSelect;
    private JComboBox movieSelect;
    
    public SessionView() {
        setLayout(null);
        
        location = new JLabel("Location: ");
        location.setSize(300,20);
        location.setLocation(10,10);
        add(location);
        
        movie = new JLabel("Movie: ");
        movie.setSize(300,20);
        movie.setLocation(10,40);
        add(movie);
        
        locationSelect = new JComboBox();
        locationSelect.setSize(300,20);
        locationSelect.setLocation(75,10);
        add(locationSelect);
        
        movieSelect = new JComboBox();
        movieSelect.setSize(300,20);
        movieSelect.setLocation(75,40);
        add(movieSelect);
    }
}
