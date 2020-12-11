/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import java.sql.Date;
import com.dht.pojo.Xe;
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
public class XeService {
    public static List <Xe> getXe(String kw) throws SQLException{
        
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM xe";
        if(kw!=null && !kw.trim().isEmpty())
            sql += " Where BienSo like ?";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        if (kw != null && !kw.trim().isEmpty())
            stm.setString(1, String.format("%%%s%%", kw.trim()));
        
        ResultSet res = stm.executeQuery();
        
        List <Xe> listXe = new ArrayList();
       while(res.next()){
           int xeID =  res.getInt("XeID");
           String bienSoXe = res.getString("BienSo");
           String loaiXe = res.getString("LoaiXe");
           int soGhe = res.getInt("SoGhe");
           Date namSX = res.getObject("NamSX", Date.class);
           int tramID = res.getInt("TramID");
           Xe x = new Xe(xeID, bienSoXe, soGhe, loaiXe, namSX,TramService.getTramByID(tramID));
           listXe.add(x);
       }
       return listXe;
       
    }
    
    public static boolean addXe(Xe xe){
        Connection conn = Utils.getConn();
        String sql = "INSERT INTO xe(XeID,BienSo,SoGhe, LoaiXe, NamSX,TramID)" + "Values(?,?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, xe.getXeID());
            stm.setString(2,xe.getBienSo());
            stm.setInt(3, xe.getSoGhe());
            stm.setString(4, xe.getLoaiXe());
            stm.setDate(5, (Date) xe.getNamSX());
            stm.setInt(6, xe.getTram().getTramID());
            int execute = stm.executeUpdate();
             
            
            conn.commit();
            return execute > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(XeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean deleteXe(String id) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "DELETE FROM xe WHERE XEID = ?";
        
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            int execute = stm.executeUpdate();
                
 
        return execute > 0;
    }
     public static Xe getXeByID(int id) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "Select * From xe WHERE XeID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1,id);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){  
           int tramID = rs.getInt("TramID");
           Xe x = new Xe(rs.getInt("XeID"), rs.getString("BienSo"), rs.getInt("SoGhe"),rs.getString("LoaiXe"),
                   rs.getObject("NamSX", Date.class),TramService.getTramByID(tramID));
           return x;
        }
        return null;
    }
}
