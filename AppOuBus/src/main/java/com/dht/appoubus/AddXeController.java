/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.Tram;
import com.dht.pojo.Xe;
import com.dht.services.TramService;
import com.dht.services.XeService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddXeController implements Initializable {

    @FXML TextField txtID;
    @FXML TextField txtBienSo;
    @FXML TextField txtSoGhe;
    @FXML TextField txtLoaiXe;
    @FXML DatePicker dateNamSX;
    @FXML ChoiceBox<Tram> choiceTram;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            choiceTram.setItems(FXCollections.observableArrayList(TramService.getTram("")));
        } catch (SQLException ex) {
            Logger.getLogger(AddXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // listener chi nhap duoc so voi 10 ky ty
          txtID.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(!newValue.matches("\\d{0,10}"))
                    txtID.setText(oldValue);
            }
        });
        txtSoGhe.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(!newValue.matches("\\d{0,10}"))
                    txtID.setText(oldValue);
            }
        });

    }    
    //xoa tram khhi click button
    public void addXeHandler() throws SQLException{
       int id = Integer.parseInt(txtID.getText());
       String bienSo = txtBienSo.getText();
       int soGhe = Integer.parseInt(txtSoGhe.getText());
       String loaiXe = txtLoaiXe.getText();
       Date namSX = Date.valueOf(dateNamSX.getValue());
       
       Xe xe;
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       if(!loaiXe.equals("") && !bienSo.equals(""))
       {
           xe = new Xe(id, bienSo, soGhe, loaiXe, namSX,choiceTram.getValue());
           if(XeService.addXe(xe) == true){
               alert.setContentText("Thêm thành công");
               clearHandler();
            }
           else alert.setContentText("Thêm thất bại, kiểm tra lại ID");
       } 
       else
           alert.setContentText("Nhập thiếu");
       alert.show();
    }
    public void clearHandler(){
        dateNamSX.setValue(LocalDate.EPOCH);
        txtID.setText("");
        txtLoaiXe.setText("");
        txtBienSo.setText("");
        txtSoGhe.setText("");
    }
    
}
