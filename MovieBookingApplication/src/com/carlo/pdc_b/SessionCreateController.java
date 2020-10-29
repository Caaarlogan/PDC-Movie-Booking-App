package com.carlo.pdc_b;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Carlo Carbonilla
 */
public class SessionCreateController extends SessionController {

    private SessionCreateView view; //The view that shows the state of the model

    public SessionCreateController() {
        super();

        view = new SessionCreateView(model);
        
        //When location is changed, update cinemas
        view.getLocationSelect().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                view.updateCinemas();
            }
        });
        
        //When create session button is pushed, see if you can create the session
        view.getCreate().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String response = createSession();
                view.getError().setText(response);
            }
        });
        
        add(view);
    }
    
    private String createSession() {
        int hour = view.getSelectedHour();
        int minute = view.getSelectedMinute();
        
        if(hour==-1 || minute==-1) {
            return "Invalid hours/minutes";
        }
        else {
            LocalDate selectedDate = view.getSelectedDate();
            LocalTime selectedTime = LocalTime.of(hour, minute);
            LocalTime timeNow = LocalTime.now();
            
            //check if time is in the past
            if(selectedDate.equals(LocalDate.now()) && selectedTime.compareTo(timeNow) < 0) {
                return "Session can't be in the past";
            }
            else {
                Movie selectedMovie = view.getSelectedMovie();
                LocalTime timeTo = selectedTime.plusHours(selectedMovie.getHourLength());
                timeTo = timeTo.plusMinutes(selectedMovie.getMinuteLength());
                
                if(selectedTime.compareTo(LocalTime.MIDNIGHT) < 0 && timeTo.compareTo(LocalTime.MIDNIGHT) >= 0) {
                    return "Finishing time can't be past 23:59";
                }
                else {
                    Location selectedLocation = view.getSelectedLocation();
                    Cinema selectedCinema = view.getSelectedCinema();
                    model.addSession(selectedLocation, selectedDate, selectedMovie, selectedCinema, selectedTime, timeTo);
                    
                    return "Session created successfully";
                }
            }
        }
    }
}
