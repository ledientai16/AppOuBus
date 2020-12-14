/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.ChuyenXe;
import com.dht.pojo.TuyenDuong;
import com.dht.pojo.Xe;
import com.dht.services.ChuyenXeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class BanVeController implements Initializable {
 
    /**
     * Initializes the controller class.
     */
    @FXML Label txtXe;
    @FXML Label txtChuyenXe;
    @FXML Label txtFrom; 
    @FXML Label txtTo;
    @FXML Label txtTime;
    @FXML Label txtDate;
    @FXML Label txtSoVe;
    @FXML Label txtDaBan;
    @FXML Label txtGia;
    @FXML TableView <ChuyenXe> tableChuyenXe;
    @FXML ChoiceBox <TuyenDuong> choiceTuyenDuong;
    @FXML DatePicker date;
    private static int chuyenID;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadChuyenXe();
        loadData("");
        
    }    
    
     public void loadChuyenXe(){
        TableColumn colID = new TableColumn("Chuyến Xe ID");
        colID.setCellValueFactory(new PropertyValueFactory("chuyenXeID"));
        
        TableColumn colTuyenDuong = new TableColumn("Tuyến Đường");
        
        colTuyenDuong.setCellValueFactory(new PropertyValueFactory("tuyenDuong"));
        colTuyenDuong.setCellFactory(column -> {
            TableCell <ChuyenXe, TuyenDuong> cell = new TableCell<>(){
                 
                 @Override
                 public void updateItem(TuyenDuong item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty )
                        setText(null);
                    else{
                        String result = item.getTuyenDuongID() + ". " + 
                                        item.getTuyenDuongName();
                        setText(result);
                        }
                }
            };
            return cell;
        });
        TableColumn colXe = new TableColumn("Xe chạy");
        
        colXe.setCellValueFactory(new PropertyValueFactory("xe"));
        colXe.setCellFactory(column -> {
             TableCell <ChuyenXe, Xe> cell = new TableCell<>(){
                 @Override
                 public void updateItem(Xe item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty )
                        setText(null);
                    else{
                        String result = item.getXeID()+ ". " + 
                                        item.getBienSo() + "(" + + item.getSoGhe() +")";
                        setText(result);
                        }
                }
            };
            return cell;
        });
        
        TableColumn colDate = new TableColumn("Ngày chạy");
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colDate.setCellFactory(column ->{ 
            TableCell <ChuyenXe,Date> cell =new TableCell<>(){
                private final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                @Override
                public void updateItem(Date item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setText(null);
                    }
                    else
                        setText(f.format(item));
                }
                        
             };
            
            return cell;
        });
        TableColumn colTime = new TableColumn("Thời gian đi");
        colTime.setCellValueFactory(new PropertyValueFactory("beginTime"));
        colTime.setCellFactory(column -> {
            TableCell <TuyenDuong, Time> cell = new TableCell<>(){
                @Override
                public void updateItem(Time item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty&& item == null)
                        setText(null);
                    else{
                        String result = item.getHours() + ":" +item.getMinutes() + ":00";
                        setText(result);
                        }
                }
            };
            return cell;
        });
        TableColumn colSoVe = new TableColumn("Lượng Vé");
        colSoVe.setCellValueFactory(new PropertyValueFactory("soVe"));
        tableChuyenXe.getColumns().addAll(colID,colXe,colTime,colDate,colSoVe,colTuyenDuong);
    }
    //fuction load data to TableChuyenXe from DB
    public void loadData(String kw) {
       
        try {
            tableChuyenXe.getItems().clear();
            tableChuyenXe.setItems(FXCollections.observableArrayList(ChuyenXeService.getChuyenXe()));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadData(Date date, int TuyenDuongID) {
       
        try {
            tableChuyenXe.getItems().clear();
            tableChuyenXe.setItems(FXCollections.observableArrayList(ChuyenXeService.getChuyenXe(date, TuyenDuongID)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void showDanhSachVe() throws SQLException, IOException{
        try {
            if(this.chuyenID == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Chưa chọn chuyến");
                alert.show();
            }
            else{
            AnchorPane danhSachVe = FXMLLoader.load(getClass().getResource("DanhSachVe.fxml"));
            Scene scene = new Scene(danhSachVe);
        
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng tạo chuyến xe theo time");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
            loadData("");
            }
        } catch (IOException ex) {
            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //Load Chi tiet
     public void loadChiTiet(ChuyenXe cx){
         txtChuyenXe.setText(String.valueOf(cx.getChuyenXeID()));
         txtXe.setText(cx.getXe().toString());
         txtFrom.setText(cx.getTuyenDuong().getToTram().toString());
         txtTo.setText(cx.getTuyenDuong().getFromTram().toString());
         txtDaBan.setText(String.valueOf(ChuyenXeService.soVeDaBan(cx.getChuyenXeID())));
         SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
         String date = f.format(cx.getDate());
         String time = cx.getBeginTime().toString();
         txtDate.setText(date);
         txtTime.setText(cx.getBeginTime().getHours() + ":" + cx.getBeginTime().getMinutes() + ":00");
         txtSoVe.setText(String.valueOf(cx.getSoVe()));
         this.chuyenID = cx.getChuyenXeID();
     }
     
    public void clickItem(MouseEvent event)
    {
        if(event.getClickCount() == 2){
            ChuyenXe cx = (ChuyenXe)tableChuyenXe.getSelectionModel().getSelectedItem();
            loadChiTiet(cx);
        }
    }

    public static void setChuyenID(int soGhe) {
        BanVeController.chuyenID = soGhe;
    }

    public static int getChuyenID() {
        return chuyenID;
    }
    public void findHanler(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tìm theo lựa chọn");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType action) {
                if(date.getValue()==null || choiceTuyenDuong.getValue()==null){
                    alert.setContentText("Nhập thiếu dữ kiện");
                }
                else{
                    
                    loadData(Date.valueOf(date.getValue()), choiceTuyenDuong.getValue().getTuyenDuongID());
                    }
            }
        });
    }
   
    
}
