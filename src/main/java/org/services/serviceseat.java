package org.services;

import org.entities.seeats;
import org.util.RventDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class serviceseat implements CRUD<seeats>{
    private Connection getConnection;

    public serviceseat() {
        getConnection = RventDB.getInstance().getConnection;}

    @Override
    public void insertOne(seeats seat) throws SQLException {
        String req = "INSERT INTO `seats`(`seat_name`) VALUES (?)";
        PreparedStatement st = getConnection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, seat.getSeat());
        st.executeUpdate();
        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            seat.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("Failed to insert seat, no ID obtained.");
        }
    }

    @Override
    public void updateOne(seeats seat) throws SQLException {
        String req = "UPDATE `seats` SET `seat_name`=? WHERE `seat_id`=?";
        PreparedStatement st = getConnection.prepareStatement(req);
        st.setString(1, seat.getSeat());
        st.setInt(2, seat.getId());
        st.executeUpdate();
    }

    @Override
    public void deleteOne(seeats seat) throws SQLException {
        String req = "DELETE FROM `seats` WHERE `seat_id`=?";
        PreparedStatement st = getConnection.prepareStatement(req);
        st.setInt(1, seat.getId());
        st.executeUpdate();
    }

    @Override
    public List<seeats> selectAll() throws SQLException {
        List<seeats> seatList = new ArrayList<>();
        String req = "SELECT `seat_id`, `seat_name` FROM `seats`";
        Statement st = getConnection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            seeats seat = new seeats();
            seat.setId(rs.getInt("seat_id"));
            seat.setSeat(rs.getString("seat_name"));
            seatList.add(seat);
        }
        return seatList;
    }



}
