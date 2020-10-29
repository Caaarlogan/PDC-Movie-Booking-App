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
    
    
    public SessionCreateView(MovieBookingModel model) {
        super(model);
        
        cinema = new JLabel("Cinema: ");
        cinema.setSize(300,20);
        cinema.setLocation(10,100);
        add(cinema);
        
        time = new JLabel("Time: ");
        time.setSize(300,20);
        time.setLocation(10,130);
        add(time);
        
        cinemaSelect = new JComboBox();
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
        error.setSize(300,50);
        error.setLocation(10,190);
        add(error);
    }
}
