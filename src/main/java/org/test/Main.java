package org.test;

import org.entities.Event;
import org.entities.User;
import org.services.EventService;
import org.services.UserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        EventService e = new EventService();
       //e.addEvent(new Event("3ersii",true,LocalDate.of(2024,03,27), LocalTime.of(21,10),100));

    }
}