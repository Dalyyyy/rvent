package org.example.test;

import org.example.entities.User;
import org.example.services.UserService;
import org.example.util.RventDB;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();
        try{
            userService.addUser(new User("foulen11","falten111","f5oulen@falten","foulen007", LocalDate.of(1995, 8, 12)));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        try{
            userService.updateUser(new User(3,"try it", "to make it", "tryit.tom@keit", "yeah", LocalDate.of(1995, 8, 12), 0));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try{
            userService.deleteUser(4);
            userService.deleteUser(5);
            userService.deleteUser(6);

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