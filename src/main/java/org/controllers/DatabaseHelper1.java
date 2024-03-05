package org.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.entities.Reservation;

public class DatabaseHelper1 {

    private static final String URL = "jdbc:mysql://localhost:3306/javaprojectdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static List<Reservation> getReservations() {
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reservation");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                String eventName = resultSet.getString("eventName");
                Time time = resultSet.getTime("time");
                Date date = resultSet.getDate("date");

                Reservation reservation = new Reservation(id, fullName, eventName, ((java.sql.Date) date).toLocalDate(), time.toLocalTime());
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public static boolean deleteReservation(int reservationId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM reservation WHERE id = ?")) {
            statement.setInt(1, reservationId);
            statement.executeUpdate();
        }
        return false;
    }
}
