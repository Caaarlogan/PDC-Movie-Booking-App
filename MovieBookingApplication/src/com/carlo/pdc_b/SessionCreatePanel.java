package com.carlo.pdc_b;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Carlo Carbonilla
 */

public class SessionCreatePanel extends JPanel{
    private JLabel location;
    private JLabel cinema;
    private JLabel movie;
    private JLabel date;
    private JLabel time;
    private JLabel error;
    private JComboBox locationSelect;
    private JComboBox cinemaSelect;
    private JComboBox movieSelect;
    private JTextField dateSelect;
    private JTextField timeSelect;
    private JButton create;
    
    public SessionCreatePanel() {
        setLayout(null);
        
        location = new JLabel("Location: ");
        location.setSize(300,20);
        location.setLocation(10,10);
        add(location);
        
        cinema = new JLabel("Cinema: ");
        cinema.setSize(300,20);
        cinema.setLocation(10,40);
        add(cinema);
        
        movie = new JLabel("Movie: ");
        movie.setSize(300,20);
        movie.setLocation(10,70);
        add(movie);
        
        date = new JLabel("Date: ");
        date.setSize(300,20);
        date.setLocation(10,100);
        add(date);
        
        time = new JLabel("Time: ");
        time.setSize(300,20);
        time.setLocation(10,130);
        add(time);
        
        locationSelect = new JComboBox();
        locationSelect.setSize(300,20);
        locationSelect.setLocation(75,10);
        add(locationSelect);
        
        cinemaSelect = new JComboBox();
        cinemaSelect.setSize(300,20);
        cinemaSelect.setLocation(75,40);
        add(cinemaSelect);
        
        movieSelect = new JComboBox();
        movieSelect.setSize(300,20);
        movieSelect.setLocation(75,70);
        add(movieSelect);
        
        dateSelect = new JTextField();
        dateSelect.setSize(300,20);
        dateSelect.setLocation(75,100);
        add(dateSelect);
                
        timeSelect = new JTextField();
        timeSelect.setSize(300,20);
        timeSelect.setLocation(75,130);
        add(timeSelect);
        
        create = new JButton("Create");
        create.setSize(75,25);
        create.setLocation(10,170);
        add(create);
        
        error = new JLabel("Date format: dd/mm/yy, Time format: hh:mm (24h)");
        error.setSize(300,50);
        error.setLocation(10,190);
        add(error);
    }
}
