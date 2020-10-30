package com.carlo.pdc_b;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Observable;
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
    private JLabel timeHours;
    private JLabel timeMinutes;
    private JLabel dateTimeHelp;
    private JComboBox cinemaSelect;
    private JTextField timeHoursSelect;
    private JTextField timeMinutesSelect;
    private JButton create;
    protected boolean constructorFinished;
    protected DateTimeFormatter timeFormat; //format time is printed in
    
    /**
     * Initialize components
     * @param model 
     */
    public SessionCreateView(MovieBookingModel model) {
        super(model);
        
        timeFormat = DateTimeFormatter.ofPattern("H:m");
        
        constructorFinished = false;
        
        cinema = new JLabel("Cinema: ");
        cinema.setSize(300,20);
        cinema.setLocation(10,100);
        add(cinema);
        
        timeHours = new JLabel("Time (Hours): ");
        timeHours.setSize(300,20);
        timeHours.setLocation(10,130);
        add(timeHours);
        
        timeMinutes = new JLabel("Time (Minutes): ");
        timeMinutes.setSize(300,20);
        timeMinutes.setLocation(10,160);
        add(timeMinutes);
        
        cinemaSelect = new JComboBox();
        cinemaSelect.setSize(300,20);
        cinemaSelect.setLocation(75,100);
        add(cinemaSelect);
        
        updateCinemas();
        
        LocalTime timeNow = LocalTime.now();
        int hours = timeNow.getHour();
        int minutes = timeNow.getMinute();
        
        timeHoursSelect = new JTextField(hours + "");
        timeHoursSelect.setSize(300,20);
        timeHoursSelect.setLocation(100,130);
        add(timeHoursSelect);
        
        timeMinutesSelect = new JTextField(minutes + "");
        timeMinutesSelect.setSize(300,20);
        timeMinutesSelect.setLocation(100,160);
        add(timeMinutesSelect);
        
        create = new JButton("Create");
        create.setSize(75,25);
        create.setLocation(10,190);
        add(create);
        
        dateTimeHelp = new JLabel("Hours (0-23), Minutes (0-59)");
        dateTimeHelp.setSize(350,50);
        dateTimeHelp.setLocation(10,210);
        add(dateTimeHelp);
        
        
        
        constructorFinished = true;
    }
    
    /**
     * Nothing to update as it's updated in the model class
     * @param model
     * @param arg 
     */
    public void update(Observable model, Object arg) {
        
    }
    
    //Updates cinemas when location is changed
    public void updateCinemas() {
        cinemaSelect.removeAllItems();
        
        HashMap<Integer,Location> locationMap = model.getLocations();
        
        int locationID = 1;
        
        //If constructor is finished, get selected location
        if(constructorFinished) {
            locationID = locationSelect.getSelectedIndex() + 1;
        }
        
        Location location = locationMap.get(locationID);
        
        HashMap<Integer, Cinema> cinemaMap = location.getCinemas();
        Set<Integer> cinemaIDs = cinemaMap.keySet();
        
        for(int id : cinemaIDs) {
            cinemaSelect.addItem("" + cinemaMap.get(id).getNum());;
        }
    }
    
    public JButton getCreate() {
        return create;
    }
    
    public int getSelectedHour() {
        String hours = timeHoursSelect.getText();
        
        //Check if string only contains numbers
        if(hours.matches("[0-9]+")) {
            int hour = Integer.parseInt(hours);
            
            if(hour >= 0 && hour <= 23) {
                return hour;
            }
        }
        
        return -1; //return -1 if invalid hour input
    }
    
    public int getSelectedMinute() {
        String minutes = timeMinutesSelect.getText();
        
        //Check if string only contains numbers
        if(minutes.matches("[0-9]+")) {
            int minute = Integer.parseInt(minutes);
            
            if(minute >= 0 && minute <= 59) {
                return minute;
            }
        }

        return -1; //return -1 if invalid minute input
    }
    
    public Cinema getSelectedCinema() {
        int i = cinemaSelect.getSelectedIndex();
        Location location = getSelectedLocation();
        
        HashMap<Integer, Cinema> cinemaMap = location.getCinemas();
        Set<Integer> keys = cinemaMap.keySet();
        
        for(int key : keys) {
            Cinema cinema = cinemaMap.get(key);
            
            if(cinema.getNum()==i+1) {
                return cinema;
            }
        }
        
        return null;
    }
    
    
}
