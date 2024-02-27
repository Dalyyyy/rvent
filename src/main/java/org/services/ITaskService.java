package org.services;

import org.entities.Task;
import org.entities.Team;

import java.sql.SQLException;
import java.util.List;

public interface ITaskService<U> {

    void addTask(Task task) throws SQLException;

    void updateTask(Task task) throws SQLException;

    void deleteTask(int taskId) throws SQLException;

    List<Task> getAllTasks() throws SQLException;
}
