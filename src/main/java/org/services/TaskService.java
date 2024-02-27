package org.services;

import org.entities.Activity;
import org.entities.Task;
import org.entities.User;
import org.util.RventDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements ITaskService {
    private final Connection connection;
    public TaskService() {connection = RventDB.getInstance().getConnection;}
    @Override
    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO task(taskName, description, status, activity, oc) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getNametask());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setBoolean(3, task.isStatus());
            preparedStatement.setString(4, String.valueOf(task.getActivity()));
            preparedStatement.setString(5, String.valueOf(task.getOc()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE task SET taskName = ?, description = ?, status = ?, activity = ?, oc = ?, team = ? WHERE taskId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, task.getNametask());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setBoolean(3, task.isStatus());
            preparedStatement.setString(4, String.valueOf(task.getActivity()));
            preparedStatement.setString(5, String.valueOf(task.getOc()));
            preparedStatement.setInt(7, task.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int taskId) throws SQLException{
        String sql = "DELETE FROM task WHERE taskId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int taskId = resultSet.getInt("taskId");
                String taskName = resultSet.getString("taskName");
                String description = resultSet.getString("description");
                boolean status = resultSet.getBoolean("status");
                String activity = resultSet.getString("activity");
                String oc = resultSet.getString("oc");


                Task task = new Task(taskId, taskName, description, status, activity, oc);
                tasks.add(task);
            }
        }


        return tasks;
    }

}

