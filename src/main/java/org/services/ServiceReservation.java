package org.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.entities.Reservation;
import org.entities.Tickets;
import org.util.RventDB;

public class ServiceReservation implements CRUD<Reservation > {
    private Connection getConnection;
    public ServiceReservation() {
        getConnection = RventDB.getInstance().getConnection;
    }


    /* public void insertOne(Reservation reservation) throws SQLException {
         String req = "INSERT INTO reservation`(fullName`, eventName, date,`time`) VALUES (?, ?, ?, ?)";
       //  PreparedStatement st = getConnection.prepareStatement(req);
         PreparedStatement st = cnx.prepareStatement(req);

         st.setString(1, reservation.getFullName());
         st.setString(2, reservation.getEventName());
         st.setDate(3, new Date(reservation.getDate().atTime())); // Convert java.util.Date to java.sql.Date
        // st.setTime(4, reservation.getTime());
         st.setTime(4, Time.valueOf(reservation.getTime()));
         st.executeUpdate();
     }*/
    public void insertOne(Reservation reservation) throws SQLException {
        String req = "INSERT INTO reservation (fullName, eventName, date, time) VALUES (?, ?, ?, ?)";
        PreparedStatement st = getConnection.prepareStatement(req);

        st.setString(1, reservation.getFullName());
        st.setString(2, reservation.getEventName());
        st.setDate(3, java.sql.Date.valueOf(reservation.getDate())); // Convert LocalDate to java.sql.Date
        st.setTime(4, java.sql.Time.valueOf(reservation.getTime())); // Convert LocalTime to java.sql.Time

        st.executeUpdate();
    }




    @Override
    public void updateOne(Reservation reservation) throws SQLException {
        String req = "UPDATE reservation SET id=?, FullName=?,  eventName=?, date=?, time=? WHERE id=?";
        PreparedStatement st = getConnection.prepareStatement(req);
        st.setInt(1, reservation.getId());
        st.setString(2, reservation.getFullName());
        st.setString(3, reservation.getFullName());
        st.setDate(4, java.sql.Date.valueOf(reservation.getDate()));
        st.setTime(5, java.sql.Time.valueOf(reservation.getTime()));
        st.setInt(6, reservation.getId()); // Utilisez l'identifiant pour filtrer l'enregistrement à mettre à jour
        st.executeUpdate();
    }


    @Override
    public void deleteOne(Reservation reservation) throws SQLException {
        String req = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement st = getConnection.prepareStatement(req)) {
            st.setInt(1, reservation.getId());
            st.executeUpdate();
        }
    }


  /*  @Override
    public void insertOne(Tickets tickets) throws SQLException {

    }

    @Override
    public void updateOne(Tickets tickets) throws SQLException {

    }

    @Override
    public void deleteOne(Tickets tickets) throws SQLException {

    }*/

    @Override
    public List<Reservation> selectAll() throws SQLException {
        List<Reservation> ResList = new ArrayList<>();
        String req = "SELECT * FROM reservation";
        Statement st =getConnection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Reservation Res = new Reservation();
            Res.setId(rs.getInt(1));
            Res.setFullName(rs.getString(2));
            Res.setEventName(rs.getString(3)); // Corrected method call
            Res.setDate(rs.getDate(4).toLocalDate());
            Res.setTime(rs.getTime(5).toLocalTime());
            ResList.add(Res);
            System.out.println("done");
        }
        return ResList;
    }

   /* @Override
    public void insertOne(String seat) throws SQLException {

    }

    @Override
    public void updateOne(String seat, int id) throws SQLException {

    }

    @Override
    public void deleteOne(int id) throws SQLException {

    }*/


}