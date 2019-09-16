/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author JOELLA
 */
public class HomeController {
    
    @FXML
    private JFXButton supplier_btn;

    @FXML
    private JFXButton customer_btn;

    @FXML
    private JFXButton item_btn;

    @FXML
    private JFXButton pembelian_btn;

    @FXML
    private JFXButton penjualan_btn;

    @FXML
    private JFXButton suratjalan_btn;

    
    @FXML
    public void initialize(Admin dataadmin) throws SQLException {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
        statement = con.createStatement();
        String jabatan = "";
        ResultSet rs = statement.executeQuery("SELECT * FROM admin WHERE username='"+dataadmin.getUsername()+"'");
        while(rs.next()){
            jabatan=rs.getString("jabatan");
        }
        rs.close();
        if (jabatan=="pegawai"){
            laporan_btn.setDisable(true);
        }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void supplier_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) supplier_btn.getScene().getWindow();
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
    public void customer_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) customer_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/customer.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("CUSTOMER");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void item_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) item_btn.getScene().getWindow();
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
    public void pembelian_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) pembelian_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/pembelian.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("PEMBELIAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void penjualan_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) penjualan_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/penjualan.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("PENJUALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void suratjalan_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) suratjalan_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/suratjalan.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("SURAT JALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
