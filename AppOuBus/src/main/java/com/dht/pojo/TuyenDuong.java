/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.pojo;


import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author Admin
 */
public class TuyenDuong {
    private int tuyenDuongID;
    private String tuyenDuongName;
    private int distance;
    //TuyenDuongtime la thoi gian uoc luong xe hoan thanh tuyen duong = khoang cach /45;
    private Time tuyenDuongTime;
    private Tram fromTram;
    private Tram toTram;
    private int tuyenKhuHoiID;

    public void setTuyenKhuHoiID(int tuyenKhuHoiID) {
        this.tuyenKhuHoiID = tuyenKhuHoiID;
    }

    public int getTuyenKhuHoiID() {
        return tuyenKhuHoiID;
    }
    public TuyenDuong() {
    }
    public TuyenDuong(int tuyenDuongID, String tuyenDuongName, int distance, Time tuyenDuongTime, Tram fromTram, Tram toTram) {
        this.tuyenDuongID = tuyenDuongID;
        this.tuyenDuongName = tuyenDuongName;
        this.distance = distance;
        this.tuyenDuongTime = tuyenDuongTime;
        this.fromTram = fromTram;
        this.toTram = toTram;
        
    }
    public TuyenDuong(int tuyenDuongID, String tuyenDuongName, int distance, Tram fromTram, Tram toTram) {
        this.tuyenDuongID = tuyenDuongID;
        this.tuyenDuongName = tuyenDuongName;
        this.distance = distance;
        
        this.fromTram = fromTram;
        this.toTram = toTram;
        
    }
   


    
    
    
    /**
     * @return the tuyenDuongID
     */
    public int getTuyenDuongID() {
        return tuyenDuongID;
    }

    /**
     * @param tuyenDuongID the tuyenDuongID to set
     */
    public void setTuyenDuongID(int tuyenDuongID) {
        this.tuyenDuongID = tuyenDuongID;
    }

    public TuyenDuong(int tuyenDuongID, String tuyenDuongName, int distance, Time tuyenDuongTime, Tram fromTram, Tram toTram, int tuyenDuongKhuHoiID) {
        this.tuyenDuongID = tuyenDuongID;
        this.tuyenDuongName = tuyenDuongName;
        this.distance = distance;
        this.tuyenDuongTime = tuyenDuongTime;
        this.fromTram = fromTram;
        this.toTram = toTram;
        this.tuyenKhuHoiID = tuyenDuongKhuHoiID;
    }
    
    /**
     * @return the tuyenDuongName
     */
    public String getTuyenDuongName() {
        return tuyenDuongName;
    }

    /**
     * @param tuyenDuongName the tuyenDuongName to set
     */
    public void setTuyenDuongName(String tuyenDuongName) {
        this.tuyenDuongName = tuyenDuongName;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return the tuyenDuongTime
     */
    public Time getTuyenDuongTime() {
        return tuyenDuongTime;
    }

    /**
     * @param tuyenDuongTime the tuyenDuongTime to set
     */
    public void setTuyenDuongTime(Time tuyenDuongTime) {
        this.tuyenDuongTime = tuyenDuongTime;
    }

    /**
     * @return the fromTram
     */
    public Tram getFromTram() {
        return fromTram;
    }

    /**
     * @param fromTram the fromTram to set
     */
    public void setFromTram(Tram fromTram) {
        this.fromTram = fromTram;
    }

    /**
     * @return the toTram
     */
    public Tram getToTram() {
        return toTram;
    }

    /**
     * @param toTram the toTram to set
     */
    public void setToTram(Tram toTram) {
        this.toTram = toTram;
    }

    /**
     * @return the tuyenDuongKhuHoiID
     */
  

    @Override
    public String toString() {
        String result = String.format("%d.%s(%s-%s)", this.tuyenDuongID,this.tuyenDuongName,this.fromTram.getName(),this.toTram.getName());
        return result;
    }
}
