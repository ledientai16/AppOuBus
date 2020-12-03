/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.pojo;




import java.util.Date;

/**
 *
 * @author Admin
 */
public class Xe {
    private int xeID;
    private String bienSo;
    private int soGhe;
    private String loaiXe;
    private Date namSX;
  
    public Xe(int xeID, String bienSo, int soGhe, String loaiXe, Date namSX) {
        this.xeID = xeID;
        this.bienSo = bienSo;
        this.soGhe = soGhe;
        this.loaiXe = loaiXe;
        this.namSX = namSX;
     
    }
    public Xe(String bienSo, int soGhe, String loaiXe, Date namSX) {
        
        this.bienSo = bienSo;
        this.soGhe = soGhe;
        this.loaiXe = loaiXe;
        this.namSX = namSX;
     
    }


    
    /**
     * @return the xeID
     */
    public int getXeID() {
        return xeID;
    }

    /**
     * @param xeID the xeID to set
     */
    public void setXeID(int xeID) {
        this.xeID = xeID;
    }

    /**
     * @return the bienSo
     */
    public String getBienSo() {
        return bienSo;
    }

    /**
     * @param bienSo the bienSo to set
     */
    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
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

    /**
     * @return the loaiXe
     */
    public String getLoaiXe() {
        return loaiXe;
    }

    /**
     * @param loaiXe the loaiXe to set
     */
    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    /**
     * @return the namSX
     */
    public Date getNamSX() {
        return namSX;
    }

    /**
     * @param namSX the namSX to set
     */
    public void setNamSX(Date namSX) {
        this.namSX = namSX;
    }


    
 
    
   
}
