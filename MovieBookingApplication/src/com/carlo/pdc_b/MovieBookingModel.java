package com.carlo.pdc_b;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Carlo Carbonilla
 */

public class MovieBookingModel{
    private Connection conn;
    public String url = "jdbc:derby://localhost:1527/MovieBookingAppDB;create=true";  //url of the DB host
    public String username = "carlocarbonilla";  //your DB username
    public String password = "18025686";   //your DB password
    private HashMap<Integer,Location> locations;
    private HashMap<Integer,Movie> movies;
    private HashMap<Integer,Session> sessions;
    private static int sessionCounter;
    private DateTimeFormatter dateFormat;
    private DateTimeFormatter timeFormat;
    
    public MovieBookingModel() {
        locations = new HashMap();
        movies = new HashMap();
        sessions = new HashMap();
        
        dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        timeFormat = DateTimeFormatter.ofPattern("H:m");
        
        try {
            this.conn = DriverManager.getConnection(url, username, password);
            updateMovies();
            updateLocations();
            updateCinemas();
            updateSessions();
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized void updateMovies() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM MOVIES");
            
            while(rs.next()) {
                int id = rs.getInt("MOVIE_ID");
                String title = rs.getString("MOVIE_TITLE");
                int hourLength = rs.getInt("MOVIE_HOUR_LENGTH");
                int minuteLength = rs.getInt("MOVIE_MINUTE_LENGTH");
                
                Movie movie = new Movie(id, title, hourLength, minuteLength);
                movies.put(id, movie);
            }
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
                
                Cinema cinema = new Cinema(id, num, width, height);
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
                
                Location location = new Location(id, name);
                
                locations.put(id, location);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void updateSessions() {
        sessionCounter = 1;
        
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM SESSIONS");
            
            while(rs.next()) {
                int id = rs.getInt("SESSION_ID");
                int locationID = rs.getInt("SESSION_LOCATION");
                LocalDate date = rs.getDate("SESSION_DATE").toLocalDate();
                int movieID = rs.getInt("SESSION_MOVIE");
                int cinemaID = rs.getInt("SESSION_ICINEMA");
                LocalTime timeFrom = rs.getTime("SESSION_TIME_FROM").toLocalTime();
                LocalTime timeTo = rs.getTime("SESSION_TIME_TO").toLocalTime();
                
                Location location = locations.get(locationID);
                Movie movie = movies.get(movieID);
                Cinema cinema = location.getCinemas().get(cinemaID);
                
                Session session = new Session(location, date, movie, cinema, timeFrom, timeTo);
                sessions.put(id, session);
                
                sessionCounter++;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void addSession(Location location, LocalDate date, Movie movie, Cinema cinema, LocalTime timeFrom, LocalTime timeTo) {
        
        Session session = new Session(location, date, movie, cinema, timeFrom, timeTo);
        sessions.put(sessionCounter, session);
        
        try {
            Statement statement = conn.createStatement();
            
            statement.executeUpdate("INSERT INTO SESSIONS VALUES ("
                    + sessionCounter + ", " + location.getID() + ", '"
                    + date.format(dateFormat) + "', " + movie.getID() + ", "
                    + cinema.getID() + ", '" + timeFrom.format(timeFormat) + "', '"
                    + timeTo.format(timeFormat) + "')");
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
