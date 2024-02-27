package org.services;

import org.entities.Enterprise;
import org.util.RventDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseService implements IEnterpriseService{
    private final Connection connection;
    public EnterpriseService() {connection = RventDB.getInstance().getConnection;}
    UserService userService=new UserService();
    @Override
    public void register(Enterprise enterprise, String confirmedPassword) throws SQLException {
        if (!enterprise.getPassword().equals(confirmedPassword)) {
            throw new IllegalArgumentException("Password and confirmed password do not match.");
        }

        if (emailExists(enterprise.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        String sql = "INSERT INTO enterprise (fullName, description, email, password) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, enterprise.getFullName());
            preparedStatement.setString(2, enterprise.getDescription());
            preparedStatement.setString(3, enterprise.getEmail());
            preparedStatement.setString(4, userService.hashPassword(enterprise.getPassword()));
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void updateEnterprise(Enterprise enterprise) throws SQLException {

        if (userService.emailExists(enterprise.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        String sql = "update enterprise set fullName=? , description=? , email=? , password=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,enterprise.getFullName());
        preparedStatement.setString(2,enterprise.getDescription());
        preparedStatement.setString(3,enterprise.getEmail());
        preparedStatement.setString(4, userService.hashPassword(enterprise.getPassword()));
        preparedStatement.setObject(5, enterprise.getId());
        preparedStatement.executeUpdate();
    }
    @Override
    public void deleteEnterprise(int id) throws SQLException {
        String sql = "DELETE FROM enterprise WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,id);
        preparedStatement.executeUpdate();
    }
    @Override
    public List<Enterprise> getAllEnterprise() throws SQLException {
        List<Enterprise> enterprises = new ArrayList<>();
        String sql = "SELECT * FROM enterprise";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id =resultSet.getInt("id");
                String name = resultSet.getString("FullName");
                String familyName = resultSet.getString("description");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                Enterprise enterprise = new Enterprise(id, name, familyName, email, password);
                enterprises.add(enterprise);
            }
        }

        return enterprises;
    }
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM enterprise WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
}
