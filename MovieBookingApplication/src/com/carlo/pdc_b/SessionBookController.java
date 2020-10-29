package com.carlo.pdc_b;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;

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
        view.getSeatsButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                createSeatView();
            }
        });

        add(view);
    }

    //Creates and transitions JFrame to seat GUI
    private void createSeatView() {
        if (!view.getSessionModel().isEmpty() && !view.getSessionSelect().isSelectionEmpty()) {
            String[] sessionString = ((String) (view.getSessionSelect().getSelectedValue())).split(" "); //get toString of session and split by space
            String[] idString = sessionString[2].split(","); //separate session id from comma
            int sessionID = Integer.parseInt(idString[0]); //get session id

            Session session = model.getSessions().get(sessionID);
            Cinema cinema = session.getCinema();
            seatView = new SeatBookView(cinema.getWidth(), cinema.getHeight(), session.getBookings());
            remove(view);
            add(seatView);
            revalidate();
            repaint();

            //If book button is pushed
            seatView.getBook().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    String message = model.bookSeat();
                    remove(seatView);
                    add(view);
                    
                    revalidate();
                    repaint();
                    view.getError().setText(message);
                }
            });
            
            //If back button is pushed
            seatView.getBack().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    remove(seatView);
                    add(view);
                    revalidate();
                    repaint();
                }
            });

            HashMap<String, JButton> seatButtons = seatView.getSeatButtons();

            //Iterate through all seats
            for (String seatCode : seatButtons.keySet()) {
                
                JButton button = seatButtons.get(seatCode); //get button of corresponding seat code
                
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        List<String> selectedSeats = seatView.getSelectedSeats();

                        //if seat has been reselected
                        if(selectedSeats.contains(seatCode)) {
                            selectedSeats.remove(seatCode); //remove seat from list of selected seats
                            
                            //clear and add seats for booked seats
                            String message = "Booked Seats: ";
                            for(String code : selectedSeats) {
                                message += code + " ";
                            }
                            
                            seatView.getBookedSeats().setText(message);
                            
                            button.setBackground(Color.WHITE);
                        }
                        else { //If seat hasn't been selected
                            String message = seatView.getBookedSeats().getText() + seatCode + " ";
                            seatView.getBookedSeats().setText(message);
                            selectedSeats.add(seatCode);
                            
                            button.setBackground(Color.GREEN);
                        }
                    }
                });
            }
        }
        else { //if nothing selected
            view.getError().setText("Nothing is selected");
        }
    }
}
