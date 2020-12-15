/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.pojo.ChuyenXe;
import com.dht.pojo.TuyenDuong;
import com.dht.pojo.VeXe;
import com.dht.pojo.Xe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ChuyenXeService {
    
    //function lấy danh sách chuyến xe từ DB
    public static List<ChuyenXe> getChuyenXe() throws SQLException{
        //connection db
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM chuyenxe";
        //excute query
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        List <ChuyenXe> listChuyenXe = new ArrayList<>();
        while(rs.next()){
            int chuyenXeID = rs.getInt("ChuyenXeID");
           
            TuyenDuong t = TuyenDuongService.getTuyenDuongByID(rs.getInt("TuyenDuongID"));
            Xe xe = XeService.getXeByID(rs.getInt("XeID"));
            Date date = rs.getObject("NgayKhoiHanh", Date.class);
            Time time = rs.getTime("BeginTime");
            ChuyenXe cx = new ChuyenXe(chuyenXeID, t, time, xe, date, rs.getDouble("GiaTien"), rs.getInt("SoVe"));
            listChuyenXe.add(cx);
        }
        return listChuyenXe;
    }
    public static List<ChuyenXe> getChuyenXe(Date d, int xeID, int tuyenDuongID) throws SQLException{
        //connection db
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM chuyenxe Where NgayKhoiHanh = ? AND XeID = ? AND TuyenDuongID = ?";
        //excute query
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setDate(1,d);
        stm.setInt(2, xeID);
        stm.setInt(3, tuyenDuongID);
        ResultSet rs = stm.executeQuery();
        
        List <ChuyenXe> listChuyenXe = new ArrayList<>();
        while(rs.next()){
            int chuyenXeID = rs.getInt("ChuyenXeID");
           
            TuyenDuong t = TuyenDuongService.getTuyenDuongByID(rs.getInt("TuyenDuongID"));
            Xe xe = XeService.getXeByID(rs.getInt("XeID"));
            Date date = rs.getObject("NgayKhoiHanh", Date.class);
            Time time = rs.getTime("BeginTime");
            ChuyenXe cx = new ChuyenXe(chuyenXeID, t, time, xe, date, rs.getDouble("GiaTien"), rs.getInt("SoVe"));
            listChuyenXe.add(cx);
        }
        return listChuyenXe;
    }
    public static List<ChuyenXe> getChuyenXe(Date d, int tram) throws SQLException{
        //connection db
        Connection conn = Utils.getConn();
        String sql = "SELECT chuyenxe.ChuyenXeID, chuyenxe.TuyenDuongID,chuyenxe.XeID,chuyenxe.NgayKhoiHanh,chuyenxe.BeginTime,chuyenxe.GiaTien,chuyenxe.SoVe "
                + " FROM chuyenxe, tuyenduong,tram Where chuyenxe.TuyenDuongID = tuyenduong.TuyenDuongID AND tuyenduong.FromTram = tram.TramID ANd tram.TramID = ? AND chuyenxe.NgayKhoiHanh = ?";
        //excute query
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setDate(2,d);
        stm.setInt(1, tram);
        ResultSet rs = stm.executeQuery();
        List <ChuyenXe> listChuyenXe = new ArrayList<>();
        while(rs.next()){
            int chuyenXeID = rs.getInt("ChuyenXeID");
        
            
            
            
            TuyenDuong t = TuyenDuongService.getTuyenDuongByID(rs.getInt("TuyenDuongID"));
            Xe xe = XeService.getXeByID(rs.getInt("XeID"));
            Date date = rs.getObject("NgayKhoiHanh", Date.class);
            Time time = rs.getTime("BeginTime");
            ChuyenXe cx = new ChuyenXe(chuyenXeID, t, time, xe, date, rs.getDouble("GiaTien"), rs.getInt("SoVe"));
            listChuyenXe.add(cx);
        }
        return listChuyenXe;
    }
    //kiem tra xem xe da co tuyen trong ngay chua
    public static int CheckChuyenXe(int xeID,Date date) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "Select * from chuyenXe WHERE XeID = ? AND NgayKhoiHanh = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, xeID);
        stm.setDate(2,date);
        ResultSet rs = stm.executeQuery();
        int count = 0;
        while(rs.next()){
            count++;
        }
        return count;
    }
    public static boolean addChuyenXe(ChuyenXe c){
        Connection conn = Utils.getConn();
        
        try {
           
            conn.setAutoCommit(false);
            String sql = "INSERT INTO chuyenxe(XeID,TuyenDuongID,BeginTime,GiaTien,NgayKhoiHanh,SoVe)" + "Values(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1,c.getXe().getXeID());
            stm.setInt(2,c.getTuyenDuong().getTuyenDuongID());
            stm.setTime(3, c.getBeginTime());
            stm.setDouble(4,c.getGiaTien());
            stm.setDate(5, c.getDate());
            stm.setInt(6, c.getSoVe());
            int executeUpdate = stm.executeUpdate();
            conn.commit();
            return executeUpdate > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
        return false;
    }
    public static ChuyenXe getChuyenByID(int id) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM chuyenxe where ChuyenXeID = ?";
        //excute query
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        
        List <ChuyenXe> listChuyenXe = new ArrayList<>();
        while(rs.next()){
            int chuyenXeID = rs.getInt("ChuyenXeID");
           
            TuyenDuong t = TuyenDuongService.getTuyenDuongByID(rs.getInt("TuyenDuongID"));
            Xe xe = XeService.getXeByID(rs.getInt("XeID"));
            Date date = rs.getObject("NgayKhoiHanh", Date.class);
            Time time = rs.getTime("BeginTime");
            ChuyenXe cx = new ChuyenXe(chuyenXeID, t, time, xe, date, rs.getDouble("GiaTien"), rs.getInt("SoVe"));
            return cx;
        }
        return null;
    }
    //Fuction Count so ve da ban
    public static int soVeDaBan(int i){
        Connection conn = Utils.getConn();
        String sql = "Select Count(ChuyenXeID) from vexe where ChuyenXeID = ?";
        
        PreparedStatement stm;
        try {
            int count = 0;
            stm = conn.prepareStatement(sql);
            stm.setInt(1, i);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                count = rs.getInt("COUNT(ChuyenXeID)");
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;
    }

    public static boolean deleteChuyenXe(int id) throws SQLException {
        
        Connection conn = Utils.getConn();
        String sql = "DELETE FROM chuyenxe where ChuyenXeID = ?";
        
     
        PreparedStatement stm = conn.prepareStatement(sql);
       
        stm.setInt(1, id);
        conn.setAutoCommit(false);
        if(stm.executeUpdate() == 1)
        {
            conn.commit();
            if(stm.executeUpdate() ==1)
                conn.commit();
            return true;
        }
        return false;
    
    }
    
}
