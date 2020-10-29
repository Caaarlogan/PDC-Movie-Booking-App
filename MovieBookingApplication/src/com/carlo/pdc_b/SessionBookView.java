package com.carlo.pdc_b;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * @author Carlo Carbonilla
 */

public class SessionBookView extends SessionView {
    private JLabel sessions;
    private JList sessionSelect;
    private DefaultListModel<String> sessionModel;
    private JScrollPane sessionScroll;
    private JButton book;
    
    public SessionBookView(MovieBookingModel model) {
        super(model);
        
        sessions = new JLabel("Sessions");
        sessions.setSize(300,20);
        sessions.setLocation(10,105);
        add(sessions);
        
        sessionModel = new DefaultListModel<>();
        sessionSelect = new JList();
        sessionSelect.setModel(sessionModel);
        sessionScroll = new JScrollPane(sessionSelect, 
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sessionScroll.setSize(460,185);
        sessionScroll.setLocation(10,130);
        add(sessionScroll);
        
        book = new JButton("Book");
        book.setSize(75,25);
        book.setLocation(10,325);
        add(book);
    }
}
