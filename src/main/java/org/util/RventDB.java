package org.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RventDB {
    private final String URL = "jdbc:mysql://localhost:3306/test";
    private final String USER ="root";
    private final String PW = "";
    public Connection getConnection;
    private static RventDB instance;
    private Connection connection;


    private RventDB(){
       try{
           getConnection = DriverManager.getConnection(URL, USER, PW);
           System.out.println("connected successfully! ");
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   public static RventDB getInstance(){
       if (instance==null)
           instance= new RventDB();
       return instance;
   }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
