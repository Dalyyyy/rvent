package org.test;

import org.entities.User;
import org.services.UserService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();
        try{
            userService.addOrganizer(new User("foulen11","falten111","f5oulen@falten","foulen007", LocalDate.of(1995, 8, 12)));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        try{
            System.out.println(userService.getAllUsers());

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}