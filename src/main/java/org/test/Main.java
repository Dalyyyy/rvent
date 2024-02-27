package org.test;

import org.entities.Enterprise;
import org.entities.User;
import org.services.EnterpriseService;
import org.services.UserService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {

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
//        userService.register(new User("qqqw","qqqqq","aaaa@aaa","0007",LocalDate.of(2000,2,2)),"0007");
//        userService.authenticateUser("aaaa5555@aaa","00087");
//
        EnterpriseService enterpriseService = new EnterpriseService();
        enterpriseService.register(new Enterprise("aaa","aaaa","x@cx","aaaw"),"aaaw");
    }

}