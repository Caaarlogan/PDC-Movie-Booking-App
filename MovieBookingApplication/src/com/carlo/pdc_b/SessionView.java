package com.carlo.pdc_b;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Template GUI that's both used for session creation and selection
 * @author Carlo Carbonilla
 */

public class SessionView extends JPanel{
    protected JLabel location;
    protected JLabel movie;
    protected JLabel date;
    protected JComboBox locationSelect;
    protected JComboBox movieSelect;
    protected JTextField dateSelect;
    protected MovieBookingModel model; //The model to which this view is attached
    protected String[] locations;
    protected String[] movies;
    protected DateTimeFormatter dateFormat; //format date is printed in
    
    public SessionView(MovieBookingModel model) {
        setLayout(null);
        
        dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        
        this.model = model;
        
        updateLocations();
        updateMovies();
        
        location = new JLabel("Location: ");
        location.setSize(300,20);
        location.setLocation(10,10);
        add(location);
        
        date = new JLabel("Date: ");
        date.setSize(300,20);
        date.setLocation(10,40);
        add(date);
        
        movie = new JLabel("Movie: ");
        movie.setSize(300,20);
        movie.setLocation(10,70);
        add(movie);
        
        locationSelect = new JComboBox(locations);
        locationSelect.setSize(300,20);
        locationSelect.setLocation(75,10);
        add(locationSelect);
        
        LocalDate dateNow = LocalDate.now();
        String date = dateNow.format(dateFormat);
        
        dateSelect = new JTextField(date);
        dateSelect.setSize(300,20);
        dateSelect.setLocation(75,40);
        add(dateSelect);
        
        movieSelect = new JComboBox(movies);
        movieSelect.setSize(300,20);
        movieSelect.setLocation(75,70);
        add(movieSelect);
    }
    
    protected void updateLocations() {
        HashMap<Integer,Location> locationMap = model.getLocations();
        Set<Integer> locationIDs = locationMap.keySet();
        int size = locationIDs.size();
        
        locations = new String[size];
        
        int i = 0;
        
        for(int id : locationIDs) {
            locations[i] = locationMap.get(id).getName();
            i++;
        }
    }
    
    protected void updateMovies() {
        HashMap<Integer,Movie> movieMap = model.getMovies();
        Set<Integer> movieIDs = movieMap.keySet();
        int size = movieIDs.size();
        
        movies = new String[size];
        
        int i = 0;
        
        for(int id : movieIDs) {
            movies[i] = movieMap.get(id).getTitle();
            i++;
        }
    }
}
