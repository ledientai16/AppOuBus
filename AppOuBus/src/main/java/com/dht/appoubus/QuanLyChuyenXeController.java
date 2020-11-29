/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;
import com.dht.pojo.ChuyenXe;
import com.dht.services.ChuyenXeService;
import com.dht.services.Utils;
import com.dht.pojo.ChuyenXe;
import com.dht.services.ChuyenXeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class QuanLyChuyenXeController implements Initializable {

    @FXML TableView <ChuyenXe> tableChuyenXe;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadChuyenXe();
        loadData("");
        
    }    
    //create table view
    public void loadChuyenXe(){
        TableColumn colID = new TableColumn("Chuyến Xe ID");
        colID.setCellValueFactory(new PropertyValueFactory("chuyenXeID"));
        
        TableColumn colMoTa = new TableColumn("Mô tả");
        colMoTa.setCellValueFactory(new PropertyValueFactory("moTa"));
        
        tableChuyenXe.getColumns().addAll(colID,colMoTa);
    }
    //fuction load data to TableChuyenXe from DB
    public void loadData(String kw) {
        
        try {
            tableChuyenXe.setItems(FXCollections.observableArrayList(ChuyenXeService.getChuyenXe()));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
