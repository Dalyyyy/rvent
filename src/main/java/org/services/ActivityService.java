package org.services;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.entities.Activity;
import org.util.RventDB;

import java.sql.*;

public  class ActivityService  implements IActivityService {
    private final Connection connection;

    public ActivityService() {
        connection = RventDB.getInstance().getConnection;
    }

    @Override
    public void ajouter(Activity activity) throws SQLException {
        String sql = "INSERT INTO activity (title, description, status, team, task) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, activity.getTitle());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setBoolean(3, activity.isStatus());
            preparedStatement.setString(4, String.valueOf(activity.getTeam()));
            preparedStatement.setString(5, String.valueOf(activity.getTeam()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error adding account: " + e.getMessage());
        }
    }



    @Override
    public void supprimer(int activityId) throws SQLException {
        String sql = "DELETE FROM activity WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, activityId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Activity activity) throws SQLException {
        String sql = "UPDATE activity SET title = ?, description = ?, status = ?, team = ?, task = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, activity.getTitle());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setBoolean(3, activity.isStatus());
            preparedStatement.setString(4, String.valueOf(activity.getTeam()));
            preparedStatement.setString(5, String.valueOf(activity.getTeam()));
            preparedStatement.setInt(6, activity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Activity> getAllActivities() throws SQLException {
        ObservableList<Activity> ActivityList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM activity";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Activity activity = new Activity();
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                boolean status = resultSet.getBoolean("status");
                String team = resultSet.getString("team");
                String task = resultSet.getString("task");
                ActivityList.add(activity);
            }
        }
        return ActivityList;

}
}
