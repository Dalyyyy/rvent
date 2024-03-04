package org.services;

import org.entities.Event;
import org.entities.Role;
import org.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface IUserService <U>{

    void addUser(User user, Role role) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(int id) throws SQLException;

    List<User> getAllUsers() throws SQLException;
    public String[] authenticateUser(String email, String password) throws SQLException;
    public  void register(User user, String confirmedPassword) throws SQLException;
}
