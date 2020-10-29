package com.carlo.pdc_b;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author Carlo Carbonilla
 */
public class SessionBookController extends SessionController {

    private SessionBookView view; //View that shows available sessions
    private SeatBookView seatView; //View that shows seat of sessions
    private ItemListener comboListener;

    public SessionBookController() {
        super();
        view = new SessionBookView(model);

        comboListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                view.updateSessions();
            }
        };

        view.getLocationSelect().addItemListener(comboListener);
        view.getDateSelect().addItemListener(comboListener);
        view.getMovieSelect().addItemListener(comboListener);

        //If book button is pushed
        view.getBook().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                createSeatView();
            }
        });
        
        //If book button is pushed
        view.getBook().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                model.bookSeat();
            }
        });
        
        add(view);
    }

    private void createSeatView() {
        if (!view.getSessionModel().isEmpty() && !view.getSessionSelect().isSelectionEmpty()) {
            String[] sessionString = ((String) (view.getSessionSelect().getSelectedValue())).split(" ");
            String[] idString = sessionString[2].split(",");
            int sessionID = Integer.parseInt(idString[0]);

            Session session = model.getSessions().get(sessionID);
            Cinema cinema = session.getCinema();
            seatView = new SeatBookView(cinema.getWidth(), cinema.getHeight(), session.getBookings());
            remove(view);
            add(seatView);
            revalidate();
        }
        else { //if nothing selected
            view.getError().setText("Nothing is selected");
        }
    }
}
