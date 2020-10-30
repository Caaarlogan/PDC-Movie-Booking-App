package com.carlo.pdc_b;

import java.time.LocalDate;
import java.util.Observable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * View of GUI where you can select sessions
 * @author Carlo Carbonilla
 */
public class SessionBookView extends SessionView {

    private JLabel sessions;
    private JList sessionSelect; //sessions you can choose from given filters
    private DefaultListModel<String> sessionModel;
    private JScrollPane sessionScroll;
    private JButton seats;

    public SessionBookView(MovieBookingModel model) {
        super(model);

        sessions = new JLabel("Sessions");
        sessions.setSize(300, 20);
        sessions.setLocation(10, 105);
        add(sessions);
        
        //List of available sessions given filters
        sessionModel = new DefaultListModel<>();
        sessionSelect = new JList();
        sessionSelect.setModel(sessionModel);
        sessionScroll = new JScrollPane(sessionSelect,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sessionScroll.setSize(460, 150);
        sessionScroll.setLocation(10, 130);
        add(sessionScroll);
        
        //Get availabel sessions
        updateSessions();

        seats = new JButton("Seats");
        seats.setSize(75, 25);
        seats.setLocation(10, 290);
        add(seats);
    }
    
    //Update used for MVC
    public void update(Observable model, Object args) {
        updateSessions();
    }

    public DefaultListModel<String> getSessionModel() {
        return sessionModel;
    }

    public JList getSessionSelect() {
        return sessionSelect;
    }

    public JButton getSeatsButton() {
        return seats;
    }

    public void updateSessions() {
        Location location = getSelectedLocation();
        Movie movie = getSelectedMovie();
        LocalDate date = getSelectedDate();

        sessionModel.removeAllElements();

        for (Session session : model.getSessionsResults(location, date, movie)) {
            sessionModel.addElement(session.toString());
        }
    }
}
