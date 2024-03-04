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


        UserService userService = new UserService();
//        try{
//            userService.addOrganizer(new User("foulen11","falten111","f5oulen@falten","foulen007", LocalDate.of(1995, 8, 12)));
//
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//
//
//        try{
//            System.out.println(userService.getAllUsers());
//
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
          userService.register(new User("rania","rebai","raniarebai561252@gmail.com","Test123456", LocalDate.of(2000,2,2),28751504),"Test123456");

//           userService.authenticateUser("aaaa5555@aaa","00087");
//
//        EnterpriseService enterpriseService = new EnterpriseService();
//        enterpriseService.register(new Enterprise("aaa","aaaa","x@cx","aaaw"),"aaaw");
//        User user = new User("John", "Doe", "john@example1.com", "password123", LocalDate.of(1990, 5, 15), 28751504);
//
//        // Create an instance of your class
//        UserService instance = new UserService();

//        try {
//            // Call the register method
//            instance.register(user, "password123");
//
//            // If the registration is successful, it means the user is inserted into the database
//            System.out.println("User registered successfully.");
//        } catch (SQLException e) {
//            // Handle any SQL exceptions
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            // Handle password mismatch or email already registered exceptions
//            e.printStackTrace();
//        } catch (IllegalStateException e) {
//            // Handle SMS sending failure exception
//            e.printStackTrace();
//        }
      }



}

