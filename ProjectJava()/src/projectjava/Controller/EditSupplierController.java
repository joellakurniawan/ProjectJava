/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
public class EditSupplierController {
    
     @FXML
    private JFXButton edit_cancel;

    @FXML
    private JFXTextField supplier_nama;

    @FXML
    private JFXTextField supplier_alamat;

    @FXML
    private JFXTextField supplier_notlp;

    @FXML
    private JFXTextField supplier_email;

    @FXML
    private JFXButton edit_save;
    
    @FXML
    private Label supplier_id;
    
    @FXML
    public void initialize(){
        
    }
    
    public void setText(supplier_class sup){
        this.supplier_id.setText(sup.getID());
        this.supplier_nama.setText(sup.getNama());
        this.supplier_alamat.setText(sup.getAlamat());
        this.supplier_notlp.setText(sup.getNotlp());
        this.supplier_email.setText(sup.getEmail());
    }
    
    @FXML
    public void edit_cancel_btn(ActionEvent event){
        try{
                Stage stage = (Stage) edit_cancel.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/supplier.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("SUPPLIER");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void edit_save_btn(ActionEvent event) throws IOException {
        Connection con = null;
        Statement statement = null;
        try {
            if(supplier_nama.getText().isEmpty() == false){
                        if(supplier_alamat.getText().isEmpty() == false){
                            if(supplier_notlp.getText().isEmpty() == false){
                                if(supplier_notlp.getLength() <= 12){
                                    if(supplier_email.getText().isEmpty() == false){
                                        con = ConnectionFactory.getConnection();
                                        statement = con.createStatement();
                                        statement.executeUpdate("UPDATE supplier "
                                                + "SET nama='" + this.supplier_nama.getText()
                                                + "', alamat='" + this.supplier_alamat.getText()
                                                + "', notlp='" + this.supplier_notlp.getText()
                                                + "', email='" + this.supplier_email.getText()
                                                + "' WHERE id='"+this.supplier_id.getText()+"'");    
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
                                                    Stage stage = (Stage) edit_save.getScene().getWindow();
                                                    stage.close();
                                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/supplier.fxml"));
                                                    Parent root1 = (Parent) fxmlLoader.load();
                                                    stage = new Stage();
                                                    Scene scene = new Scene(root1);
                                                    stage.setTitle("SUPPLIER");
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
                                        alertEmail.setContentText("Email belum terisi");
                                        alertEmail.show();
                                    }
                                }
                                else{
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setHeaderText(null);
                                        alert.setContentText("Nomor telepon tidak valid");
                                        alert.show();
                                    }
                            }
                            else{
                                Alert alertNo = new Alert(Alert.AlertType.WARNING);
                                alertNo.setHeaderText(null);
                                alertNo.setContentText("Nomor telepon belum terisi");
                                alertNo.show();
                            }
                        }
                        else{
                            Alert alertAlamat = new Alert(Alert.AlertType.WARNING);
                            alertAlamat.setHeaderText(null);
                            alertAlamat.setContentText("Alamat belum terisi");
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
