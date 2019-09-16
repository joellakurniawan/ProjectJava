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

/**
 *
 * @author JOELLA
 */
public class ItemController {
    
    @FXML
    private JFXButton item_home;

    @FXML
    private JFXButton item_supplier;

    @FXML
    private JFXButton item_customer;

    @FXML
    private JFXButton item_item;

    @FXML
    private JFXButton item_pembelian;

    @FXML
    private JFXButton item_penjualan;

    @FXML
    private JFXButton item_laporan;

    @FXML
    private JFXButton edititem_btn;

    @FXML
    private JFXButton additem_btn;
    
    @FXML
    private JFXButton deleteitem_btn;
    
    @FXML
    private JFXTextField search_txt;

    @FXML
    private JFXButton searchitem_btn;
    
    @FXML
    private TableColumn ID;

    @FXML
    private TableColumn nama;

    @FXML
    private TableColumn merk;

    @FXML
    private TableColumn stock;

    @FXML
    private TableColumn harga_pokok;

    @FXML
    private TableColumn harga_jual;
    
    @FXML
    private TableColumn id_lokasi;
    
    @FXML
    private TableView item_table;
    
    ObservableList<item_class> data = FXCollections.observableArrayList();
    
    @FXML
    public void initialize(){
        //batas
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
         try{                
             
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM item i JOIN lokasi l ON i.id_lokasi=l.id WHERE i.status_aktif=1");
                
                while(rs.next()){
                    data.addAll(new item_class(
                            rs.getString("i.id"),
                            rs.getString("i.nama"),
                            rs.getString("i.merk"),
                            rs.getInt("i.stock"),
                            rs.getInt("i.harga_pokok"),
                            rs.getInt("i.harga_jual"),
                            rs.getString("l.nama")
                    ));
                }
                ID.setCellValueFactory(new PropertyValueFactory<item_class, String>("ID"));
                nama.setCellValueFactory(new PropertyValueFactory<item_class, String>("nama"));
                merk.setCellValueFactory(new PropertyValueFactory<item_class, String>("merk"));
                stock.setCellValueFactory(new PropertyValueFactory<item_class, String>("stock"));
                harga_pokok.setCellValueFactory(new PropertyValueFactory<item_class, String>("harga_pokok"));
                harga_jual.setCellValueFactory(new PropertyValueFactory<item_class, String>("harga_jual"));
                id_lokasi.setCellValueFactory(new PropertyValueFactory<item_class, String>("id_lokasi"));
                item_table.setItems(data);                    
                rs.close();
            }catch(Exception e2){
                System.err.println(e2);
            }
    }
    
    @FXML
    public void item_home_change(ActionEvent event){
        try{
                Stage stage = (Stage) item_home.getScene().getWindow();
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
    public void item_supplier_change(ActionEvent event){
        try{
                Stage stage = (Stage) item_supplier.getScene().getWindow();
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
    public void item_customer_change(ActionEvent event){
        try{
                Stage stage = (Stage) item_customer.getScene().getWindow();
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
    public void item_item_change(ActionEvent event){
        try{
                Stage stage = (Stage) item_item.getScene().getWindow();
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
    public void item_pembelian_change(ActionEvent event){
        try{
                Stage stage = (Stage) item_pembelian.getScene().getWindow();
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
    public void item_penjualan_change(ActionEvent event){
        try{
                Stage stage = (Stage) item_penjualan.getScene().getWindow();
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
    public void item_suratjalan_change(ActionEvent event){
        try{
                Stage stage = (Stage) item_suratjalan.getScene().getWindow();
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
    public void additem_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) additem_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/additem.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD ITEM");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void edititem_btnClick(ActionEvent event){
        try{
                item_class item = (item_class) item_table.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/edititem.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                EditItemController display = fxmlLoader.getController();
                display.setText(item);
                Stage stage = (Stage) edititem_btn.getScene().getWindow();
                stage.close();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("EDIT ITEM");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
     @FXML
    void deleteitem_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    item_class item = (item_class) item_table.getSelectionModel().getSelectedItem();
                    String data = item.getID();
                    statement = con.createStatement();
                    statement.executeUpdate("UPDATE item SET status_aktif=0 WHERE id='"+data+"'");
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
    void searchitem_btnClick(ActionEvent event) {
        String cari = search_txt.getText();
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
         try{                
             
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM item i JOIN lokasi l ON i.id_lokasi=l.id WHERE i.status_aktif=1 AND (id LIKE '%" + cari + "%' OR nama LIKE '%" + cari + "%' "
                        + "OR merk LIKE '%" + cari + "%')");
                
                while(rs.next()){
                    data.addAll(new item_class(
                            rs.getString("i.id"),
                            rs.getString("i.nama"),
                            rs.getString("i.merk"),
                            rs.getInt("i.stock"),
                            rs.getInt("i.harga_pokok"),
                            rs.getInt("i.harga_jual"),
                            rs.getString("l.nama")
                    ));
                }
                ID.setCellValueFactory(new PropertyValueFactory<item_class, String>("ID"));
                nama.setCellValueFactory(new PropertyValueFactory<item_class, String>("nama"));
                merk.setCellValueFactory(new PropertyValueFactory<item_class, String>("merk"));
                stock.setCellValueFactory(new PropertyValueFactory<item_class, String>("stock"));
                harga_pokok.setCellValueFactory(new PropertyValueFactory<item_class, String>("harga_pokok"));
                harga_jual.setCellValueFactory(new PropertyValueFactory<item_class, String>("harga_jual"));
                id_lokasi.setCellValueFactory(new PropertyValueFactory<item_class, String>("id_lokasi"));
                item_table.setItems(data);                    
                rs.close();
            }catch(Exception e2){
                System.err.println(e2);
            }
    }
}
