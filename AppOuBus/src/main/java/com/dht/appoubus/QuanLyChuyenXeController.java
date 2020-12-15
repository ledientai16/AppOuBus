/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;
import com.dht.services.ChuyenXeService;
import com.dht.services.Utils;
import com.dht.pojo.ChuyenXe;
import com.dht.pojo.TuyenDuong;
import com.dht.pojo.Xe;
import com.dht.services.ChuyenXeService;
import static com.dht.services.TramService.addTram;
import com.dht.services.TuyenDuongService;
import com.dht.services.XeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class QuanLyChuyenXeController implements Initializable {

    @FXML TableView <ChuyenXe> tableChuyenXe;
    
    @FXML ChoiceBox <TuyenDuong> choiceTuyenDuong;
    @FXML DatePicker date;
    @FXML ChoiceBox <Xe> choiceXe;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            choiceTuyenDuong.setItems(FXCollections.observableArrayList(TuyenDuongService.getTuyenDuong("")));
            choiceXe.setItems(FXCollections.observableArrayList(XeService.getXe("")));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadChuyenXe();
        loadData("");
        
    }    
    //create table view
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
        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("giaTien"));
        
        colID.prefWidthProperty().bind(tableChuyenXe.widthProperty().multiply(0.1));
        colXe.prefWidthProperty().bind(tableChuyenXe.widthProperty().multiply(0.2));
        colTuyenDuong.prefWidthProperty().bind(tableChuyenXe.widthProperty().multiply(0.3));
        colDate.prefWidthProperty().bind(tableChuyenXe.widthProperty().multiply(0.1));
        colTime.prefWidthProperty().bind(tableChuyenXe.widthProperty().multiply(0.1));
        colSoVe.prefWidthProperty().bind(tableChuyenXe.widthProperty().multiply(0.1));
        colGia.prefWidthProperty().bind(tableChuyenXe.widthProperty().multiply(0.1));
        tableChuyenXe.getColumns().addAll(colID,colXe,colTime,colDate,colSoVe,colGia,colTuyenDuong);
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
    public void loadData(Date date, int xeID, int TuyenDuongID) {
       
        try {
            tableChuyenXe.getItems().clear();
            tableChuyenXe.setItems(FXCollections.observableArrayList(ChuyenXeService.getChuyenXe(date, xeID, TuyenDuongID)));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void showAddChuyenXe() throws SQLException, IOException{
        try {
            AnchorPane addChuyenXe = FXMLLoader.load(getClass().getResource("AddChuyenXe.fxml"));
            Scene scene = new Scene(addChuyenXe);
        
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng tạo chuyến xe theo time");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
            loadData("");
        } catch (IOException ex) {
            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void findHanler(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Tìm theo lựa chọn");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType action) {
                if(date.getValue()==null || choiceTuyenDuong.getValue()==null || choiceXe.getValue() ==null){
                    alert.setContentText("Nhập thiếu dữ kiện");
                }
                else{
                    
                    loadData(Date.valueOf(date.getValue()), choiceXe.getValue().getXeID(), choiceTuyenDuong.getValue().getTuyenDuongID());
                    }
            }
        });
    }

    /**
     *
     */
    public void refreshHanler(){
        
        loadData("");
    }
    public void deleteChuyenXeHandler(){
        ChuyenXe selected = (ChuyenXe) tableChuyenXe.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        int id =selected.getChuyenXeID();
        alert.setContentText("Bạn chắc chắn xóa Tuyến đường: TuyenDuongId = " + id + "?");
        alert.showAndWait().ifPresent(res -> {
            try {
                
                if(ChuyenXeService.deleteChuyenXe(id) == true){
                    alert.setContentText("Đã xóa thành công");
                    loadData("");
                }
                else
                    alert.setContentText("Xóa thất bại?Dữ liệu tuyến đường có thể đang được dùng");
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTuyenDuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
            alert.show();
        });
        }
   
}