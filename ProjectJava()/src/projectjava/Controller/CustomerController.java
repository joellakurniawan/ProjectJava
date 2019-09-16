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
public class CustomerController {
    
     @FXML
    private JFXButton customer_home;

    @FXML
    private JFXButton customer_supplier;

    @FXML
    private JFXButton customer_customer;

    @FXML
    private JFXButton customer_item;

    @FXML
    private JFXButton customer_pembelian;

    @FXML
    private JFXButton customer_penjualan;

    @FXML
    private JFXButton customer_suratjalan;

    @FXML
    private JFXButton addcustomer_btn;

    @FXML
    private JFXButton editcustomer_btn;
    
    @FXML
    private JFXTextField search_txt;

    @FXML
    private JFXButton searchcustomer_btn;
    
    @FXML
    private TableColumn ID;

    @FXML
    private TableColumn nama;

    @FXML
    private TableColumn alamat;

    @FXML
    private TableColumn notlp;

    @FXML
    private TableColumn email;
    
    @FXML
    private TableView customer_table;
    
    @FXML
    private JFXButton deletecustomer_btn;

    ObservableList<customer_class> data = FXCollections.observableArrayList();
    
    @FXML
    public void initialize(){
        //batas
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
         try{                
             
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE status_aktif=1");
                
                while(rs.next()){
                    data.addAll(new customer_class(
                            rs.getString("id"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getString("notlp"),
                            rs.getString("email")
                    ));
                }
                ID.setCellValueFactory(new PropertyValueFactory<customer_class, String>("ID"));
                nama.setCellValueFactory(new PropertyValueFactory<customer_class, String>("nama"));
                alamat.setCellValueFactory(new PropertyValueFactory<customer_class, String>("alamat"));
                notlp.setCellValueFactory(new PropertyValueFactory<customer_class, String>("notlp"));
                email.setCellValueFactory(new PropertyValueFactory<customer_class, String>("email"));
                customer_table.setItems(data);                    
                rs.close();
            }catch(Exception e2){
                System.err.println(e2);
            }
    }
    
    @FXML
    public void customer_home_change(ActionEvent event){
        try{
                Stage stage = (Stage) customer_home.getScene().getWindow();
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
    public void customer_supplier_change(ActionEvent event){
        try{
                Stage stage = (Stage) customer_supplier.getScene().getWindow();
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
    public void customer_customer_change(ActionEvent event){
        try{
                Stage stage = (Stage) customer_customer.getScene().getWindow();
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
    public void customer_item_change(ActionEvent event){
        try{
                Stage stage = (Stage) customer_item.getScene().getWindow();
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
    public void customer_pembelian_change(ActionEvent event){
        try{
                Stage stage = (Stage) customer_pembelian.getScene().getWindow();
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
    public void customer_penjualan_change(ActionEvent event){
        try{
                Stage stage = (Stage) customer_penjualan.getScene().getWindow();
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
    public void customer_suratjalan_change(ActionEvent event){
        try{
                Stage stage = (Stage) customer_suratjalan.getScene().getWindow();
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
    public void addcustomer_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) addcustomer_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/addcustomer.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD CUSTOMER");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void editcustomer_btnClick(ActionEvent event){
        try{
                customer_class cust = (customer_class) customer_table.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/editcustomer.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                EditCustomerController display = fxmlLoader.getController();
                display.setText(cust);
                Stage stage = (Stage) editcustomer_btn.getScene().getWindow();
                stage.close();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("EDIT CUSTOMER");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void deletecustomer_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    customer_class cust = (customer_class) customer_table.getSelectionModel().getSelectedItem();
                    String data = cust.getID();
                    statement = con.createStatement();
                    statement.executeUpdate("UPDATE customer SET status_aktif=0 WHERE id='"+data+"'");
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
    void searchcustomer_btnClick(ActionEvent event) {
        String cari = search_txt.getText();
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
         try{                
             
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE status_aktif = 1 AND (id LIKE '%" + cari + "%' OR nama LIKE '%" + cari + "%' "
                        + "OR alamat LIKE '%" + cari + "%' OR notlp LIKE '%" + cari + "%' OR email LIKE '%" + cari + "%')");
                
                while(rs.next()){
                    data.addAll(new customer_class(
                            rs.getString("id"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getString("notlp"),
                            rs.getString("email")
                    ));
                }
                ID.setCellValueFactory(new PropertyValueFactory<customer_class, String>("ID"));
                nama.setCellValueFactory(new PropertyValueFactory<customer_class, String>("nama"));
                alamat.setCellValueFactory(new PropertyValueFactory<customer_class, String>("alamat"));
                notlp.setCellValueFactory(new PropertyValueFactory<customer_class, String>("notlp"));
                email.setCellValueFactory(new PropertyValueFactory<customer_class, String>("email"));
                customer_table.setItems(data);                    
                rs.close();
            }catch(Exception e2){
                System.err.println(e2);
            }
    }
}
