package com.carlo.pdc_b;

import java.util.HashMap;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Carlo Carbonilla
 */

public class SessionCreateView extends SessionView{
    private JLabel cinema;
    private JLabel date;
    private JLabel time;
    private JLabel error;
    private JComboBox cinemaSelect;
    private JTextField dateSelect;
    private JTextField timeSelect;
    private JButton create;
    protected String[] cinemas;
    protected boolean constructorFinished;
    
    public SessionCreateView(MovieBookingModel model) {
        super(model);
        
        constructorFinished = false;
        
        updateCinemas();
        
        cinema = new JLabel("Cinema: ");
        cinema.setSize(300,20);
        cinema.setLocation(10,100);
        add(cinema);
        
        time = new JLabel("Time: ");
        time.setSize(300,20);
        time.setLocation(10,130);
        add(time);
        
        cinemaSelect = new JComboBox(cinemas);
        cinemaSelect.setSize(300,20);
        cinemaSelect.setLocation(75,100);
        add(cinemaSelect);
                
        timeSelect = new JTextField();
        timeSelect.setSize(300,20);
        timeSelect.setLocation(75,130);
        add(timeSelect);
        
        create = new JButton("Create");
        create.setSize(75,25);
        create.setLocation(10,170);
        add(create);
        
        error = new JLabel("Date format: dd/mm/yy, Time format: hh:mm (00-23 hours)");
        error.setSize(350,50);
        error.setLocation(10,190);
        add(error);
        
        constructorFinished = true;
    }
    
    protected void updateCinemas() {
        HashMap<Integer,Location> locationMap = model.getLocations();
        
        int locationID = 1;
        
        if(constructorFinished) {
            locationID = locationSelect.getSelectedIndex() + 1;
        }
        
        Location location = locationMap.get(locationID);
        
        HashMap<Integer, Cinema> cinemaMap = location.getCinemas();
        Set<Integer> cinemaIDs = cinemaMap.keySet();
        int size = cinemaIDs.size();
        
        cinemas = new String[size];
        
        int i = 0;
        
        for(int id : cinemaIDs) {
            cinemas[i] = "" + cinemaMap.get(id).getNum();
            i++;
        }
    }
}
