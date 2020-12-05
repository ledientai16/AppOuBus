/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.appoubus;

import java.sql.Date;
import com.dht.pojo.Xe;

import com.dht.services.XeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class QuanLyXeController implements Initializable {

    @FXML TableView <Xe>tableXe; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            loadXe();
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    } 
    //load table xe
    public void loadXe(){
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        TableColumn colID = new TableColumn("Xe ID");
        colID.setCellValueFactory(new PropertyValueFactory("xeID"));
        
        TableColumn colBienXo = new TableColumn("Biển số xe");
        colBienXo.setCellValueFactory(new PropertyValueFactory("bienSo"));
        
        TableColumn colLoai = new TableColumn("Loại xe");
        colLoai.setCellValueFactory(new PropertyValueFactory("loaiXe"));
        
        TableColumn colSoGhe = new TableColumn("Số ghế");
        colSoGhe.setCellValueFactory(new PropertyValueFactory("soGhe"));
        
        TableColumn colNamSX = new TableColumn("Năm sản xuất");
        colNamSX.setCellValueFactory(new PropertyValueFactory("namSX"));

        colNamSX.setCellFactory(column ->{ 
            TableCell <Xe,Date> cell =new TableCell<Xe,Date>(){
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
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(et -> {
            TableCell cell = new TableCell();
            Button btn = new Button("XÓA");
            btn.setOnAction(evt -> {
                Button b = (Button) evt.getSource();
                TableCell c = (TableCell) b.getParent();
                Xe x = (Xe) c.getTableRow().getItem();
                
                String id = String.valueOf(x.getXeID());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn có muốn xóa không?");
                alert.showAndWait().ifPresent(res -> {
                        try {
                            if(XeService.deleteXe(id) == true){
                               alert.setContentText("Đã xóa");
                               loadData();
                            }
                            else
                                alert.setContentText("Xóa thất bại");
                               } catch (SQLException ex) {
                            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    alert.show();
            });
              
            });
            cell.setGraphic(btn);
            return cell;
        });
        colID.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.2));
        colBienXo.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.2));
        colLoai.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.2));
        colNamSX.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.2));
        colAction.prefWidthProperty().bind(tableXe.widthProperty().multiply(0.2));
        tableXe.getColumns().addAll(colID,colBienXo,colLoai,colSoGhe,colNamSX,colAction);
   }
   //load data xe
   public void loadData() throws SQLException{
       tableXe.getItems().clear();
       tableXe.setItems(FXCollections.observableArrayList(XeService.getXe()));
       
   }
   //chọn auto ID;
   
   
   //show ui add xe
   public void showAddXe() throws SQLException{
        try {
            AnchorPane addTram = FXMLLoader.load(getClass().getResource("AddXe.fxml"));
            Scene scene = new Scene(addTram);
        
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Chức năng thêm Xe");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
            loadData();
        } catch (IOException ex) {
            Logger.getLogger(QuanLyTramController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
