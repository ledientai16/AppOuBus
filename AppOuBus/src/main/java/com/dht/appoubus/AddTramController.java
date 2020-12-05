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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddTramController implements Initializable {
    @FXML TextField txtID;
    @FXML TextField txtName;
    @FXML TextField txtDiaChi;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // listener chi nhap duoc so 10 ky tu
        txtID.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(!newValue.matches("\\d{0,10}"))
                    txtID.setText(oldValue);
            }
        });
    }    
    public void addTramHandler(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Tram tram = new Tram();
        String name = txtName.getText();
        String diaChi = txtDiaChi.getText();        
        if(!name.equals("")&& !diaChi.equals("")){
            tram.setDiaChi(diaChi);
            tram.setName(name);
            tram.setTramID(Integer.parseInt(txtID.getText()));
            if(TramService.addTram(tram) == true)
            {
                
                alert.setContentText("Đã thêm. Hay refresh lại quản lý trạm");
            }
            else alert.setContentText("Lỗi");
        }
        else{
            alert.setContentText("Nhập thiếu!");
        }
        alert.show();
    }
    public void clearHandler(){
        txtDiaChi.setText("");
        txtID.setText("");
        txtName.setText("");
    }
}
