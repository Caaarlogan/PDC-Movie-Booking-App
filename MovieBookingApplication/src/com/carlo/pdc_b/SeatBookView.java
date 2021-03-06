package com.carlo.pdc_b;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI View of selecting seats when booking a movie session
 * @author Carlo Carbonilla
 */
public class SeatBookView extends JPanel implements Observer {

    private JLabel screen; //Label that shows the movie screen
    private JLabel bookedSeats; //String that stores booked seats
    private JButton book; //book button to book seats
    private JButton back; //back button to get back to sessions
    private HashMap<String, JButton> seatButtons; //int seat code key, button value
    private List<String> selectedSeats; //List of selected seats
    private int width; //number of cinema seats horizontally
    private int height; //number of cinema seats vertically
    private MovieBookingModel model;
    private int sessionID;

    public SeatBookView(MovieBookingModel model, int sessionID) {
        setLayout(null);

        this.model = model;
        this.sessionID = sessionID;
        Session session = model.getSessions().get(sessionID);
        
        
        seatButtons = new HashMap();
        selectedSeats = new ArrayList();

        width = session.getCinema().getWidth();
        height = session.getCinema().getHeight();
        
        screen = new JLabel("SCREEN");
        screen.setSize(100, 20);
        screen.setLocation((width / 2) * 30, 40 + (height * 30));
        add(screen);

        bookedSeats = new JLabel("Booked Seats: ");
        bookedSeats.setSize(500, 20);
        bookedSeats.setLocation(10, 70 + (height * 30));
        add(bookedSeats);

        book = new JButton("Book");
        book.setSize(75, 20);
        book.setLocation(10, 100 + (height * 30));
        add(book);

        back = new JButton("Back");
        back.setSize(75, 20);
        back.setLocation(95, 100 + (height * 30));
        add(back);
        
        updateSeats();
    }

    public void update(Observable model, Object arg) {
        updateSeats();
    }
    
    /**
     * Goes through seats and checked whether they're booked or not and
     * color codes them white (not booked), or red (booked)
     */
    private void updateSeats() {
        
        Session session = model.getSessions().get(sessionID);
        
        boolean[][] bookings = session.getBookings();

        //Iterate through rows of cinema
        for (int i = 0; i <= height; i++) {
            int y = 0;
            char row = (char) (64 + i);
            
            //If at the first column, print out row letters
            if (i != 0) {
                y = 10 + (i * 30);
                JLabel letter = new JLabel("" + row);
                letter.setSize(75, 20);
                letter.setLocation(10, y);
                add(letter);
            }

            //Iterate through columns of cinema
            for (int j = 0; j < width; j++) {
                int x = 30 + (j * 30);
                
                //If at the first row, print out column numbers
                if (i == 0) {
                    JLabel columnNum = new JLabel("" + (j + 1));
                    columnNum.setSize(100, 20);
                    columnNum.setLocation(x, 10);
                    add(columnNum);
                }
                else {
                    int seatNum = j + 1;
                    JButton button = new JButton();
                    button.setSize(20, 20);
                    button.setLocation(x, y);
                    button.setBackground(Color.WHITE);

                    //If seat has been booked, disable button
                    if (bookings[i - 1][j]) {
                        button.setEnabled(false);
                        button.setBackground(Color.RED);
                    }

                    add(button);

                    String seat = "" + row + seatNum; //seat code (row and column)
                    seatButtons.put(seat, button); //store button in hash map
                }
            }
        }

        selectedSeats = new ArrayList();
        bookedSeats.setText("Booked Seats: ");

        revalidate();
        repaint();
    }

    public JButton getBook() {
        return book;
    }

    public JButton getBack() {
        return back;
    }

    public HashMap<String, JButton> getSeatButtons() {
        return seatButtons;
    }

    public JLabel getBookedSeats() {
        return bookedSeats;
    }

    public List<String> getSelectedSeats() {
        return selectedSeats;
    }

    public void clearSelectedSeats() {
        selectedSeats.clear();
    }
}
