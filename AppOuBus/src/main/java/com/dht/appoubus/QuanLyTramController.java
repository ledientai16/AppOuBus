/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.Tram;
import com.dht.services.TramService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class QuanLyTramController implements Initializable {
    @FXML TableView <Tram> tableTram;
    @FXML TextField txtName;
    @FXML TextField txtDiaChi;
    /**
     * Initializes the controller class.
     */
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableTram();
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void loadTableTram(){
        TableColumn colID = new TableColumn("Trạm ID");
        colID.setCellValueFactory(new PropertyValueFactory("tramID"));
       
        TableColumn colName = new TableColumn("Tên Trạm");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn colDiaChi = new TableColumn("Địa Chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
        //thiet lap kich thuoc tableColumn
        colID.prefWidthProperty().bind(tableTram.widthProperty().multiply(0.2));
        colName.prefWidthProperty().bind(tableTram.widthProperty().multiply(0.3));
        colDiaChi.prefWidthProperty().bind(tableTram.widthProperty().multiply(0.5));
        
        tableTram.getColumns().addAll(colID, colName, colDiaChi);
    }
    public void loadData() throws SQLException{
        tableTram.getItems().clear();
        tableTram.setItems(FXCollections.observableArrayList(TramService.getTram()));
        
    }
    public void addTramHandler(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Tram tram = new Tram();
        String name = txtName.getText();
        String diaChi = txtDiaChi.getText();
        if(!name.equals("")&& !diaChi.equals("")){
            tram.setDiaChi(diaChi);
            tram.setName(name);
            if(TramService.addTram(tram) == true)
            {
                alert.setContentText("Đã thêm");
                try {
                    loadData();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else alert.setContentText("Lỗi");
        }
        else{
            alert.setContentText("Nhập thiếu!");
        }
        alert.show();
    }
}
