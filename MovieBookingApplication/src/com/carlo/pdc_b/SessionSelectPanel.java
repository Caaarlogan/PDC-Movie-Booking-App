package com.carlo.pdc_b;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * @author Carlo Carbonilla
 */

public class SessionSelectPanel extends JPanel {
    private JLabel location;
    private JLabel date;
    private JLabel movie;
    private JLabel sessions;
    private JComboBox locationSelect;
    private JComboBox dateSelect;
    private JComboBox movieSelect;
    private JList sessionSelect;
    private DefaultListModel<String> model;
    private JButton book;
    
    public SessionSelectPanel() {
        setLayout(null);
        
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
        
        sessions = new JLabel("Sessions");
        sessions.setSize(300,20);
        sessions.setLocation(10,105);
        add(sessions);
        
        locationSelect = new JComboBox();
        locationSelect.setSize(300,20);
        locationSelect.setLocation(75,10);
        add(locationSelect);
        
        dateSelect = new JComboBox();
        dateSelect.setSize(300,20);
        dateSelect.setLocation(75,40);
        add(dateSelect);
        
        movieSelect = new JComboBox();
        movieSelect.setSize(300,20);
        movieSelect.setLocation(75,70);
        add(movieSelect);
        
        model = new DefaultListModel<>();
        sessionSelect = new JList();
        sessionSelect.setModel(model);
        sessionSelect.setSize(460,185);
        sessionSelect.setLocation(10,130);
        add(sessionSelect);
        
        book = new JButton("Book");
        book.setSize(75,25);
        book.setLocation(10,325);
        add(book);
    }
}
