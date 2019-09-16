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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author JOELLA
 */
public class AddSupplierController {
    @FXML
    private JFXButton add_cancel;
    
    @FXML
    private JFXButton add_add;
    
    @FXML
    private JFXTextField supplier_nama;
    
    @FXML
    private JFXTextField supplier_alamat;
    
    @FXML
    private JFXTextField supplier_notlp;
    
    @FXML
    private JFXTextField supplier_email;
    
    @FXML
    private Label supplier_id;
    
    @FXML
    public void initialize(){
                supplier_nama.clear();
                supplier_alamat.clear();
                supplier_notlp.clear();
                supplier_email.clear();
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    statement = con.createStatement();
                    int masuk=0;
                    ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'supplier'");
                    while(rs.next()){
                        masuk=rs.getInt("AUTO_INCREMENT");
                    }
                    supplier_id.setText(String.valueOf(masuk));
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
    public void add_add_btn(){
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
                                        statement.executeUpdate("INSERT INTO supplier values ('"+supplier_id.getText()+"','"
                                                +supplier_nama.getText().toString()+"','"+supplier_alamat.getText().toString()+"','"
                                                +supplier_notlp.getText().toString()+"','"
                                                +supplier_email.getText().toString()+"',1)");
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
                                                    Stage stage = (Stage) add_cancel.getScene().getWindow();
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
                                        Alert alertEmail = new Alert(AlertType.WARNING);
                                        alertEmail.setHeaderText(null);
                                        alertEmail.setContentText("Email belum terisi");
                                        alertEmail.show();
                                    }
                                }
                                else{
                                        Alert alert = new Alert(AlertType.WARNING);
                                        alert.setHeaderText(null);
                                        alert.setContentText("Nomor telepon tidak valid");
                                        alert.show();
                                    }
                            }
                            else{
                                Alert alertNo = new Alert(AlertType.WARNING);
                                alertNo.setHeaderText(null);
                                alertNo.setContentText("Nomor telepon belum terisi");
                                alertNo.show();
                            }
                        }
                        else{
                            Alert alertAlamat = new Alert(AlertType.WARNING);
                            alertAlamat.setHeaderText(null);
                            alertAlamat.setContentText("Alamat belum terisi");
                            alertAlamat.show();
                        }
                    }
                    else{
                        Alert alertNama = new Alert(AlertType.WARNING);
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
    public void add_cancel_btn(ActionEvent event){
        try{
                Stage stage = (Stage) add_cancel.getScene().getWindow();
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
