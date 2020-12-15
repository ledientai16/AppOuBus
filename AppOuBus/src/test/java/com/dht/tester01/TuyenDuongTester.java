/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.tester01;

import com.dht.pojo.TuyenDuong;
import com.dht.pojo.Xe;
import com.dht.services.TramService;
import com.dht.services.TuyenDuongService;
import com.dht.services.Utils;
import com.dht.services.XeService;
import java.sql.Connection;
import java.sql.SQLException;
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
public class TuyenDuongTester {
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
    public void testNameIsNotNull() {
        try {
            List<TuyenDuong> tuyenDuong = TuyenDuongService.getTuyenDuong("");
            for (TuyenDuong t: tuyenDuong) {
                Assert.assertNotNull(t.getTuyenDuongName());
                Assert.assertNotEquals("", t.getTuyenDuongName().trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testCateCounter() {
        try {
            List<Xe> xes = XeService.getXe("");
            
            Assert.assertTrue(xes.size() >= 4);
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testAddTuyen(){
        try {
            TuyenDuong t = new TuyenDuong(15, "Tuyen tester", 120, TramService.getTramByID(1), TramService.getTramByID(2));
            Assert.assertTrue(TuyenDuongService.addTuyen(t));
        } catch (SQLException ex) {
            Logger.getLogger(TuyenDuongTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test 
    public void testTaoTuyenKhuHoi(){
       try {
            TuyenDuong t = new TuyenDuong(15, "Tuyen tester", 120, TramService.getTramByID(1), TramService.getTramByID(2));
            Assert.assertTrue(TuyenDuongService.addTuyen(t));
            Assert.assertTrue(TuyenDuongService.taoTuyenKhuHoi(t));

        } catch (SQLException ex) {
            Logger.getLogger(TuyenDuongTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testDeleteTuyenDuong(){
        try {
            Assert.assertFalse(TuyenDuongService.deleteTuyenDuong("1"));
            Assert.assertTrue(TuyenDuongService.deleteTuyenDuong("12"));
        } catch (SQLException ex) {
            Logger.getLogger(TuyenDuongTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void testGetTuyenDuongByID() throws SQLException{
        Assert.assertNotNull(TuyenDuongService.getTuyenDuongByID(12));
    }
}
