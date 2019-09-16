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
public class SuratJalanController {
     
    @FXML
    private JFXButton suratjalan_home;

    @FXML
    private JFXButton suratjalan_supplier;

    @FXML
    private JFXButton suratjalan_customer;

    @FXML
    private JFXButton suratjalan_item;

    @FXML
    private JFXButton suratjalan_pembelian;

    @FXML
    private JFXButton suratjalan_penjualan;

    @FXML
    private JFXButton suratjalan_suratjalan;

    @FXML
    private JFXButton addsuratjalan_btn;
    
    @FXML
    private JFXButton deletesuratjalan_btn;
    
    @FXML
    private TableView<suratjalan_class> suratjalan_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn tanggal;

    @FXML
    private TableColumn lokasi_awal;

    @FXML
    private TableColumn lokasi_tujuan;
    
    @FXML
    private JFXTextField searchsuratjalan_tf;

    @FXML
    private JFXButton searchsuratjalan_btn;
    
    ObservableList<suratjalan_class> data = FXCollections.observableArrayList();
    
    @FXML
    public void initialize(){
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
         try{
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM surat_jalan WHERE status_aktif=1");
                
                while(rs.next()){
                    data.addAll(new suratjalan_class(
                            rs.getInt("id"),
                            rs.getDate("tanggal"),
                            rs.getInt("lokasi_awal"),
                            rs.getInt("lokasi_tujuan")
                    ));
                }
                id.setCellValueFactory(new PropertyValueFactory<suratjalan_class, String>("id"));
                tanggal.setCellValueFactory(new PropertyValueFactory<suratjalan_class, String>("tanggal"));
                lokasi_awal.setCellValueFactory(new PropertyValueFactory<suratjalan_class, String>("lokasi_awal"));
                lokasi_tujuan.setCellValueFactory(new PropertyValueFactory<suratjalan_class, String>("lokasi_tujuan"));
                suratjalan_table.setItems(data);                    
                rs.close();
                }catch(Exception e2){
                System.err.println(e2);
                }
    }
    
    @FXML
    public void suratjalan_home_change(ActionEvent event){
        try{
                Stage stage = (Stage) suratjalan_home.getScene().getWindow();
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
    public void suratjalan_supplier_change(ActionEvent event){
        try{
                Stage stage = (Stage) suratjalan_supplier.getScene().getWindow();
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
    public void suratjalan_customer_change(ActionEvent event){
        try{
                Stage stage = (Stage) suratjalan_customer.getScene().getWindow();
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
    public void suratjalan_item_change(ActionEvent event){
        try{
                Stage stage = (Stage) suratjalan_item.getScene().getWindow();
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
    public void suratjalan_pembelian_change(ActionEvent event){
        try{
                Stage stage = (Stage) suratjalan_pembelian.getScene().getWindow();
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
    public void suratjalan_penjualan_change(ActionEvent event){
        try{
                Stage stage = (Stage) suratjalan_penjualan.getScene().getWindow();
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
    public void suratjalan_suratjalan_change(ActionEvent event){
        try{
                Stage stage = (Stage) suratjalan_suratjalan.getScene().getWindow();
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
    public void addsuratjalan_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) addsuratjalan_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/AddSuratJalan.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD SURATJALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void deletesuratjalan_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    suratjalan_class sj = suratjalan_table.getSelectionModel().getSelectedItem();
                    int data = sj.getId();
                    statement = con.createStatement();
                    statement.executeUpdate("UPDATE surat_jalan SET status_aktif=0 WHERE id='"+data+"'");
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
    void searchsuratjalan_btnClick(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        data.clear();
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM surat_jalan WHERE status_aktif=1 AND (id LIKE '%"+searchsuratjalan_tf.getText()+"%' OR tanggal LIKE '%"+searchsuratjalan_tf.getText()+"%')");
                
                while(rs.next()){
                    data.addAll(new suratjalan_class(
                            rs.getInt("id"),
                            rs.getDate("tanggal"),
                            rs.getInt("lokasi_awal"),
                            rs.getInt("lokasi_tujuan")
                    ));
                }
                id.setCellValueFactory(new PropertyValueFactory<suratjalan_class, String>("id"));
                tanggal.setCellValueFactory(new PropertyValueFactory<suratjalan_class, String>("tanggal"));
                lokasi_awal.setCellValueFactory(new PropertyValueFactory<suratjalan_class, String>("lokasi_awal"));
                lokasi_tujuan.setCellValueFactory(new PropertyValueFactory<suratjalan_class, String>("lokasi_tujuan"));
                suratjalan_table.setItems(data);                    
                rs.close();
        }catch (SQLException ex) {
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
