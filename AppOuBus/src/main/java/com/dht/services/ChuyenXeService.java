/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.pojo.ChuyenXe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            String mota  = rs.getString("MoTa");
            ChuyenXe cx = new ChuyenXe(chuyenXeID, mota);
            listChuyenXe.add(cx);
        }
        return listChuyenXe;
    }
   
}
