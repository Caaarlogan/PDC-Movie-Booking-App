package com.carlo.pdc_b;

import javax.swing.JFrame;

/**
 * @author Carlo Carbonilla
 */
public class MovieBookingView extends JFrame {
    public static void main(String[] args) {
        MovieBookingView frame = new MovieBookingView();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
//        frame.setResizable(false);

        SeatSelectPanel ssp = new SeatSelectPanel();
        frame.add(ssp);

        frame.setVisible(true);
    }
}
