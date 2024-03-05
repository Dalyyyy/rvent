package org.services;

import javafx.collections.ObservableList;
import org.entities.*;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface IEventService <Event> {
    public void addEvent(Event event) throws SQLException;
    public void updateEvent(Event event) throws SQLException;



    public void deleteEvent(int idEvent) throws SQLException;




    public List<Event> getAllEventsByEnterpriseId(int id) throws SQLException;

    public ObservableList<Event> afficher() throws SQLException;
}
