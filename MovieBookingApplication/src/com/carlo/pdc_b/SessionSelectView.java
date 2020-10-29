package com.carlo.pdc_b;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 * @author Carlo Carbonilla
 */

public class SessionSelectView extends SessionView {
    private JLabel date;
    private JLabel sessions;
    private JComboBox dateSelect;
    private JList sessionSelect;
    private DefaultListModel<String> model;
    private JButton book;
    
    public SessionSelectView() {
        setLayout(null);
        
        date = new JLabel("Date: ");
        date.setSize(300,20);
        date.setLocation(10,70);
        add(date);
        
        sessions = new JLabel("Sessions");
        sessions.setSize(300,20);
        sessions.setLocation(10,105);
        add(sessions);
        
        dateSelect = new JComboBox();
        dateSelect.setSize(300,20);
        dateSelect.setLocation(75,70);
        add(dateSelect);
        
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
