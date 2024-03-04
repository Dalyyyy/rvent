package org.services;

import org.entities.Event;
import org.entities.Reclamation;
import org.entities.User;
import org.util.RventDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService implements IReclamationService{
    private final Connection connection;
    public ReclamationService() {connection = RventDB.getInstance().getConnection;}
    @Override
    public void createReclamation(User user, Event event, String title, String description) throws SQLException {
        if (user == null || event == null) {
            throw new IllegalArgumentException("User and event must be provided.");
        }

        String sql = "INSERT INTO reclamation (title, description, user_id, event_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, user.getId());
            preparedStatement.setInt(4, event.getId());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public List<Reclamation> getReclamationsByEventId(int eventId) throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        String sql = "SELECT title, description FROM reclamation WHERE event_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Reclamation reclamation = new Reclamation();
                    reclamation.setTitle(resultSet.getString("title"));
                    reclamation.setDescription(resultSet.getString("description"));
                    reclamations.add(reclamation);
                }
            }
        }
        return reclamations;
    }
}
