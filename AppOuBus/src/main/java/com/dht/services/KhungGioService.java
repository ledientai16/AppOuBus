/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.pojo.KhungGio;
import com.dht.pojo.Tram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhungGioService {
    public static List <KhungGio> getKhungGio() throws SQLException{
         Connection conn = Utils.getConn();
        String sql = "Select * From KhungGio";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List <KhungGio> listTime = new ArrayList<>();
        while(rs.next()){
            
            KhungGio k = new KhungGio(rs.getInt("KhungGioID"),rs.getTime("BeginTime"), rs.getTime("EndTime"));
            listTime.add(k);
        }
        return listTime;
    }
     public static List <Time> listSoChuyen(Time begin, Time end, Time t){
        List <Time> listTime = new ArrayList<>(); 
        while(begin.getHours()<= end.getHours())
        {
            if((begin.getHours()== end.getHours() && begin.getMinutes() >= end.getMinutes()))
                break;
            else{
                int h = begin.getHours() + t.getHours();
                int m = begin.getMinutes() + t.getMinutes();
                
                
                if(m >= 60){
                    h++;
                    m -= 60;
                }
                if(h >end.getHours() || (h==end.getHours() && m >= end.getMinutes()))
                    break;
                Time i = new Time(begin.getHours(),begin.getMinutes(),0);
                begin.setHours(h);
                begin.setMinutes(m);
                
                listTime.add(i);
            }
        }
        return listTime;
    }
}
