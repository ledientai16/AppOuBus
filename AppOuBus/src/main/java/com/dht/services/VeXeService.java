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
import java.time.LocalTime;
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
        String sql = "SELECT Count(SoGhe) FROM vexe WHERE ChuyenXeID = ? AND SoGhe = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, ChuyenID);
        stm.setInt(2, soGhe);
        ResultSet rs = stm.executeQuery();
        try {
            int s = 0;
            while(rs.next()){
                s= rs.getInt("COUNT(SoGhe)");
            }
            return s > 0; 
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //chưa đặt
       return false;
    }
   
    public static VeXe getVeXeBySoGhe(int soGhe, int chuyenXeID) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM vexe where SoGhe = ? AND ChuyenXeID = ?";
        //excute query
        
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, soGhe);
            stm.setInt(2, chuyenXeID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
            VeXe ve =new VeXe(rs.getInt("VeXeID"), ChuyenXeService.getChuyenByID(rs.getInt("ChuyenXeID")),
                    rs.getString("Phone"), rs.getTime("GioDat"), rs.getString("KhachHangName"),rs.getInt("SoGhe"),rs.getInt("VeDat"));
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
            String sql = "INSERT INTO vexe(ChuyenXeID, SoGhe, GioDat,KhachHangName,Phone,VeDat)" + "Values(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,v.getChuyenXe().getChuyenXeID());
            stm.setInt(2,v.getSoGhe());
            stm.setTime(3,v.getGioDat());
            stm.setString(4, v.getKhachHangName());
            stm.setString(5,v.getPhone());
            stm.setInt(6, v.getVeDat());
            int executeUpdate = stm.executeUpdate();
            conn.commit();
            return executeUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
    }
    public static boolean datVeXe(VeXe v){
       
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO vexe(ChuyenXeID, SoGhe, GioDat,KhachHangName,Phone,VeDat)" + "Values(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,v.getChuyenXe().getChuyenXeID());
            stm.setInt(2,v.getSoGhe());
            stm.setTime(3,v.getGioDat());
            stm.setString(4, v.getKhachHangName());
            stm.setString(5,v.getPhone());
            stm.setInt(6,1);
            int executeUpdate = stm.executeUpdate();
            conn.commit();
            return executeUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //delete ve đặt
    public static boolean deleteVeDat(int soGhe, int chuyenXeID) throws SQLException{
        Connection conn = Utils.getConn();
        VeXe v= getVeXeBySoGhe(soGhe, chuyenXeID);
        if(v != null){
            if(v.getVeDat() == 1){
                String sql = "DELETE FROM vexe where SoGhe = ? AND ChuyenXeID = ?";

                try {
                    conn.setAutoCommit(false);
                    PreparedStatement stm = conn.prepareStatement(sql);
                    stm.setInt(1, soGhe);
                    stm.setInt(2, chuyenXeID);
                    if(stm.executeUpdate() == 1);
                    {
                        conn.commit();
                        return true;
                     }
            } catch (SQLException ex) {
                Logger.getLogger(TramService.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
      return false;
    }
    //delete tat ca ve dat
    public static boolean deleteAllVeDat() throws SQLException{
        Connection conn = Utils.getConn();
        String sql ="DELETE From vexe where VeDat = 1";
        PreparedStatement stm = conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        if(stm.executeUpdate() == 1){
            conn.commit();
            return true;
       }
        return false;
        
        
    }
    //update vedat == 0 ve duoc duoc chuyen sang ve mua
    public static boolean nhanVeDat(int soGhe, int chuyenXeID) throws SQLException{
         Connection conn = Utils.getConn();
         VeXe v= getVeXeBySoGhe(soGhe, chuyenXeID);
         if(v != null && v.getVeDat() ==1){
            String sql ="UPDATE vexe Set VeDat = 0 where SoGhe = ? AND ChuyenXeID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, soGhe);
            stm.setInt(2, chuyenXeID);
            conn.setAutoCommit(false);
            if(stm.executeUpdate() == 1){
                conn.commit();
                return true;
            }
        
         }
        return false;
    }
    public static boolean ChangeVeDat(VeXe v, int gheMoi,int changeChuyenXeID) throws SQLException{
         Connection conn = Utils.getConn();
        
          VeXe v2 = getVeXeBySoGhe(gheMoi, changeChuyenXeID);
          if(v2 == null && v.getVeDat() == 1){
            String sql ="UPDATE vexe Set SoGhe = ?, ChuyenXeID = ? WHERE VeXeID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, gheMoi);
            stm.setInt(2, changeChuyenXeID);
            stm.setInt(3, v.getVeXeID());
            
            conn.setAutoCommit(false);
            if(stm.executeUpdate() == 1){
                conn.commit();
                return true;
            }
            return false;
          }
          return false;
        
        
    }
    //hàm kiểm tra thời gian mua vé chỉ được đặt trước 5'
    public static boolean checkTimeMuaVe(Time t, Date d){
     
        long millis = System.currentTimeMillis();  
        Date nowDay=new java.sql.Date(millis);
        if(d.after(nowDay))
            return true;
        if(d.getYear() == nowDay.getYear() && d.getMonth() == nowDay.getMonth() && d.getDay() == nowDay.getDay())
          { 
            
            int checkSum = LocalTime.now().getHour() * 60 + LocalTime.now().getMinute();
            int checkBeginTime = t.getHours() * 60 + t.getMinutes();
            
            if((checkBeginTime - checkSum) >= 5)
                return true;
            return false;
           }
      return false;
    }   
    
    
    //tự động
    
    //hàm kiểm tra thời gian đặt vé chỉ được đặt trước 60'
    public static boolean checkTimeDatVe(Time t, Date d){
     
        long millis = System.currentTimeMillis();  
        Date nowDay=new java.sql.Date(millis);
        if(d.after(nowDay))
            return true;
        if(d.getYear() == nowDay.getYear() && d.getMonth() == nowDay.getMonth() && d.getDay() == nowDay.getDay())
          { 
            
            int checkSum = LocalTime.now().getHour() * 60 + LocalTime.now().getMinute();
            int checkBeginTime = t.getHours() * 60 + t.getMinutes();
            
            if((checkBeginTime - checkSum) >= 60)
                return true;
            return false;
           }
       return false;
    }  
    //hàm kiểm tra thời gian đặt vé trước 30'
    public static boolean checkTimeDeleteAll(Time t, Date d){
     
        long millis = System.currentTimeMillis();  
        Date nowDay=new java.sql.Date(millis);
        if(d.after(nowDay))
            return true;
        if(d.getYear() == nowDay.getYear() && d.getMonth() == nowDay.getMonth() && d.getDay() == nowDay.getDay())
          { 
            
            int checkSum = LocalTime.now().getHour() * 60 + LocalTime.now().getMinute();
            int checkBeginTime = t.getHours() * 60 + t.getMinutes();
            
            if((checkBeginTime - checkSum) <= 30)
                return true;
            return false;
           }
       return false;
    }  
   
}