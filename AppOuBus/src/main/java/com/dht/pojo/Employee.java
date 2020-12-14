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
public class Employee {
    private int employeeID;
    private String name;

    public Employee() {
    }

    public Employee(int employeeID, String name) {
        this.employeeID = employeeID;
        this.name = name;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return employeeID + ". " + name;
    }
    
    
}
