/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.tester01;

import com.dht.pojo.Tram;
import com.dht.pojo.VeXe;
import com.dht.services.ChuyenXeService;
import com.dht.services.TramService;
import com.dht.services.Utils;
import com.dht.services.VeXeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class VeXeTester {
    private static Connection conn;
    
   @BeforeClass
    public static void setUp() {
        conn = Utils.getConn();
    }
    
    @AfterClass
    public static void tearDown() throws SQLException {
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testKiemTraDat() throws SQLException{
        Assert.assertTrue(VeXeService.kiemTraDat(20, 74));
    }
    @Test
    public void testGetVeXeBySoGhe() throws SQLException{
        Assert.assertNotNull(VeXeService.getVeXeBySoGhe(20, 74));
         Assert.assertNull(VeXeService.getVeXeBySoGhe(20, 11));
    }
    @Test 
    public void testAddVeXe() throws SQLException{
        Assert.assertTrue(VeXeService.addVeXe(new VeXe(ChuyenXeService.getChuyenByID(71), "0192", new Time(4, 1, 0), "ba den",33,1)));
    }
    @Test
    public void testDatVeXe() throws SQLException{
        Assert.assertTrue(VeXeService.datVeXe(new VeXe(ChuyenXeService.getChuyenByID(71), "0192", new Time(4, 1, 0), "ba den",33,1)));
    }
    
    @Test
    public void testDeleteVeDat() {
        try {
            Assert.assertTrue(VeXeService.deleteVeDat(9, 74));
            Assert.assertFalse(VeXeService.deleteVeDat(5, 74));
        } catch (SQLException ex) {
            Logger.getLogger(VeXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testNhanVeDat(){
        try {
            Assert.assertTrue(VeXeService.nhanVeDat(24, 74));
        } catch (SQLException ex) {
            Logger.getLogger(VeXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testChangeGheDat(){
        try {
            Assert.assertTrue(VeXeService.ChangeVeDat(VeXeService.getVeXeBySoGhe(27, 74), 28, 0));
        } catch (SQLException ex) {
            Logger.getLogger(VeXeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
