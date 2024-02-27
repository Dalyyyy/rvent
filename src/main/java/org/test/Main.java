package org.test;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import org.entities.Reservation;
import org.services.ServiceReservation;
import org.entities.seeats;
import org.services.serviceseat;

import org.util.RventDB;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

        RventDB cn1 = RventDB.getInstance();
        seeats seat =new seeats(1,"aaa");
        serviceseat st=new serviceseat();
       // LocalDate date = LocalDate.of (2024, 2, 1);
       // LocalTime time = LocalTime.of (1, 20, 20);
       // Reservation Res = new Reservation("hhh", "fff",Date.valueOf(date), Time.valueOf(time)); // Corrected instantiation
        //ServiceReservation SR = new ServiceReservation();
        try {
            st.insertOne(seat);
        } catch (SQLException e) {
            System.out.println("Erreur" + e.getMessage());
        }}}
        /*try {
            SL.insertOne(l1);
        } catch (SQLException e) {
            System.out.println("Erreur" + e.getMessage());
        }
        try {
            Sli.insertOne(Liv);
        } catch (SQLException e) {
            System.out.println("Erreur" + e.getMessage());
        }
        try {
            SL.insertOne(l3);
        } catch (SQLException e) {
            System.out.println("Erreur" + e.getMessage());
        }
      try {
            SR.deleteOne(Res);
            System.out.println("reservation supprimée* : " + Res);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la resvervation : " + e.getMessage());
        }
    }*/

