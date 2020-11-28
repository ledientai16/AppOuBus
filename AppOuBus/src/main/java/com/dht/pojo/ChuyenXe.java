/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.pojo;

import java.sql.Time;
import java.util.GregorianCalendar;

/**
 *
 * @author Admin
 */
public class ChuyenXe {
    private int chuyenXeID;
    private String moTa;
    private int toTram;
    private int fromTram;
    private int xeID;
    private Time timeStart;
    private Time timeEnd;
    private GregorianCalendar date;

    public ChuyenXe(int chuyenXeID, int toTram, int fromTram, int XeID) {
        this.chuyenXeID = chuyenXeID;
        
        this.toTram = toTram;
        this.fromTram = fromTram;
        
    }
    public ChuyenXe(int chuyenXeID, String moTa){
     this.chuyenXeID = chuyenXeID;
     this.moTa = moTa;
    }
    public ChuyenXe() {
    }

    /**
     * @return the chuyenXeID
     */
    public int getChuyenXeID() {
        return chuyenXeID;
    }

    /**
     * @param chuyenXeID the chuyenXeID to set
     */
    public void setChuyenXeID(int chuyenXeID) {
        this.chuyenXeID = chuyenXeID;
    }

    /**
     * @return the name
     */
  

    /**
     * @return the toTram
     */
    public int getToTram() {
        return toTram;
    }

    /**
     * @param toTram the toTram to set
     */
    public void setToTram(int toTram) {
        this.toTram = toTram;
    }

    /**
     * @return the fromTram
     */
    public int getFromTram() {
        return fromTram;
    }

    /**
     * @param fromTram the fromTram to set
     */
    public void setFromTram(int fromTram) {
        this.fromTram = fromTram;
    }

    /**
     * @return the XeID
     */
    public int getXeID() {
        return xeID;
    }

    /**
     * @param XeID the XeID to set
     */
    public void setXeID(int XeID) {
        this.xeID = xeID;
    }

    public String getMoTa() {
        return moTa;
    }
    

}
