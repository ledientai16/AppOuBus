/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.pojo;

import java.sql.Time;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Admin
 */
public class ChuyenXe {
    private int chuyenXeID;
    private TuyenDuong tuyenDuong;
    private Time beginTime;
    private Xe xe;
    private Date date;
    private double giaTien;
    private int soVe;
    public ChuyenXe() {
    
    }

    public ChuyenXe(int chuyenXeID, TuyenDuong tuyenDuong, Time beginTime, Xe xe, Date date, double giaTien, int soVe) {
        this.chuyenXeID = chuyenXeID;
        this.tuyenDuong = tuyenDuong;
        this.beginTime = beginTime;
        this.xe = xe;
        this.date = date;
        this.giaTien = giaTien;
        this.soVe = soVe;
    }

    
    public ChuyenXe(int chuyenXeID, TuyenDuong tuyenDuong, Time beginTime, Xe xe, Date date) {
        this.chuyenXeID = chuyenXeID;
        this.tuyenDuong = tuyenDuong;
        this.beginTime = beginTime;
        this.xe = xe;
        this.date = date;
    }
    public ChuyenXe(TuyenDuong tuyenDuong, Time beginTime, Xe xe, Date date, double giaTien, int soVe) {
      
        this.tuyenDuong = tuyenDuong;
        this.beginTime = beginTime;
        this.xe = xe;
        this.date = date;
        this.giaTien = giaTien;
        this.soVe = soVe;
    }
    public void setSoVe(int soVe) {
        this.soVe = soVe;
    }

    public int getSoVe() {
        return soVe;
    }
    
    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
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
     * @return the tuyenDuong
     */
    public TuyenDuong getTuyenDuong() {
        return tuyenDuong;
    }

    /**
     * @param tuyenDuong the tuyenDuong to set
     */
    public void setTuyenDuong(TuyenDuong tuyenDuong) {
        this.tuyenDuong = tuyenDuong;
    }

    /**
     * @return the beginTime
     */
    public Time getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime the beginTime to set
     */
    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return the xe
     */
    public Xe getXe() {
        return xe;
    }

    /**
     * @param xe the xe to set
     */
    public void setXe(Xe xe) {
        this.xe = xe;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
