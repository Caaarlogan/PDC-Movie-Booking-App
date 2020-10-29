package com.carlo.pdc_b;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Template GUI that's both used for session creation and selection
 * @author Carlo Carbonilla
 */

public class SessionView extends JPanel{
    protected JLabel location;
    protected JLabel movie;
    protected JLabel date;
    protected JComboBox locationSelect;
    protected JComboBox movieSelect;
    protected JTextField dateSelect;
    protected MovieBookingModel model; //The model to which this view is attached
    protected String[] locations;
    
    public SessionView(MovieBookingModel model) {
        setLayout(null);
        
        this.model = model;
        
        location = new JLabel("Location: ");
        location.setSize(300,20);
        location.setLocation(10,10);
        add(location);
        
        date = new JLabel("Date: ");
        date.setSize(300,20);
        date.setLocation(10,40);
        add(date);
        
        movie = new JLabel("Movie: ");
        movie.setSize(300,20);
        movie.setLocation(10,70);
        add(movie);
        
        locationSelect = new JComboBox();
        locationSelect.setSize(300,20);
        locationSelect.setLocation(75,10);
        add(locationSelect);
        
        dateSelect = new JTextField();
        dateSelect.setSize(300,20);
        dateSelect.setLocation(75,40);
        add(dateSelect);
        
        movieSelect = new JComboBox();
        movieSelect.setSize(300,20);
        movieSelect.setLocation(75,70);
        add(movieSelect);
    }
}
