/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlo.pdc_b;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit Tests for session creator application
 * @author Carlo Carbonilla
 */
public class SessionCreateControllerTest {
    
    public SessionCreateControllerTest() {
    }
    
    //Test what happens when you input time of less than zero
    @Test
    public void hourMinuteLessThanZero() {
        SessionCreateController scc = new SessionCreateController();
        LocalDate dateNow = LocalDate.now();
        String output = scc.createSession(-1, -1, dateNow);
        assertEquals(output, "Invalid hours/minutes");
    }
    
    //Test what happens when you input time of greater than 23 hours and
    //59 minutes
    @Test
    public void hourMinuteGreaterThanMax() {
        SessionCreateController scc = new SessionCreateController();
        LocalDate dateNow = LocalDate.now();
        String output = scc.createSession(24, 60, dateNow);
        assertEquals(output, "Invalid hours/minutes");
    }
    
    //Test when creating session in the past
    @Test
    public void pastSession() {
        SessionCreateController scc = new SessionCreateController();
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        int hour = timeNow.getHour() - 1;
        int minute = timeNow.getMinute() - 1;
        String output = scc.createSession(hour, minute, dateNow);
        assertEquals(output, "Session can't be in the past");
    }
    
    //Test when creating session that goes to the next day
    @Test
    public void sessionGoingToNextDay() {
        SessionCreateController scc = new SessionCreateController();
        LocalDate dateNow = LocalDate.now();
        String output = scc.createSession(23, 59, dateNow);
        assertEquals(output, "Finishing time can't be past 23:59");
    }
    
    //Test creating a session in midnight
    @Test
    public void midnightSession() {
        SessionCreateController scc = new SessionCreateController();
        LocalDate date = LocalDate.now().plusDays(1);
        String output = scc.createSession(00, 00, date);
        assertEquals(output, "Session created successfully");
    }
    
    //This session should overlap with the previous midnight session
    @Test
    public void overlappingSessions() {
        SessionCreateController scc = new SessionCreateController();
        LocalDate date = LocalDate.now().plusDays(1);
        String output = scc.createSession(01, 00, date);
        assertEquals(output, "This session overlaps with another session");
    }
    
    //This session should be equal with the previous midnight session
    @Test
    public void equalSessions() {
        SessionCreateController scc = new SessionCreateController();
        LocalDate date = LocalDate.now().plusDays(1);
        String output = scc.createSession(00, 00, date);
        assertEquals(output, "This session overlaps with another session");
    }
}
