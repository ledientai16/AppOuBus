/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.Tram;
import com.dht.pojo.TuyenDuong;
import com.dht.pojo.Xe;
import com.dht.services.TuyenDuongService;
import com.dht.services.Utils;
import com.dht.services.XeService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
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
public class AddChuyenXeController implements Initializable {

    
    @FXML TextField txtGia;
    @FXML DatePicker date;
    @FXML ChoiceBox<Xe> choiceXe;
    @FXML ChoiceBox<TuyenDuong>choiceTuyenDuong;
    @FXML ChoiceBox<Integer>choiceBeginHours;
    @FXML ChoiceBox<Integer>choiceBeginMinutes;
    @FXML ChoiceBox<Integer> choiceEndHour; 
    @FXML ChoiceBox<Integer>choiceEndMinutes;
    private  ArrayList <Integer> hour;
    private ArrayList <Integer> minute;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      choiceBeginHours.setItems(FXCollections.observableArrayList(Utils.getListHour()));
      choiceBeginMinutes.setItems(FXCollections.observableArrayList(Utils.getListMinutes()));
      choiceEndHour.setItems(FXCollections.observableArrayList(Utils.getListHour()));
      choiceEndMinutes.setItems(FXCollections.observableArrayList(Utils.getListMinutes()));
        try {
            choiceXe.setItems(FXCollections.observableArrayList(XeService.getXe("")));
            choiceTuyenDuong.setItems(FXCollections.observableArrayList(TuyenDuongService.getTuyenDuong("")));
        } catch (SQLException ex) {
            Logger.getLogger(AddChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    } 
    public void addChuyenXeHandler(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Time begin = new Time(choiceBeginHours.getValue(), choiceBeginMinutes.getValue(), 0);
        Time end = new Time(choiceEndHour.getValue(), choiceEndMinutes.getValue(), 0);
        ArrayList <Time> list = (ArrayList <Time>) listSoChuyen(begin, end, choiceTuyenDuong.getValue().getTuyenDuongTime());
        alert.setContentText("Có " + list.size() +"Chuyến xe sẽ được tạo");
      
        alert.show();
    }
    public List <Time> listSoChuyen(Time begin, Time end, Time t){
        List <Time> listTime = new ArrayList<>(); 
        while(begin.getHours()<= end.getHours())
        {
            if((begin.getHours()== end.getHours() && begin.getMinutes() >= end.getMinutes()))
                break;
            else{
                int h = begin.getHours() + t.getHours();
                int m = begin.getMinutes() + t.getMinutes();
                if(m >= 60){
                    h++;
                    m -= 60;
                }
                begin.setHours(h);
                begin.setMinutes(m);
                Time i = new Time(begin.getHours(),begin.getMinutes(),0);
                listTime.add(i);
            }
        }
        return listTime;
    }
}
