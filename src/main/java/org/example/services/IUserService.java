package org.example.services;

import org.example.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService <U>{
    void addUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}
