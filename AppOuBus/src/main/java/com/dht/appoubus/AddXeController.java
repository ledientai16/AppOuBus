/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.Xe;
import com.dht.services.XeService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // txtID chi co the nhap so toi da 10 ky tu
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
           xe = new Xe(id, bienSo, soGhe, loaiXe, namSX);
           if(XeService.addXe(xe) == true){
               alert.setContentText("Thêm thành công");
            }
           else alert.setContentText("Thêm thất bại, kiểm tra lại ID");
       } 
       else
           alert.setContentText("Nhập thiếu");
       alert.show();
    }
    
    
}
