
package org.services;
import org.entities.Tickets;
import org.util.RventDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
public class seviceticket implements CRUD<Tickets> {
    private Connection getConnection;

    public seviceticket() {
        getConnection = RventDB.getInstance().getConnection;
    }

    @Override
    public void insertOne(Tickets tickets) throws SQLException {
        String req = "INSERT INTO `tickets`(`totprix`, `nbrchild`, `nbrsen`, `nbradul`,`date`, `time`) VALUES (?, ?, ?, ?, ? , ?)";
        PreparedStatement st = getConnection.prepareStatement(req);
        // st.setInt(1, tickets.getId());
        st.setInt(1, tickets.getTotprix());
        st.setInt(2, tickets.getNbrchild());
        st.setInt(3, tickets.getNbrsen());
        st.setInt(4, tickets.getNbradul());
        st.setDate(5, new java.sql.Date(tickets.getDate().getTime())); // Convert java.util.Date to java.sql.Date
        st.setTime(6, tickets.getTime());
        st.executeUpdate();
    }

    @Override
    public void updateOne(Tickets tickets) throws SQLException {
        String req = "UPDATE `tickets` SET `totprix`=?, `nbrchild`=?, `nbrsen`=?, `nbradul`=?, `date`=?, `time`=? WHERE `id`=?";
        PreparedStatement st = getConnection.prepareStatement(req);
        st.setInt(1, tickets.getTotprix());
        st.setInt(2, tickets.getNbrchild());
        st.setInt(3, tickets.getNbrsen());
        st.setInt(4, tickets.getNbradul());
        st.setDate(5, new java.sql.Date(tickets.getDate().getTime()));
        st.setTime(6, tickets.getTime());
        //st.setInt(7, tickets.getId());
        st.executeUpdate();
    }

    @Override
    public void deleteOne(Tickets tickets) throws SQLException {
        String req = "DELETE FROM `tickets` WHERE `id`=?";
        PreparedStatement st = getConnection.prepareStatement(req);
        st.setInt(1, tickets.getId());
        st.executeUpdate();
    }

    @Override
    public List<Tickets> selectAll() throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();
        String req = "SELECT * FROM `tickets`";
        Statement st = getConnection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Tickets tickets = new Tickets();
            tickets.setId(rs.getInt("id"));
            tickets.setTotprix(rs.getInt("totprix"));
            tickets.setNbrchild(rs.getInt("nbrchild"));
            tickets.setNbrsen(rs.getInt("nbrsen"));
            tickets.setNbradul(rs.getInt("nbradul"));
            tickets.setDate(rs.getDate("date"));
            tickets.setTime(rs.getTime("time"));
            ticketsList.add(tickets);
        }
        return ticketsList;
    }
/*
    @Override
    public void insertOne(String seat) throws SQLException {

    }

    @Override
    public void updateOne(String seat, int id) throws SQLException {

    }

    @Override
    public void deleteOne(int id) throws SQLException {

    }*/
}


