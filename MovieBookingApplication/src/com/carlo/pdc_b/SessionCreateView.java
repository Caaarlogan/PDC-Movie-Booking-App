package com.carlo.pdc_b;

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
    
    public SessionCreateView() {
        cinema = new JLabel("Cinema: ");
        cinema.setSize(300,20);
        cinema.setLocation(10,70);
        add(cinema);
        
        date = new JLabel("Date: ");
        date.setSize(300,20);
        date.setLocation(10,100);
        add(date);
        
        time = new JLabel("Time: ");
        time.setSize(300,20);
        time.setLocation(10,130);
        add(time);
        
        cinemaSelect = new JComboBox();
        cinemaSelect.setSize(300,20);
        cinemaSelect.setLocation(75,70);
        add(cinemaSelect);
        
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
