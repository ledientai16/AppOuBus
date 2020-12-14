/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import com.dht.pojo.Tram;
import com.dht.pojo.TuyenDuong;
import com.dht.services.TuyenDuongService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class QuanLyTuyenDuongController implements Initializable {

    @FXML TableView <TuyenDuong> TableTuyenDuong;
    @FXML TextField txtFind;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTuyenDuong();
        try {
            loadData("");
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtFind.textProperty().addListener(es->{
            try {
                loadData(txtFind.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    public void loadTuyenDuong(){
        TableColumn colID = new TableColumn("Tuyến Đường ID");
        colID.setCellValueFactory(new PropertyValueFactory("tuyenDuongID"));
        
        TableColumn colName = new TableColumn("Tên tuyến đường");
        colName.setCellValueFactory(new PropertyValueFactory("tuyenDuongName"));
        
        TableColumn colFromTram = new TableColumn("Trạm Xuất phát");
        colFromTram.setCellValueFactory(new PropertyValueFactory("fromTram"));
        colFromTram.setCellFactory(column -> {
             TableCell <TuyenDuong, Tram> cell = new TableCell<>(){
                @Override
                public void updateItem(Tram item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty )
                        setText(null);
                    else{
                        String result = item.getTramID() + ". " + item.getName() + "(" +  item.getDiaChi()+")";
                        setText(result);
                        }
                }
            };
            return cell;
        });
        
        TableColumn colToTram = new TableColumn("Trạm đích đến");
        colToTram.setCellValueFactory(new PropertyValueFactory("toTram"));
        colToTram.setCellFactory(column -> {
            TableCell <TuyenDuong, Tram> cell = new TableCell<>(){
                @Override
                public void updateItem(Tram item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty)
                        setText(null);
                    else{
                        String result = item.getTramID() + ". " + item.getName() + "(" +  item.getDiaChi()+")";
                        setText(result);
                        }
                }
            };
            return cell;
        });
        TableColumn colDistance = new TableColumn("Khoảng cách(KM)");
        colDistance.setCellValueFactory(new PropertyValueFactory("distance"));
        
        
        TableColumn colTime = new TableColumn("Thời gian đi");
        colTime.setCellValueFactory(new PropertyValueFactory("tuyenDuongTime"));
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
        TableColumn colKhuHoi = new TableColumn("Mã tuyến khứ hồi");
        colKhuHoi.setCellValueFactory(new PropertyValueFactory("tuyenKhuHoiID"));
        
        TableTuyenDuong.getColumns().addAll(colID,colName,colFromTram,colToTram,colTime,colDistance,colKhuHoi);
    }
    public void loadData(String kw) throws SQLException{
        TableTuyenDuong.getItems().clear();
        TableTuyenDuong.setItems(FXCollections.observableArrayList(TuyenDuongService.getTuyenDuong(kw)));
    }
    public void showAddTuyenDuong() throws SQLException{
        try {
            AnchorPane addTram = FXMLLoader.load(getClass().getResource("AddTuyenDuong.fxml"));
            Scene scene = new Scene(addTram);
        
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm trạm");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
            loadData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteTuyenDuongHandler(){
        TuyenDuong selected = (TuyenDuong) TableTuyenDuong.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String id =String.valueOf( selected.getTuyenDuongID());
        String name = selected.getTuyenDuongName();
        alert.setContentText("Bạn chắc chắn xóa Tuyến đường: TuyenDuongId =" + id +" tên tuyến đường = " + name + "?");
        alert.showAndWait().ifPresent(res -> {
            try {
                
                if(TuyenDuongService.deleteTuyenDuong(id) == true){
                    alert.setContentText("Đã xóa thành công");
                    try {
                        loadData("");
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                    alert.setContentText("Xóa thất bại?Dữ liệu tuyến đường có thể đang được dùng");
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
            alert.show();
        });
        
    }
    public void refreshHandler() throws SQLException{
        loadData("");
    }
    public void taoKhuHoiHanler(){
        TuyenDuong selected = (TuyenDuong) TableTuyenDuong.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String id =String.valueOf( selected.getTuyenDuongID());
        String name = selected.getTuyenDuongName();
        alert.setContentText("Bạn muốn tạo tuyến khứ hồi cho " + selected.getTuyenDuongID() +
                            selected.getTuyenDuongName() + "?");
        alert.showAndWait().ifPresent(res -> {
            try {
                if(TuyenDuongService.taoTuyenKhuHoi(selected) == true){
                    alert.setContentText("Đã tạo thành công thành công");
                    try {
                        loadData("");
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                    alert.setContentText("Tạo thất bại?Có thể đã có tuyến khứ hồi hoặc lỗi");
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
            alert.show();
        });
        
    }
    }

