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
public class VeXe extends ChuyenXe {
    private int VeXeID;
    private ChuyenXe chuyenXe;
    private String phone;
    private Time GioDat;
    private String KhachHangName;
    private int soGhe;
    
    //1 là đặt 0 là vé mua
    
    private int veDat;
    /**
     * @param VeXeID
     * @return the VeXeID
     */
    
    
    public VeXe(int VeXeID, ChuyenXe chuyenXe, String phone, Time GioDat, String KhachHangName, int soGhe,int veDat) {
        this.VeXeID = VeXeID;
        this.chuyenXe = chuyenXe;
        this.phone = phone;
        this.GioDat = GioDat;
        this.KhachHangName = KhachHangName;
        this.soGhe = soGhe;
        this.veDat = veDat;
    }

    public VeXe(ChuyenXe chuyenXe, String phone, Time GioDat, String KhachHangName, int soGhe, int veDat) {
       
        this.chuyenXe = chuyenXe;
        this.phone = phone;
        this.GioDat = GioDat;
        this.KhachHangName = KhachHangName;
        this.soGhe = soGhe;
        this.veDat = veDat;
    }
    
    public VeXe( ChuyenXe chuyenXe, String phone, Time GioDat, String KhachHangName, int soGhe) {
       
        this.chuyenXe = chuyenXe;
        this.phone = phone;
        this.GioDat = GioDat;
        this.KhachHangName = KhachHangName;
        this.soGhe = soGhe;
    }
    public int getVeXeID() {
        return VeXeID;
    }

    /**
     * @param VeXeID the VeXeID to set
     */
    public void setVeXeID(int VeXeID) {
        this.VeXeID = VeXeID;
    }

    /**
     * @return the chuyenXe
     */
    public ChuyenXe getChuyenXe() {
        return chuyenXe;
    }

    /**
     * @param chuyenXe the chuyenXe to set
     */
    public void setChuyenXe(ChuyenXe chuyenXe) {
        this.chuyenXe = chuyenXe;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the GioDat
     */
    public Time getGioDat() {
        return GioDat;
    }

    /**
     * @param GioDat the GioDat to set
     */
    public void setGioDat(Time GioDat) {
        this.GioDat = GioDat;
    }

    /**
     * @return the KhachHangName
     */
    public String getKhachHangName() {
        return KhachHangName;
    }

    /**
     * @param KhachHangName the KhachHangName to set
     */
    public void setKhachHangName(String KhachHangName) {
        this.KhachHangName = KhachHangName;
    }

    /**
     * @return the soGhe
     */
    public int getSoGhe() {
        return soGhe;
    }

    /**
     * @param soGhe the soGhe to set
     */
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public int getVeDat() {
        return veDat;
    }

    public void setVeDat(int veDat) {
        this.veDat = veDat;
    }
    
}
