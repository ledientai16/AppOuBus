/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.pojo.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class EmployeeService {
    public static List<Employee> getEmployee() throws SQLException{
        Connection conn = Utils.getConn();
        String sql = "Select * From employee";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        ResultSet rs = stm.executeQuery();
        
        List <Employee> listEmployees = new ArrayList<>();
        while(rs.next()){
           
            Employee e = new Employee(rs.getInt("EmployeeID"), rs.getString("Name"));
            listEmployees.add(e);
        }
        return listEmployees;
    }
}
