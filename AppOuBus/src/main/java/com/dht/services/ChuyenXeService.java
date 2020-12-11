/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.pojo.ChuyenXe;
import com.dht.pojo.TuyenDuong;
import com.dht.pojo.Xe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
            ChuyenXe cx = new ChuyenXe(chuyenXeID, t, time, xe, date);
            listChuyenXe.add(cx);
        }
        return listChuyenXe;
    }
    public static boolean addChuyenXe(ChuyenXe c){
        Connection conn = Utils.getConn();
      
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO chuyenxe(ChuyenXeID,XeID,TuyenDuongID,BeginTime,GiaTien,NgayKhoiHanh,SoVe)" + "Values(?,?,?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,c.getChuyenXeID());
            stm.setInt(2,c.getXe().getXeID());
            stm.setInt(3,c.getTuyenDuong().getTuyenDuongID());
            stm.setTime(4, c.getBeginTime());
            stm.setDouble(5,c.getGiaTien());
            stm.setDate(6, c.getDate());
            stm.setInt(7, c.getSoVe());
            int executeUpdate = stm.executeUpdate();
                
            
            conn.commit();
            return executeUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
        return false;
    
    }
   
}
