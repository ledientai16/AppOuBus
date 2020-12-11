/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class Utils {
    private static Connection conn;
    static{
        try {
            
                    
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/appoubusdb",
                    "root",
                    "12345678");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public static Connection getConn() {
        return conn;
    }
    public static List<Integer> getListHour(){
         List <Integer> hours = new ArrayList<Integer>();
         for(int i = 1; i <= 24; i++){
             hours.add(i);
         }
         return hours;
     }
    public static List<Integer> getListMinutes(){
         List <Integer> minutes = new ArrayList<Integer>();
         for(int i = 0; i <= 60 ; i++){
             minutes.add(i);
         }
         return minutes;
     }
   
}
