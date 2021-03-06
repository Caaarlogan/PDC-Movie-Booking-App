package com.carlo.pdc_b;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Template GUI that's both used for session creation and selection
 * @author Carlo Carbonilla
 */

public class SessionView extends JPanel implements Observer{
    protected JLabel location;
    protected JLabel movie;
    protected JLabel date;
    protected JLabel error; //label used for error messages
    protected JComboBox locationSelect; //drop downs to select location,
    protected JComboBox movieSelect; //movie
    protected JComboBox dateSelect; //and date of session
    protected MovieBookingModel model; //The model to which this view is attached
    protected String[] locations; //arrays to store string of locations
    protected String[] movies; // and movies
    protected String[] dates;
    protected DateTimeFormatter dateFormat; //format date is printed in
    
    public SessionView(MovieBookingModel model) {
        setLayout(null);
        
        dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        
        this.model = model;
        
        updateDates();
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
        
        dateSelect = new JComboBox(dates);
        dateSelect.setSize(300,20);
        dateSelect.setLocation(75,40);
        add(dateSelect);
        
        movieSelect = new JComboBox(movies);
        movieSelect.setSize(300,20);
        movieSelect.setLocation(75,70);
        add(movieSelect);
        
        error = new JLabel("");
        error.setSize(500,50);
        error.setLocation(10,320);
        add(error);
    }
    
    //Hook method
    public void update(Observable model, Object arg) {
        
    }
    
    //Populate dates with days from today up to a week later
    protected void updateDates() {
        dates = new String[7];
        LocalDate dateNow = LocalDate.now();
        
        for(int i=0; i<7; i++) {
            dates[i] = dateNow.plusDays(i).format(dateFormat);
        }
    }
    
    //Populate array with locations from data model
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
    
    //Populate arrays with movies from data model
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
    
    public JComboBox getLocationSelect() {
        return locationSelect;
    }
    
    public JComboBox getDateSelect() {
        return dateSelect;
    }
    
    public JComboBox getMovieSelect() {
        return movieSelect;
    }
    
    public Location getSelectedLocation() {
        int i = locationSelect.getSelectedIndex();
        HashMap<Integer,Location> locationMap = model.getLocations();
        return locationMap.get(i+1);
    }
    
    public Movie getSelectedMovie() {
        int i = movieSelect.getSelectedIndex();
        HashMap<Integer,Movie> movieMap = model.getMovies();
        return movieMap.get(i+1);
    }
    
    public LocalDate getSelectedDate() {
        int i = dateSelect.getSelectedIndex();
        return LocalDate.now().plusDays(i);
    }
    
    public JLabel getError() {
        return error;
    }
}
