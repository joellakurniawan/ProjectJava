/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author JOELLA
 */
public class SupplierController {
    
    @FXML
    private JFXButton supplier_home;

    @FXML
    private JFXButton supplier_supplier;

    @FXML
    private JFXButton supplier_customer;

    @FXML
    private JFXButton supplier_item;

    @FXML
    private JFXButton supplier_pembelian;

    @FXML
    private JFXButton supplier_penjualan;

    @FXML
    private JFXButton supplier_suratjalan;

    @FXML
    private JFXButton searchsupplier_btn;

    @FXML
    private JFXButton addsupplier_btn;

    @FXML
    private JFXButton editsupplier_btn;

    @FXML
    private JFXButton deletesupplier_btn;
    
    @FXML
    private JFXTextField search_txt;
    
    @FXML
    private TableColumn nama;
    
    @FXML
    private TableColumn ID;

    @FXML
    private TableColumn alamat;

    @FXML
    private TableColumn notlp;

    @FXML
    private TableColumn email;
    
    @FXML
    private TableView<supplier_class> supplier_table;
    
    @FXML
    private JFXComboBox<String> urut_cb;
    
    ObservableList<supplier_class> data = FXCollections.observableArrayList();
    ObservableList<String> combobox_data;
    
    @FXML
    public void initialize(){
        //batas
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
         try{                
             
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM supplier WHERE status_aktif=1");
                
                while(rs.next()){
                    data.addAll(new supplier_class(
                            rs.getString("id"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getString("notlp"),
                            rs.getString("email")
                    ));
                }
                ID.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("ID"));
                nama.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("nama"));
                alamat.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("alamat"));
                notlp.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("notlp"));
                email.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("email"));
                supplier_table.setItems(data);                    
                rs.close();
                
                combobox_data = FXCollections.observableArrayList(
                        new String("ID"),
                        new String("Nama")
                );
                urut_cb.setItems(combobox_data);

                
            }catch(Exception e2){
                System.err.println(e2);
            }
    }
    
    @FXML
    public void supplier_home_change(ActionEvent event){
        try{
                Stage stage = (Stage) supplier_home.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/home.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("HOME");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    @FXML
    public void supplier_supplier_change(ActionEvent event){
        try{
                Stage stage = (Stage) supplier_supplier.getScene().getWindow();
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
    public void supplier_customer_change(ActionEvent event){
        try{
                Stage stage = (Stage) supplier_customer.getScene().getWindow();
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
    public void supplier_item_change(ActionEvent event){
        try{
                Stage stage = (Stage) supplier_item.getScene().getWindow();
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
    public void supplier_pembelian_change(ActionEvent event){
        try{
                Stage stage = (Stage) supplier_pembelian.getScene().getWindow();
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
    public void supplier_penjualan_change(ActionEvent event){
        try{
                Stage stage = (Stage) supplier_penjualan.getScene().getWindow();
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
    public void supplier_suratjalan_change(ActionEvent event){
        try{
                Stage stage = (Stage) supplier_suratjalan.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/suratjalan.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("SURATJALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void addsupplier_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) addsupplier_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/addsupplier.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD SUPPLIER");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void editsupplier_btnClick(ActionEvent event){
        try{
                supplier_class sup = supplier_table.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/editsupplier.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                EditSupplierController display = fxmlLoader.getController();
                display.setText(sup);
                Stage stage = (Stage) editsupplier_btn.getScene().getWindow();
                stage.close();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("EDIT SUPPLIER");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void deletesupplier_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    supplier_class sup = supplier_table.getSelectionModel().getSelectedItem();
                    String data = sup.getID();
                    statement = con.createStatement();
                    statement.executeUpdate("UPDATE supplier SET status_aktif=0 WHERE id='"+data+"'");
                    initialize();
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
    void searchsupplier_btnClick(ActionEvent event) {
        String cari=search_txt.getText();
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        data.clear();
        try{                     
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM supplier WHERE status_aktif = 1 AND (id LIKE '%" + cari + "%' OR nama LIKE '%" + cari + "%' "
                        + "OR alamat LIKE '%" + cari + "%' OR notlp LIKE '%" + cari + "%' OR email LIKE '%" + cari + "%')");
                
                while(rs.next()){
                    data.addAll(new supplier_class(
                            rs.getString("id"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getString("notlp"),
                            rs.getString("email")
                    ));
                }
                ID.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("ID"));
                nama.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("nama"));
                alamat.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("alamat"));
                notlp.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("notlp"));
                email.setCellValueFactory(new PropertyValueFactory<supplier_class, String>("email"));
                supplier_table.setItems(data);                    
                rs.close();
            }catch(Exception e2){
                System.err.println(e2);
            }
    }
}
