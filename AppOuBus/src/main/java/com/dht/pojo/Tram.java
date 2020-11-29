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
public class Tram {
    private int tramID;
    private String name;
    private String diaChi;

    public Tram(int tramID, String name, String diaChi) {
        this.tramID = tramID;
        this.name = name;
        this.diaChi = diaChi;
    }
    public Tram(String name, String diaChi) {
        
        this.name = name;
        this.diaChi = diaChi;
    }
    public Tram() {
    }

    
    /**
     * @return the tramID
     */
    public int getTramID() {
        return tramID;
    }

    /**
     * @param tramID the tramID to set
     */
    public void setTramID(int tramID) {
        this.tramID = tramID;
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
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
