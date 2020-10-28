package com.carlo.pdc_b;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Carlo Carbonilla
 */
public class SeatSelectPanel extends JPanel {
    private JLabel bookedSeats;
    private JButton book;
    
    public SeatSelectPanel() {
        setLayout(null);
        
        int height = 10;
        int width = 10;
        
        for (int i = 0; i < height; i++) {
            int y = 10 + (i*30);
            JLabel letter = new JLabel("" + (char)(65+i));
            letter.setSize(75,20);
            letter.setLocation(10, y);
            add(letter);
            
            for (int j = 0; j < width; j++) {
                int seatNum = j+1;
                JButton button = new JButton("" + seatNum);
                button.setSize(48, 20);
                
                int x = 30 + (j*60);

                button.setLocation(x, y);
                add(button);
            }
        }
        
        bookedSeats = new JLabel("Booked Seats: ");
        bookedSeats.setSize(500,20);
        bookedSeats.setLocation(10, 10 + (height*30));
        add(bookedSeats);
        
        book = new JButton("Book");
        book.setSize(100,20);
        book.setLocation(10, 35 + (height*30));
        add(book);
    }
}
