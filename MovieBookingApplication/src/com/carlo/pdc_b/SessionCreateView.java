package com.carlo.pdc_b;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private JLabel timeHours;
    private JLabel timeMinutes;
    private JLabel dateTimeHelp;
    private JLabel error;
    private JComboBox cinemaSelect;
    private JTextField timeHoursSelect;
    private JTextField timeMinutesSelect;
    private JButton create;
    protected boolean constructorFinished;
    protected DateTimeFormatter timeFormat; //format time is printed in
    
    //Initialize components
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
        
        error = new JLabel("");
        error.setSize(350,50);
        error.setLocation(10,240);
        add(error);
        
        constructorFinished = true;
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
    
    public LocalDate getSelectedDate() {
        int i = dateSelect.getSelectedIndex();
        return LocalDate.now().plusDays(i);
    }
    
    public Location getSelectedLocation() {
        int i = locationSelect.getSelectedIndex();
        HashMap<Integer,Location> locationMap = model.getLocations();
        return locationMap.get(i+1);
    }
    
    public Movie getSelectedMovie() {
        int i = movieSelect.getSelectedIndex();
        HashMap<Integer,Movie> movieMap = model.getMovies();
        return movieMap.get(i+1);
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
    
    public JLabel getError() {
        return error;
    }
}
