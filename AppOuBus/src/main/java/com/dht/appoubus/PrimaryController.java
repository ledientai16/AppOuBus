package com.dht.appoubus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrimaryController {
    
    @FXML BorderPane rootPane;
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }   
    
    /**
     *
     *  Event power off
     * Exit APPLiCATION when Click power off
     * 
     */
    public void close (MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    
    public void loadUI(String UI) throws IOException{
       
        AnchorPane pane = FXMLLoader.load(getClass().getResource(UI));
        rootPane.setCenter(pane);
 
        
   }
        
    
   
  
    public void loadQuanLyChuyenXeUI(ActionEvent e){
        
        try {
            loadUI("QuanLyChuyenXe.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadQuanLyTramUI(ActionEvent e){
        
        try {
            loadUI("QuanLyTram.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadQuanLyXeUI(ActionEvent e){
        try {
            loadUI("QuanLyXe.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadLoginUI(){
        
        try {
            loadUI("Login.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
