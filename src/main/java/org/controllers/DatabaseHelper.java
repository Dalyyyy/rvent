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
import org.entities.Tickets;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/javaprojectdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static List<Tickets> getTickets() {
        List<Tickets> tickets = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int totalPrice = resultSet.getInt("totprix");
                int childTickets = resultSet.getInt("nbrchild");
                int seniorTickets = resultSet.getInt("nbrsen");
                int adultTickets = resultSet.getInt("nbradul");
                Time time = resultSet.getTime("time");
                Date date = resultSet.getDate("date");
                Tickets ticket = new Tickets(id, totalPrice, childTickets, seniorTickets, adultTickets, date, time);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }
    public static boolean deleteTicket(int ticketId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM tickets WHERE id = ?")) {
            statement.setInt(1, ticketId);
            statement.executeUpdate();
        }
        return false;
    }
}



