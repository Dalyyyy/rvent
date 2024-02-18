package org.services;

import org.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface IUserService <U>{
    void addOrganizer(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(int id) throws SQLException;

    List<User> getAllUsers() throws SQLException;
    public boolean authenticateUser(String email, String password) throws SQLException;
    public  void register(User user, String confirmedPassword) throws SQLException;
}
