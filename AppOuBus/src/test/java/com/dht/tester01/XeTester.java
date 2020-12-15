/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.tester01;

import com.dht.pojo.Tram;
import com.dht.pojo.Xe;
import com.dht.services.TramService;
import com.dht.services.Utils;
import com.dht.services.XeService;
import java.sql.Connection;
import java.sql.Date;
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
public class XeTester {
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
            List<Xe> xes = XeService.getXe("");
            for (Xe xe: xes) {
                Assert.assertNotNull(xe.getBienSo());
                Assert.assertNotEquals("", xe.getBienSo().trim());
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
    public void testAddXe() throws SQLException{
        Xe x = new Xe(19, "kwo", 40, "toyota",new Date(2020, 1, 1), TramService.getTramByID(3));
        Assert.assertTrue(XeService.addXe(x));
    }
    @Test
    public void testDeleteXe(){
       
        try {
            Assert.assertFalse(XeService.deleteXe("1"));
            Assert.assertTrue(XeService.deleteXe("5"));
        } catch (SQLException ex) {
            Logger.getLogger(XeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void testGetXeByID(){
       
        try {
            Xe x = XeService.getXeByID(1);
            Assert.assertNotNull(x.getBienSo());
            Assert.assertNotEquals("", x.getBienSo().trim());
            Assert.assertNotNull(x.getSoGhe());
        } catch (SQLException ex) {
            Logger.getLogger(XeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
}
