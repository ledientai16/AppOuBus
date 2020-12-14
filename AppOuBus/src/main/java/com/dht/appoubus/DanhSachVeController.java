/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.ChuyenXe;
import com.dht.pojo.VeXe;
import com.dht.services.ChuyenXeService;
import com.dht.services.KhungGioService;
import com.dht.services.Utils;
import com.dht.services.VeXeService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DanhSachVeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int chuyenID;
    @FXML VBox box;
    @FXML Label txtXe;
    @FXML Label txtChuyenXe;
    @FXML Label txtFrom; 
    @FXML Label txtTo;
    @FXML Label txtTime;
    @FXML Label txtDate;
    @FXML Label txtGia;
   
    @FXML Label txtEmployee;
    @FXML Label txtTimeBook;
    @FXML TextField txtSoGhe;
    @FXML TextField txtKhachHang;
    @FXML TextField txtphone;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            chuyenID = BanVeController.getChuyenID();
            loadChiTiet(ChuyenXeService.getChuyenByID(chuyenID));
            
            loadBox();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    public void loadBox() throws SQLException{
        box.getChildren().clear();
        ChuyenXe cx = ChuyenXeService.getChuyenByID(chuyenID);
        int soVe = cx.getSoVe();
        int temp = 1;
        for(int i =0 ; i <= soVe / 5; i++)   
        {
            ArrayList <Button> listButton = new ArrayList <>();
            int count = 1;
            while(temp <= soVe && count <= 5){
                Button b = new Button(String.valueOf(temp));
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                     public void handle(ActionEvent event) {
                        try {
                            loadTicket(Integer.parseInt(b.getText()));
                        } catch (SQLException ex) {
                            Logger.getLogger(DanhSachVeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }   
                });
                if(VeXeService.kiemTraDat(Integer.parseInt(b.getText()), chuyenID) == false)
                    b.setId("isbook");
                else
                    b.setId("isnotbook");
                listButton.add(b);
                temp++;
                count++;
            }
            HBox h = new HBox();
            h.getChildren().addAll(listButton);
            addHBox(h);
        }
    }
    public void addHBox(HBox v){
        box.getChildren().add(v);
    }
    public void loadChiTiet(ChuyenXe cx){
         txtChuyenXe.setText(String.valueOf(cx.getChuyenXeID()));
         txtXe.setText(cx.getXe().toString());
         txtFrom.setText(cx.getTuyenDuong().getToTram().toString());
         txtTo.setText(cx.getTuyenDuong().getFromTram().toString());
         
         SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
         String date = f.format(cx.getDate());
         String time = cx.getBeginTime().toString();
         txtDate.setText(date);
         txtTime.setText(cx.getBeginTime().getHours() + ":" + cx.getBeginTime().getMinutes() + ":00");
    }
    public void loadTicket(int soGhe) throws SQLException{
        txtSoGhe.setText(String.valueOf(soGhe));
        if(VeXeService.kiemTraDat(soGhe, chuyenID) == true){
            txtKhachHang.setText(VeXeService.getVeXeBySoGhe(soGhe, chuyenID).getKhachHangName());
            txtphone.setText(VeXeService.getVeXeBySoGhe(soGhe, chuyenID).getPhone());
        }
    }
    public void SellVeHandler() throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        VeXe v = new VeXe(ChuyenXeService.getChuyenByID(chuyenID), txtphone.getText(), 
                Time.valueOf(LocalTime.now()), txtKhachHang.getText(), Integer.parseInt(txtSoGhe.getText()));
        if(VeXeService.kiemTraDat(Integer.parseInt(txtSoGhe.getText()), chuyenID) == true &&
           Utils.checkTime(ChuyenXeService.getChuyenByID(chuyenID).getBeginTime(), ChuyenXeService.getChuyenByID(chuyenID).getDate()) == true){
            if(VeXeService.addVeXe(v)){
            alert.setContentText("Mua vé thành công");
            loadBox();
            }
        }
        else
            alert.setContentText("Vé đã mua rồi");
        alert.show();
    }
}

