/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.pojo;

/**
 *
 * @author Admin
 */
public class ChuyenXe {
    private int chuyenXeID;
    private String name;
    private int toTram;
    private int fromTram;
    private int XeID;

    public ChuyenXe(int chuyenXeID, String name, int toTram, int fromTram, int XeID) {
        this.chuyenXeID = chuyenXeID;
        this.name = name;
        this.toTram = toTram;
        this.fromTram = fromTram;
        this.XeID = XeID;
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
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

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
        return XeID;
    }

    /**
     * @param XeID the XeID to set
     */
    public void setXeID(int XeID) {
        this.XeID = XeID;
    }
    

}
