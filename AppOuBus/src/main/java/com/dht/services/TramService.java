/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.pojo.Tram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TramService {
    public static List<Tram> getTram(String kw) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "Select * From tram";
        if(kw !=null && !kw.trim().isEmpty())
            sql += " WHERE Name like ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        if(kw !=null && !kw.trim().isEmpty())
            stm.setString(1,String.format("%%%s%%",kw.trim()));
        ResultSet rs = stm.executeQuery();
        
        List <Tram> listTram = new ArrayList<>();
        while(rs.next()){
            int tramID = rs.getInt("TramID");
            String name = rs.getString("Name");
            String diaChi = rs.getString("DiaChi");
            Tram tram = new Tram(tramID, name, diaChi);
            listTram.add(tram);
        }
        return listTram;
    }
    
    public static boolean addTram(Tram tram){
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO tram(TramID,Name, DiaChi)" + "Values(?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,tram.getTramID());
            stm.setString(2,tram.getName());
            stm.setString(3,tram.getDiaChi());
            int executeUpdate = stm.executeUpdate();
                
            
            conn.commit();
            return executeUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean deleteTram(String tramID){
        Connection conn = Utils.getConn();
        String sql = "DELETE FROM tram where TramID = ?";
        
        try {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, tramID);
            int kq = stm.executeUpdate();
            stm.executeUpdate();
            conn.commit();
            return kq >0;
        } catch (SQLException ex) {
            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return false;
    }
    public static Tram getTramByID(int id) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "Select * From tram WHERE TramID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1,id);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){  
            Tram tram = new Tram( rs.getInt("TramID"),rs.getString("Name"), rs.getString("DiaChi"));
             return tram;
        }
        return null;
    }
    
}
