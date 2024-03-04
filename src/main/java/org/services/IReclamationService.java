package org.services;

import org.entities.Event;
import org.entities.Reclamation;
import org.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IReclamationService <R>{
    public void createReclamation(User user, Event event, String title, String description) throws SQLException;
    public List<Reclamation> getReclamationsByEventId(int eventId) throws SQLException;
}
