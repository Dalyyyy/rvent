package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RventDB {
    private final String URL = "";
    private final String USER ="";
    private final String PW = "";
    private Connection connection;
    private static RventDB instance;


   private RventDB(){
       try{
           connection = DriverManager.getConnection(URL, USER, PW);
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   public static RventDB getInstance(){
       if (instance==null)
           instance= new RventDB();
       return instance;
   }
}
