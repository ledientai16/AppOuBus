/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.ChuyenXe;
import com.dht.pojo.KhungGio;
import com.dht.pojo.Tram;
import com.dht.pojo.TuyenDuong;
import com.dht.pojo.Xe;
import com.dht.services.ChuyenXeService;
import com.dht.services.KhungGioService;
import com.dht.services.TuyenDuongService;
import com.dht.services.Utils;
import com.dht.services.XeService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
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
    @FXML DatePicker datePicker;
    @FXML ChoiceBox<Xe> choiceXe;
    @FXML ChoiceBox<TuyenDuong>choiceTuyenDuong;
    @FXML ChoiceBox<KhungGio>choiceKhungGio;
    private  ArrayList <Integer> hour;
    private ArrayList <Integer> minute;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
      
        try {
            choiceKhungGio.setItems(FXCollections.observableArrayList(KhungGioService.getKhungGio()));
            choiceXe.setItems(FXCollections.observableArrayList(XeService.getXe("")));
            choiceTuyenDuong.setItems(FXCollections.observableArrayList(TuyenDuongService.getTuyenDuong("")));
        } catch (SQLException ex) {
            Logger.getLogger(AddChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    } 
    public void addChuyenXeHandler(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Time begin = choiceKhungGio.getValue().getBeginTime();
        Time end = choiceKhungGio.getValue().getEndTime();
        ArrayList <Time> list = (ArrayList <Time>) KhungGioService.listSoChuyen(begin, end, choiceTuyenDuong.getValue().getTuyenDuongTime());
        if(choiceTuyenDuong.getValue().getTuyenKhuHoiID() == 0)
            alert.setContentText("Chỉ có thể tạo với tuyến có tuyến khứ hồi");
        alert.setContentText("Sẽ có " + list.size() +"Chuyến xe sẽ được tạo");
        Date d = Date.valueOf(datePicker.getValue());
        double gia = Double.valueOf(txtGia.getText());
        
        alert.showAndWait().ifPresent(action ->{
            try {
                if(choiceTuyenDuong.getValue().getTuyenKhuHoiID() == 0)
                    alert.setContentText("Tuyến này chưa có khứ hồi");
                else if(begin != null && end != null&& ChuyenXeService.CheckChuyenXe(choiceXe.getValue().getXeID(), d) == 0)
                {
                    for(int i = 0; i < list.size(); i++){
                        ChuyenXe cx;
                        if(i % 2 == 0){
                                cx = new ChuyenXe(choiceTuyenDuong.getValue(),list.get(i),choiceXe.getValue(),d,
                                gia, choiceXe.getValue().getSoGhe() - 1);
                        }
                        else
                                cx = new ChuyenXe(TuyenDuongService.getTuyenDuongByID(choiceTuyenDuong.getValue().getTuyenKhuHoiID()),list.get(i),choiceXe.getValue(),d,
                                gia, choiceXe.getValue().getSoGhe() - 1);
                        ChuyenXeService.addChuyenXe(cx);
                    }
                    alert.setContentText("Tạo Thành Công");
                }   
                else alert.setContentText("Lỗi nhập thiếu");
            } catch (SQLException ex) {
                Logger.getLogger(AddChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        alert.show();
    }
   
}
