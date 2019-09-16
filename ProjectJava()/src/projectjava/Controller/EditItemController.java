/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
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
public class EditItemController {
    
    @FXML
    private JFXButton edititem_cancel;
    
    @FXML
    private Label item_id;

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
    private JFXButton edititem_save;
     
    
    public void setText(item_class item){
        this.item_id.setText(item.getID());
        this.item_nama.setText(item.getNama());
        this.item_merk.setText(item.getMerk());
        this.item_stock.setText(String.valueOf(item.getStock()));
        this.item_hargapokok.setText(String.valueOf(item.getHarga_pokok()));
        this.item_hargajual.setText(String.valueOf(item.getHarga_jual()));
    }
    
    @FXML
    public void edititem_cancel_btn(ActionEvent event){
        try{
                Stage stage = (Stage) edititem_cancel.getScene().getWindow();
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
    
    @FXML
    void edititem_save_btn(ActionEvent event) {
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
                                        statement.executeUpdate("UPDATE item "
                                                                + "SET nama='"+item_nama.getText().toString()+"',"
                                                                + "merk='"+item_merk.getText().toString()+"',"
                                                                + "stock="+item_stock.getText().toString()+","
                                                                + "harga_pokok="+item_hargapokok.getText().toString()+","
                                                                + "harga_jual="+item_hargajual.getText().toString()
                                                                + " WHERE id="+item_id.getText().toString());
                                        
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
                                                    Stage stage = (Stage) edititem_cancel.getScene().getWindow();
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

}
