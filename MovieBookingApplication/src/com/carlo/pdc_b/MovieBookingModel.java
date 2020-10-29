package com.carlo.pdc_b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Carlo Carbonilla
 */

public class MovieBookingModel{
    private Connection conn;
    public String url = "jdbc:derby:MovieBookingAppDB;create=true";  //url of the DB host
    public String username = "carlocarbonilla";  //your DB username
    public String password = "18025686";   //your DB password
    private HashMap<Integer,Location> locations;
    private HashMap<Integer,Movie> movies;
    private HashMap<Integer,Session> sessions;
    private static int sessionCounter;
    public boolean updatedLocations;
    public boolean updatedMovies;
    public boolean updatedSessions;
    
    public MovieBookingModel() {
        locations = new HashMap();
        movies = new HashMap();
        sessions = new HashMap();
        
        updatedLocations = false;
        updatedMovies = false;
        updatedSessions = false;
        
        try {
            this.conn = DriverManager.getConnection(url, username, password);
            updateMovies();
            updateLocations();
            updateCinemas();
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void run() {
//        try {
//            this.conn = DriverManager.getConnection(url, username, password);
//            updateMovies();
//            updateLocations();
//            updateCinemas();
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    private synchronized void updateMovies() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM MOVIES");
            
            while(rs.next()) {
                int id = rs.getInt("MOVIE_ID");
                String title = rs.getString("MOVIE_TITLE");
                int hourLength = rs.getInt("MOVIE_HOUR_LENGTH");
                int minuteLength = rs.getInt("MOVIE_MINUTE_LENGTH");
                
                Movie movie = new Movie(title, hourLength, minuteLength);
                movies.put(id, movie);
            }
            updatedMovies = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized void updateCinemas() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CINEMAS");
            
            while(rs.next()) {
                int id = rs.getInt("CINEMA_ID");
                int num = rs.getInt("CINEMA_NUM");
                int locationID = rs.getInt("CINEMA_LOCATION");
                int height = rs.getInt("CINEMA_HEIGHT");
                int width = rs.getInt("CINEMA_WIDTH");
                
                Cinema cinema = new Cinema(num, width, height);
                Location location = locations.get(locationID);
                location.addCinema(id, cinema);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized void updateLocations() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM LOCATIONS");
            
            while(rs.next()) {
                int id = rs.getInt("LOCATION_ID");
                String name = rs.getString("LOCATION_NAME");
                
                Location location = new Location(name);
                
                locations.put(id, location);
            }
            updatedLocations = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void addSession(Session session) {
        sessions.put(sessionCounter, session);
        
        try {
            Statement statement = conn.createStatement();
            
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sessionCounter++;
    }
    
    public HashMap<Integer,Location> getLocations() {
        return locations;
    }
    
    public HashMap<Integer,Movie> getMovies(){
        return movies;
    }
    
    public HashMap<Integer,Session> getSessions() {
        return sessions;
    }
}
