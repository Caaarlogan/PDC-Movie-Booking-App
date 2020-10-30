package com.carlo.pdc_b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Model of movie booking application, where the data is stored, read, and
 * written.
 * @author Carlo Carbonilla
 */
public class MovieBookingModel extends Observable implements Runnable{

    private Connection conn;
    public String url = "jdbc:derby:MovieBookingAppDB;create=true"; //url of embedded DB
    public String username = "carlocarbonilla"; //your DB username
    public String password = "18025686"; //your DB password
    private HashMap<Integer, Location> locations; //id key location value
    private HashMap<Integer, Movie> movies; //id key movie value
    private HashMap<Integer, Session> sessions; //id key session value
    private static int sessionCounter; //gets the latest session ID
    private static int bookingCounter; //gets the latest booking ID
    private DateTimeFormatter dateFormat;
    private DateTimeFormatter timeFormat;

    public MovieBookingModel() {
        locations = new HashMap();
        movies = new HashMap();
        sessions = new HashMap();

        dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        timeFormat = DateTimeFormatter.ofPattern("HH:mm");

        try {
            this.conn = DriverManager.getConnection(url, username, password); //connect to DB
            
            //Initialize and populate data and information for model
            initializeMovies();
            initializeLocations();
            initializeCinemas();
            initializeSessions();
            initializeBookings();
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Run method for notifying observers if model has changed.
     */
    public void run() {
        while(true) {
            if(hasChanged()) {
                notifyObservers();
                clearChanged();
            }
        }
    }
    
    /**
     * Get movies from database and populate hash map
     */
    private void initializeMovies() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM MOVIES");

            while (rs.next()) {
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
    
    /**
     * Get cinemas from database and populate hash map
     */
    private void initializeCinemas() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CINEMAS");

            while (rs.next()) {
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
    
    /**
     * Get locations from database and populate hash map
     */
    private void initializeLocations() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM LOCATIONS");

            while (rs.next()) {
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
    
    /**
     * Get sessions from database and populate hash map
     */
    public void initializeSessions() {
        sessionCounter = 1;
        
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM SESSIONS");

            while (rs.next()) {
                int id = rs.getInt("SESSION_ID");
                int locationID = rs.getInt("SESSION_LOCATION");
                LocalDate date = rs.getDate("SESSION_DATE").toLocalDate();
                int movieID = rs.getInt("SESSION_MOVIE");
                int cinemaID = rs.getInt("SESSION_CINEMA");
                LocalTime timeFrom = rs.getTime("SESSION_TIME_FROM").toLocalTime();
                LocalTime timeTo = rs.getTime("SESSION_TIME_TO").toLocalTime();

                Location location = locations.get(locationID);
                Movie movie = movies.get(movieID);
                Cinema cinema = location.getCinemas().get(cinemaID);

                Session session = new Session(id, location, date, movie, cinema, timeFrom, timeTo);
                sessions.put(id, session);

                sessionCounter++; //increment session ID
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Get bookings from database and fill hash map
    public void initializeBookings() {
        bookingCounter = 1;

        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM BOOKINGS");

            while (rs.next()) {
                int id = rs.getInt("BOOKING_ID");
                int sessionID = rs.getInt("BOOKING_SESSION");
                int column = rs.getInt("BOOKING_COLUMN");
                String string = rs.getString("BOOKING_ROW");
                char letter = string.charAt(0); //Convert string to char
                int row = letter - 'A'; //Convert char to int

                Session session = sessions.get(sessionID);
                session.book(row, column - 1);

                bookingCounter++;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Get sessions filtered with specified location, date, movie, and return list
    //Used for session booking application
    public List<Session> getSessionsResults(Location location, LocalDate date, Movie movie) {
        List<Session> sessionResults = new ArrayList();

        try {
            Statement statement = conn.createStatement();

            String query = "SELECT SESSION_ID FROM SESSIONS WHERE\n"
                    + "SESSION_LOCATION=? AND SESSION_DATE=? AND SESSION_MOVIE=?";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, location.getID() + ""); //location ID
            ps.setString(2, date.format(dateFormat)); //date
            ps.setString(3, movie.getID() + ""); //movie ID

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("SESSION_ID");
                Session session = sessions.get(id);
                sessionResults.add(session);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessionResults;
    }
    
    //Add session to hash map and database with the given session attributes
    public void addSession(Location location, LocalDate date, Movie movie, Cinema cinema, LocalTime timeFrom, LocalTime timeTo) {

        Session session = new Session(sessionCounter, location, date, movie, cinema, timeFrom, timeTo);
        sessions.put(sessionCounter, session);

        try {
            Statement statement = conn.createStatement();

            String query = "INSERT INTO SESSIONS VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, sessionCounter + ""); //session ID
            ps.setString(2, location.getID() + ""); //location ID
            ps.setString(3, date.format(dateFormat)); //date
            ps.setString(4, movie.getID() + ""); //movie ID
            ps.setString(5, cinema.getID() + ""); //cinema Num
            ps.setString(6, timeFrom.format(timeFormat)); //starting time
            ps.setString(7, timeTo.format(timeFormat)); //finishing time

            ps.executeUpdate();
            
            sessionCounter++; //increment sessionID
            
            setChanged(); //database has been modified
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Book seat with given session ID, row, and column
     *
     * @param sessionID
     * @param row
     * @param column
     */
    public void bookSeat(int sessionID, char row, int column) {
        Session session = sessions.get(sessionID);
        session.book(row - 65, column - 1); //turn seat code to array index

        try {
            Statement statement = conn.createStatement();

            String query = "INSERT INTO BOOKINGS VALUES (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, bookingCounter + "");
            ps.setString(2, sessionID + "");
            ps.setString(3, row + "");
            ps.setString(4, column + "");

            ps.executeUpdate();

            bookingCounter++;
            
            setChanged();
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Check if sessions overlap each other, searching for sessions with the
     * same location, date, and cinema
     *
     * @param location
     * @param date
     * @param cinema
     * @param start1
     * @param end1
     * @return true if overlap, false if no overlap
     */
    public boolean checkOverlap(Location location, LocalDate date, Cinema cinema, LocalTime start1, LocalTime end1) {
        boolean overlap = false;

        try {
            Statement statement = conn.createStatement();

            String query = "SELECT SESSION_ID FROM SESSIONS WHERE\n"
                    + "SESSION_LOCATION=? AND SESSION_DATE=? AND SESSION_CINEMA=?";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, location.getID() + "");
            ps.setString(2, date.format(dateFormat));
            ps.setString(3, cinema.getID() + "");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("SESSION_ID");
                Session result = sessions.get(id);

                LocalTime start2 = result.getTimeFrom();
                LocalTime end2 = result.getTimeTo();

                //Check for overlap or equal session times
                if (!start1.isAfter(end2) && !start2.isAfter(end1)) {
                    overlap = true;
                    break;
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MovieBookingModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return overlap;
    }

    public HashMap<Integer, Location> getLocations() {
        return locations;
    }

    public HashMap<Integer, Movie> getMovies() {
        return movies;
    }

    public HashMap<Integer, Session> getSessions() {
        return sessions;
    }
}
