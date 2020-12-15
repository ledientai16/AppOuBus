/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.pojo.Tram;
import com.dht.pojo.TuyenDuong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class TuyenDuongService {
    public static List<TuyenDuong> getTuyenDuong(String kw) throws SQLException{
        String sql = "SELECT * FROM tuyenduong";
        Connection conn =Utils.getConn();
        PreparedStatement stm;
        if(kw != null &&  !kw.trim().isEmpty())
            sql += " WHERE TuyenDuongName like ?";
        stm = conn.prepareStatement(sql);
        if(kw != null && !kw.trim().isEmpty())
            stm.setString(1, String.format("%%%s%%", kw.trim()));
        ResultSet rs = stm.executeQuery();
        
        List <TuyenDuong> list = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("TuyenDuongID");
            String name = rs.getString("TuyenDuongName");
            int distance = rs.getInt("Distance");
            Time tuyenDuongTime = rs.getTime("TuyenDuongTime");
            int fromTramID = rs.getInt("FromTram");
            int toTramID = rs.getInt("ToTram");
            int tuyenkhuHoiID = rs.getInt("TuyenKhuHoiID");
            Tram fromTram = TramService.getTramByID(fromTramID);
            Tram toTram = TramService.getTramByID(toTramID);
            
            TuyenDuong td = new TuyenDuong(id, name, distance, tuyenDuongTime, fromTram, toTram,tuyenkhuHoiID);
            list.add(td);
        }
        return list;
        
    }
    public static boolean taoTuyenKhuHoi(TuyenDuong t) throws SQLException{
        Connection conn = Utils.getConn();
        PreparedStatement stm;
        String khuHoiID = String.valueOf(t.getTuyenKhuHoiID());
        conn.setAutoCommit(false);
        if(khuHoiID == null){
           return false;
        }
        else{
            String sql = "SELECT * from tuyenduong where TuyenDuongID like '100%' ";
            stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            int id = 0;
            while(rs.next()){
                id= rs.getInt("TuyenDuongID");
            }
            if(id == 0)
                id += 1001;
            else
                id += 1;
            String sql2 = "INSERT INTO tuyenduong(TuyenDuongID, TuyenDuongName,FromTram,ToTRam,Distance,TuyenDuongTime,TuyenKhuHoiID)" 
                    + "Values(?,?,?,?,?,?,?)";
            PreparedStatement stm2 = conn.prepareStatement(sql2);
            stm2.setInt(1, id);
            stm2.setString(2,t.getToTram().getName() + " to " + t.getFromTram().getName()+ "(" + id + ")");
            stm2.setInt(3, t.getToTram().getTramID());
            stm2.setInt(4, t.getFromTram().getTramID());
            stm2.setInt(5, t.getDistance());
            stm2.setTime(6, t.getTuyenDuongTime());
            stm2.setInt(7, t.getTuyenDuongID());
            
            //update id khu hoi
            PreparedStatement stm3 =conn.prepareStatement
                                ("UPDATE tuyenduong set TuyenKhuHoiID = ? Where TuyenDuongID = ?");
            stm3.setInt(1,id);
            stm3.setInt(2, t.getTuyenDuongID());
     
            if(stm2.executeUpdate() == 1 && stm3.executeUpdate() == 1){
                conn.commit();
                return true;
            }
            return false;
        }
    }
    public static boolean addTuyen(TuyenDuong tuyen) throws SQLException{
        Connection conn = Utils.getConn();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO tuyenduong(TuyenDuongID, TuyenDuongName,FromTram,ToTRam,Distance,TuyenDuongTime)"
                   + "VALUE(?,?,?,?,?,?)";
           PreparedStatement stm = conn.prepareStatement(sql);
        /**PreparedStatement stm2=conn.prepareStatement("UPDATE tuyenduong set TuyenKhuHoiID = ? Where TuyenDuong = ?");
        int idkhuhoi = -1;
        if(auto == true)
        {
           idkhuhoi = taoTuyenKhuHoi(tuyen);
            if(idkhuhoi != -1)
                stm.setInt(7, idkhuhoi);
            stm2.setInt(1,tuyen.getTuyenDuongID());
            stm2.setInt(2, idkhuhoi);
        }*/
        stm.setInt(1, tuyen.getTuyenDuongID());
        stm.setString(2, tuyen.getToTram().getName() + "-" + tuyen.getFromTram().getName());
        stm.setInt(3, tuyen.getFromTram().getTramID());
        stm.setInt(4, tuyen.getToTram().getTramID());
        stm.setInt(5, tuyen.getDistance());
        double t =(double) tuyen.getDistance() / 48;
        int temp = (int) t;
        System.out.println(temp);
        Time time = new Time(temp, (int) ((t -temp) * 60), 0);
        stm.setTime(6,time);
        if(stm.executeUpdate() == 1){
            /**if(auto == true && idkhuhoi != -1)
                if(stm2.executeUpdate()== 1){
                 
                }*/
            conn.commit();
            return true;
        }
           return false;
        
    }
    public static boolean deleteTuyenDuong(String id) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "DELETE FROM tuyenduong where TuyenDuongID = ?";
        String sql2 = "DELETE FROM tuyenduong where TuyenKhuHoiID = ?";
        PreparedStatement stm2 = conn.prepareStatement(sql2);
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, id);
        stm2.setString(1, id);
        conn.setAutoCommit(false);
        if(stm.executeUpdate() == 1)
        {
            conn.commit();
            if(stm2.executeUpdate() ==1)
                conn.commit();
            return true;
        }
        return false;
    }
    
     public static TuyenDuong getTuyenDuongByID(int id) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "Select * From tuyenduong WHERE TuyenDuongID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1,id);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){  
            int i = rs.getInt("TuyenDuongID");
            String name = rs.getString("TuyenDuongName");
            int distance = rs.getInt("Distance");
            Time tuyenDuongTime = rs.getTime("TuyenDuongTime");
            int fromTramID = rs.getInt("FromTram");
            int toTramID = rs.getInt("ToTram");
            int tuyenkhuHoiID = rs.getInt("TuyenKhuHoiID");
            Tram fromTram = TramService.getTramByID(fromTramID);
            Tram toTram = TramService.getTramByID(toTramID);
            
            TuyenDuong td = new TuyenDuong(i, name, distance, tuyenDuongTime, fromTram, toTram,tuyenkhuHoiID);
            return td;
        }
        return null;
    }
    public static List<TuyenDuong> getTuyenDuongByFromTram(int tramID) throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "Select * From tuyenduong WHERE FromTram = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1,tramID);
        ResultSet rs = stm.executeQuery();
        List <TuyenDuong> tuyen = new ArrayList<>();
        while(rs.next()){  
            int i = rs.getInt("TuyenDuongID");
            String name = rs.getString("TuyenDuongName");
            int distance = rs.getInt("Distance");
            Time tuyenDuongTime = rs.getTime("TuyenDuongTime");
            int fromTramID = rs.getInt("FromTram");
            int toTramID = rs.getInt("ToTram");
            int tuyenkhuHoiID = rs.getInt("TuyenKhuHoiID");
            Tram fromTram = TramService.getTramByID(fromTramID);
            Tram toTram = TramService.getTramByID(toTramID);
            
            TuyenDuong td = new TuyenDuong(i, name, distance, tuyenDuongTime, fromTram, toTram,tuyenkhuHoiID);
            tuyen.add(td);
        }
        return tuyen;
    }
}
