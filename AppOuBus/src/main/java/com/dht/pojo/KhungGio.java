/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.pojo;

import java.sql.Time;

/**
 *
 * @author Admin
 */
public class KhungGio {
    private Time beginTime;
    private Time endTime;
    private int khungGioID;
    public KhungGio(int khungGioID,Time beginTime, Time endTime) {
        this.khungGioID = khungGioID;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public KhungGio() {
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return khungGioID + "."+beginTime.getHours()+":" +beginTime.getMinutes()+ " - " + endTime.getHours()+":" +endTime.getMinutes();
        
    }
    
}
