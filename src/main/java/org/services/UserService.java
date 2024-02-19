package org.services;

import org.entities.Event;
import org.entities.Role;
import org.entities.User;
import org.util.RventDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{
    private Connection connection;
    public UserService(){
        connection = RventDB.getInstance().getConnection;
    }
    @Override
    public boolean authenticateUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    return storedPassword.equals(password);
                }
            }
        }
        return false;
    }
    @Override
    public void register(User user, String confirmedPassword) throws SQLException {
        if (!user.getPassword().equals(confirmedPassword)) {
            throw new IllegalArgumentException("Password and confirmed password do not match.");
        }

        String sql = "INSERT INTO user (name, familyName, email, password, dateBirth, role) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamilyName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, java.sql.Date.valueOf(user.getDateBirth()));
            preparedStatement.setString(6, Role.CLIENT.name());

            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void addOrganizer(User user) throws SQLException {
        String sql = "INSERT INTO user (name, familyName, email, password, dateBirth, role) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamilyName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, java.sql.Date.valueOf(user.getDateBirth()));
            preparedStatement.setString(6, Role.ORGANIZER.name());

            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void addAdmin(User user) throws SQLException {
        String sql = "INSERT INTO user (name, familyName, email, password, dateBirth, role) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamilyName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, java.sql.Date.valueOf(user.getDateBirth()));
            preparedStatement.setString(6, Role.ADMIN.name());

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
        preparedStatement.setObject(6, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id =resultSet.getInt("id");
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

    public int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }


}
