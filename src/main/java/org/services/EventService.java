package org.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.entities.Event;
import org.util.RventDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService implements IEventService <Event>{


    private final Connection connection;
    public EventService() {connection = RventDB.getInstance().getConnection;
    }

    @Override
    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO event (eventName,full, description, status, enterpriseName, maxParticipantNbr) VALUES (?,?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setBoolean(2, event.isFull());
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.setBoolean(4, event.isStatus());
            preparedStatement.setString(5, event.getEnterpriseName());
            preparedStatement.setInt(6, event.getMaxParticipantNbr());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error adding event: " + e.getMessage());
        }
    }

    @Override
    public void updateEvent(Event event) throws SQLException {
        String query = "UPDATE event SET eventName=?, description=?, status=?, enterpriseName=?, maxParticipantNbr=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getDescription());
            preparedStatement.setBoolean(3, event.isFull());
            preparedStatement.setString(4, event.getEnterpriseName());
            preparedStatement.setInt(5, event.getMaxParticipantNbr());
            preparedStatement.setInt(6, event.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error editing event: " + e.getMessage());
        }
    }

    @Override
    public void deleteEvent(int id) throws SQLException {
        String sql = "DELETE FROM event WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error deleting event: " + e.getMessage());
        }
    }

    @Override
    public List<Event> getAllEventsByEnterpriseId(int id) throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT eventName, status, maxParticipantNbr FROM event WHERE enterprise_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("eventName");
                    boolean status = resultSet.getBoolean("status");
                    int maxParticipantNbr = resultSet.getInt("maxParticipantNbr");

                    Event event = new Event(-1, false, name, "", status, "", maxParticipantNbr);
                    events.add(event);
                }
            }
        }

        return events;
    }

    @Override
    public ObservableList<Event> afficher() throws SQLException {
        ObservableList<Event> EventList = FXCollections.observableArrayList();
        String query = "SELECT * FROM event"; // Assurez-vous que cette requête SQL est correcte selon votre schéma de base de données

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setFull(resultSet.getBoolean("full"));
                event.setEventName(resultSet.getString("eventName"));
                event.setDescription(resultSet.getString("description"));
                event.setStatus(resultSet.getBoolean("status"));
                event.setEnterpriseName(resultSet.getString("enterpriseName"));
                event.setMaxParticipantNbr(resultSet.getInt("maxParticipantNbr"));
                //sponsoring.setTetab(Sponsoring.Tetab.fromString(resultSet.getString("tetab")));
                EventList.add(event);
            }
        }
        return EventList;    }

}



