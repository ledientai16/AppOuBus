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
public class VeXe extends ChuyenXe {
    private int VeXe;

    public VeXe(int VeXe) {
        this.VeXe = VeXe;
    }

    public VeXe(int VeXe, int chuyenXeID, String name, int toTram, int fromTram, int XeID) {
        this.VeXe = VeXe;
    }
    
}
