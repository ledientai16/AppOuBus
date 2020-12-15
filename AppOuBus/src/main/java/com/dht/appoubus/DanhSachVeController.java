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
import com.dht.services.TramService;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private int tramID;
    private int chuyenID;
    private Date date;
    
    @FXML VBox box;
    @FXML Label txtXe;
    @FXML Label txtChuyenXe;
    @FXML Label txtFrom; 
    @FXML Label txtTo;
    @FXML Label txtTime;
    @FXML Label txtDate;
    @FXML Label txtGia;
   
    @FXML Label txtEmployee;
    @FXML TextField txtTimeBook;
    @FXML Label txtSoGhe;
    @FXML TextField txtKhachHang;
    @FXML TextField txtphone;
    
    @FXML ChoiceBox <ChuyenXe> choiceChuyen;
    @FXML TextField txtNewGhe;
    @FXML Label txtChangeGhe;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
                
            // load chuyen và trạm từ bán vé form
            txtEmployee.setText(BanVeController.getNhanVien().toString());
            txtNewGhe.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(!newValue.matches("\\d{0,10}"))
                    txtNewGhe.setText(oldValue);
            }
            });
            date = BanVeController.getNgayChay();
            tramID = BanVeController.getTramID();
            chuyenID = BanVeController.getChuyenID();
            loadChiTiet(ChuyenXeService.getChuyenByID(chuyenID));
            loadBox();
            if(VeXeService.checkTimeDatVe(ChuyenXeService.getChuyenByID(chuyenID).getBeginTime(), date) == true)
                VeXeService.deleteAllVeDat();
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
                VeXe v = VeXeService.getVeXeBySoGhe(Integer.parseInt(b.getText()), chuyenID);
                if(VeXeService.kiemTraDat(Integer.parseInt(b.getText()), chuyenID) == true )
                {
                   if(v.getVeDat() == 1)
                       b.setId("vedat");
                    else
                       b.setId("isbook");
                }
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
    //Click button
    public void loadTicket(int soGhe) throws SQLException{
        txtSoGhe.setText(String.valueOf(soGhe));
        
            VeXe v = VeXeService.getVeXeBySoGhe(soGhe, chuyenID);
            if(v != null){
                txtKhachHang.setText(v.getKhachHangName());
                txtphone.setText(v.getPhone());
                Time t = v.getGioDat();
                txtTimeBook.setText(t.getHours()+ ":" +t.getMinutes()+":00" );
                loadChangeBox(v.getSoGhe());
            }
    }
    
    public void SellVeHandler() throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ChuyenXe c = ChuyenXeService.getChuyenByID(chuyenID);
        if(VeXeService.checkTimeMuaVe(c.getBeginTime(), c.getDate()) == true){
          VeXe v = new VeXe(ChuyenXeService.getChuyenByID(chuyenID), txtphone.getText(), 
                    Time.valueOf(LocalTime.now()), txtKhachHang.getText(), Integer.parseInt(txtSoGhe.getText()));
            if(VeXeService.kiemTraDat(Integer.parseInt(txtSoGhe.getText()), chuyenID) == false ){
                if(VeXeService.addVeXe(v)){
                alert.setContentText("Mua vé thành công");
                loadBox();
                }
            }
            else
                alert.setContentText("Vé đã bán rồi");
        
        }
        else alert.setContentText("Chuyến này đã qua thời gian đặt");
        alert.show();
    }
    
    //Class đặt vé
    public void datVeHandler() throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ChuyenXe c = ChuyenXeService.getChuyenByID(chuyenID);
        if(VeXeService.checkTimeDatVe(c.getBeginTime(), c.getDate()) == true){
          VeXe v = new VeXe(ChuyenXeService.getChuyenByID(chuyenID), txtphone.getText(), 
                    Time.valueOf(LocalTime.now()), txtKhachHang.getText(), Integer.parseInt(txtSoGhe.getText()));
        if(VeXeService.kiemTraDat(Integer.parseInt(txtSoGhe.getText()), chuyenID) == false ){
            if(VeXeService.datVeXe(v)){
                alert.setContentText("đặt vé thành công");
                loadBox();
            }
            else
                alert.setContentText("Vé đã bán rồi");
        }
        }
         else alert.setContentText("Chuyến này đã qua thời gian đặt");
        alert.show();
    }
    public void DeleteVeDatHandler() throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(VeXeService.deleteVeDat(Integer.parseInt(txtSoGhe.getText()), chuyenID) == true)
        {  
            alert.setContentText("Đã hủy vé đặt");
            loadBox();
        }
        else alert.setContentText("ghế này có đặt đâu mà xóa");
        alert.show();
    }
    public void nhanVeDatHandler() throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(VeXeService.nhanVeDat(Integer.parseInt(txtSoGhe.getText()), chuyenID) == true)
        {  
            alert.setContentText("Khách Nhận Vé");
            loadBox();
        }
        else alert.setContentText("Vé này chưa được đặt hoặc được bán");
        alert.show();
    }
    public void loadChangeBox(int i) throws SQLException{
        choiceChuyen.setItems(FXCollections.observableArrayList(ChuyenXeService.getChuyenXe(date,tramID)));
        txtChangeGhe.setText(String.valueOf(i));
    }
    //thuc hien doi chuyen ve va ghe cho ve da dat
    public void changeVeDatHandler() throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(!"".equals(txtChangeGhe.getText())){
           VeXe v = VeXeService.getVeXeBySoGhe(Integer.parseInt(txtChangeGhe.getText()), chuyenID);
           if(txtNewGhe.getText().equals("") || choiceChuyen.getValue() == null){
               alert.setContentText("Nhap thieu");
           }
           else
           {    
               ChuyenXe c = choiceChuyen.getValue();
               if(VeXeService.checkTimeDatVe(c.getBeginTime(), c.getDate()))
               { 
                   if(Integer.parseInt(txtNewGhe.getText()) <= c.getSoVe()){
                        if(true== VeXeService.ChangeVeDat(v,Integer.parseInt(txtNewGhe.getText()),choiceChuyen.getValue().getChuyenXeID()))
                        {
                            txtChangeGhe.setText("");
                            loadBox();
                            alert.setContentText("Đã đổi thành công");
                        }
                        else alert.setContentText("Đã có người đặt hoặc mua rồi");
                            
                        
                   }   
                   else alert.setContentText("Không có ghế này");
                }                
               else
                   alert.setContentText("Chuyến này đã quá giờ đặt");
           }
        }
        else
            alert.setContentText("Nhập thiếu thông tin");
        alert.show();
    }
}

