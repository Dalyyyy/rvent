package org.example.services;

import org.example.entities.User;
import org.example.util.RventDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{
    private Connection connection;
    public UserService(){
        connection = RventDB.getInstance().getConnection;
    }
    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (name, familyName, email, password, dateBirth) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamilyName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, java.sql.Date.valueOf(user.getDateBirth()));

            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "update user set name=? , familyName=? , email=? , password=? , dateBirth=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getFamilyName());
        preparedStatement.setString(3,user.getEmail());
        preparedStatement.setString(4,user.getPassword());
        preparedStatement.setDate(5, java.sql.Date.valueOf(user.getDateBirth()));
        preparedStatement.setInt(6, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String familyName = resultSet.getString("familyName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Date dateBirth = resultSet.getDate("dateBirth");
                int reservationNbr = resultSet.getInt("reservationNbr");

                User user = new User(id, name, familyName, email, password, dateBirth.toLocalDate(), reservationNbr);
                users.add(user);
            }
        }

        return users;
    }

}
