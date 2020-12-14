/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.Tram;
import com.dht.pojo.TuyenDuong;
import com.dht.services.TramService;
import com.dht.services.TuyenDuongService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddTuyenDuongController implements Initializable {

    @FXML ChoiceBox<Tram> choiceFromTram;
    @FXML ChoiceBox<Tram> choiceToTram;
    @FXML TextField txtTuyenDuongID;
    @FXML TextField txtTuyenDuongName;
    @FXML TextField txtKhoangCach;
    @FXML Label labelTime;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            choiceFromTram.setItems(FXCollections.observableArrayList(TramService.getTram("")));
            choiceToTram.setItems(FXCollections.observableArrayList(TramService.getTram("")));
        }     
        catch (SQLException ex) {
            Logger.getLogger(AddTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtTuyenDuongID.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<?extends String> observable, String oldValue, String newValue){
                if(!newValue.matches("\\d{0,10}"))
                    txtTuyenDuongID.setText(oldValue);
           }
        });
        
        txtKhoangCach.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<?extends String> observable, String oldValue,String newValue){
                if(!newValue.matches("\\d{0,10}"))
                    txtKhoangCach.setText(oldValue);
            }
        });
        //auto tinh toan thoi gian hoan thanh chuyen khi nhap khoang cach
        txtKhoangCach.textProperty().addListener(evt ->{
            double i = Double.parseDouble(txtKhoangCach.getText()) / 48;
            i = (double) Math.ceil(i * 1000) /1000;
            labelTime.setText(String.valueOf(i));
        });
    }    
    public void addTuyenDuongHandler() throws SQLException{
        int id = Integer.parseInt(txtTuyenDuongID.getText());
        
        Tram from = choiceFromTram.getValue();
        Tram to =   choiceToTram.getValue();
        String name = from.getName() + "-" + to.getName();
        int distance = Integer.parseInt(txtKhoangCach.getText());
       
       
        TuyenDuong tuyenduong = new TuyenDuong(id, name, distance, from,to);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(TuyenDuongService.addTuyen(tuyenduong) == true){
            alert.setContentText("add thành công");
        }
        else alert.setContentText("add thất bại");
        alert.show();
    }
    
}