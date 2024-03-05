package org.services;

import org.entities.Event;

import java.sql.SQLException;
import java.util.List;

public interface IMediaProfile {


    public void addEvent(Event event) throws SQLException;
    public void updateEvent(Event event) throws SQLException;



    public void deleteEvent(int idEvent) throws SQLException;




    public List<Event> getAllEventsByEnterpriseId(int id) throws SQLException;

    List<Event> getAllEvents() throws SQLException;
}
