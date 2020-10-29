package com.carlo.pdc_b;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Carlo Carbonilla
 */
public class SeatBookView extends JPanel {
    
    private JLabel screen;
    private JLabel bookedSeats;
    private JButton book;
    private JButton back;
    private HashMap<String,JButton> seatButtons; //int seat code key, button value
    private List<String> selectedSeats;

    public SeatBookView(int width, int height, boolean[][] bookings) {
        setLayout(null);

        seatButtons = new HashMap();
        selectedSeats = new ArrayList();
        
        //Iterate through rows of cinema
        for (int i = 0; i <= height; i++) {
            int y = 0;
            char row = (char) (64 + i);
            
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
                    if(bookings[j][i-1]) {
                        button.setEnabled(false);
                    }
                    
                    add(button);
                    
                    String seat = "" + row + seatNum; //seat code (row and column)
                    seatButtons.put(seat, button); //store button in hash map
                }
            }
        }
        
        screen = new JLabel("SCREEN");
        screen.setSize(100, 20);
        screen.setLocation(200, 40 + (height * 30));
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
    }
    
    public JButton getBook() {
        return book;
    }
    
    public JButton getBack() {
        return back;
    }
    
    public HashMap<String,JButton> getSeatButtons() {
        return seatButtons;
    }
    
    public JLabel getBookedSeats() {
        return bookedSeats;
    }
    
    public List<String> getSelectedSeats() {
        return selectedSeats;
    }
}
