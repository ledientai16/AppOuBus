/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.tester01;

import com.dht.pojo.Tram;
import com.dht.services.TramService;
import com.dht.services.Utils;
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
public class TramTester {
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
            List<Tram> Trams = TramService.getTram("");
            for (Tram tram: Trams) {
                Assert.assertNotNull(tram.getName());
                Assert.assertNotEquals("", tram.getName().trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCateCounter() {
        try {
            List<Tram> cats = TramService.getTram("");
            
            Assert.assertTrue(cats.size() >= 4);
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testDelete(){
        Assert.assertFalse(TramService.deleteTram("1"));
        Assert.assertFalse(TramService.deleteTram("2"));
        Assert.assertTrue(TramService.deleteTram("5"));
    }
    public void testGetTramByID(){
        try {
            Tram t = TramService.getTramByID(1);
            Assert.assertNotNull(t.getName());
            Assert.assertNotEquals("", t.getName().trim());
            Assert.assertNotNull(t.getDiaChi());
            
        } catch (SQLException ex) {
            Logger.getLogger(TramTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void testAddTram(){
        Tram t = new Tram("Trạm tester", "Trạm Test");
        Assert.assertTrue(TramService.addTram(t));
    }
}