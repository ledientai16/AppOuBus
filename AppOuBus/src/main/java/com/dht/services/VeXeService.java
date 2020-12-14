/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.pojo.ChuyenXe;
import com.dht.pojo.Tram;
import com.dht.pojo.TuyenDuong;
import com.dht.pojo.VeXe;
import com.dht.pojo.Xe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class VeXeService {
    public static boolean kiemTraDat(int soGhe, int ChuyenID) throws SQLException{
        
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM vexe wHERE ChuyenXeID = ? AND SoGhe = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, ChuyenID);
        stm.setInt(2, soGhe);
        ResultSet rs = stm.executeQuery();
        try {

            while(rs.next()){
               return false; 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //chưa đặt
        return true;
    }
    public static VeXe getVeXeBySoGhe(int soGhe, int chuyenXeID) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM chuyenxe where soVe = ? AND chuyenXeID = ?";
        //excute query
        
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, soGhe);
            stm.setInt(2, chuyenXeID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
            VeXe ve =new VeXe(rs.getInt("VeXeID"), ChuyenXeService.getChuyenByID(rs.getInt("ChuyenXeID")),
                    rs.getString("Phone"), rs.getTime("GioDat"), rs.getString("KhachHangName"),rs.getInt("SoGhe") );
            return ve;
        }
        } catch (SQLException ex) {
            Logger.getLogger(VeXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     
       
        return null;
    }
    public static boolean addVeXe(VeXe v){
       
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO vexe(ChuyenXeID, SoGhe, GioDat,KhachHangName,Phone)" + "Values(?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,v.getChuyenXe().getChuyenXeID());
            stm.setInt(2,v.getSoGhe());
            stm.setTime(3,v.getGioDat());
            stm.setString(4, v.getKhachHangName());
            stm.setString(5,v.getPhone());
            int executeUpdate = stm.executeUpdate();
                
            
            conn.commit();
            return executeUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
    }
}
