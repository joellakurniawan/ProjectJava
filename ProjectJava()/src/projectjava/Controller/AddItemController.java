/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author JOELLA
 */
public class AddItemController {
    
    @FXML
    private JFXButton additem_cancel;

    @FXML
    private JFXButton additem_add;
    
    @FXML
    private JFXTextField item_nama;
    
    @FXML
    private JFXTextField item_merk;
    
    @FXML
    private JFXTextField item_stock;
    
    @FXML
    private JFXTextField item_hargapokok;
    
    @FXML
    private JFXTextField item_hargajual;
    
    @FXML
    private Label item_id;
    
    @FXML
    public void initialize(){
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    statement = con.createStatement();
                    int masuk=0;
                    ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'item'");
                    while(rs.next()){
                        masuk=rs.getInt("AUTO_INCREMENT");
                    }
                    item_id.setText(String.valueOf(masuk));
                    rs.close();
                } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                }finally{
                try {
                if(statement!=null)
                   statement.close();
                if(con!=null)
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    }
                 }
        
    }
    
    @FXML
    public void additem_add_btn(){
                Connection con = null;
                Statement statement = null;
                try {
                    if(item_nama.getText().isEmpty() == false){
                        if(item_merk.getText().isEmpty() == false){
                            if(item_stock.getText().isEmpty() == false){
                                if(item_hargapokok.getText().isEmpty() == false){
                                    if(item_hargajual.getText().isEmpty() == false){
                                        con = ConnectionFactory.getConnection();
                                        statement = con.createStatement();
                                        statement.executeUpdate("INSERT INTO item(nama, merk,stock,harga_jual,harga_pokok,status_aktif,id_lokasi) values ('"+item_nama.getText().toString()+"','"+item_merk.getText().toString()+"',"+item_stock.getText().toString()+","+item_hargajual.getText().toString()+","+item_hargapokok.getText().toString()+", 1, 1)");
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("");
                                        alert.setHeaderText("Berhasil!");

                                        ButtonType buttonTypeOK = new ButtonType("OK");
                                        alert.getButtonTypes().setAll(buttonTypeOK);
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.get() == buttonTypeOK){
                                            try{
                                                    con = ConnectionFactory.getConnection();
                                                    statement = con.createStatement();
                                                    Stage stage = (Stage) additem_cancel.getScene().getWindow();
                                                    stage.close();
                                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/item.fxml"));
                                                    Parent root1 = (Parent) fxmlLoader.load();
                                                    stage = new Stage();
                                                    Scene scene = new Scene(root1);
                                                    stage.setTitle("ITEM");
                                                    stage.setScene(scene);
                                                    stage.show();
                                            } catch (Exception e){
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    }
                                    else{
                                        Alert alertEmail = new Alert(Alert.AlertType.WARNING);
                                        alertEmail.setHeaderText(null);
                                        alertEmail.setContentText("Harga jual belum terisi");
                                        alertEmail.show();
                                    }
                                }
                                else{
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setHeaderText(null);
                                        alert.setContentText("Harga pokok telepon tidak valid");
                                        alert.show();
                                    }
                            }
                            else{
                                Alert alertNo = new Alert(Alert.AlertType.WARNING);
                                alertNo.setHeaderText(null);
                                alertNo.setContentText("Stock belum terisi");
                                alertNo.show();
                            }
                        }
                        else{
                            Alert alertAlamat = new Alert(Alert.AlertType.WARNING);
                            alertAlamat.setHeaderText(null);
                            alertAlamat.setContentText("Merk belum terisi");
                            alertAlamat.show();
                        }
                    }
                    else{
                        Alert alertNama = new Alert(Alert.AlertType.WARNING);
                        alertNama.setHeaderText(null);
                        alertNama.setContentText("Nama belum terisi");
                        alertNama.show();
                    }
                } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                }finally{
                try {
                if(statement!=null)
                   statement.close();
                if(con!=null)
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    }
                 }
    }
    
    @FXML
    public void additem_cancel_btn(ActionEvent event){
        try{
                Stage stage = (Stage) additem_cancel.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/item.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ITEM");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
