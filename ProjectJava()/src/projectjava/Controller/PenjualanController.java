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
public class PenjualanController {
    
    @FXML
    private JFXButton penjualan_home;

    @FXML
    private JFXButton penjualan_supplier;

    @FXML
    private JFXButton penjualan_customer;

    @FXML
    private JFXButton penjualan_item;

    @FXML
    private JFXButton penjualan_pembelian;

    @FXML
    private JFXButton penjualan_penjualan;

    @FXML
    private JFXButton penjualan_suratjalan;

    @FXML
    private JFXButton addpenjualan_btn;

    @FXML
    private JFXButton editpenjualan_btn;
    
    @FXML
    private TableView<notapenjualan_class> notapenjualan_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn nama_customer;

    @FXML
    private TableColumn tanggal;

    @FXML
    private TableColumn total_harga;
    
    ObservableList<notapenjualan_class> data = FXCollections.observableArrayList();
    
    @FXML
    private TableView<pembayaranpiutang_class> pembayaranpiutang_table;

    @FXML
    private TableColumn id_pembayaranpiutang;

    @FXML
    private TableColumn nama_customer_pp;

    @FXML
    private TableColumn tanggal_pp;

    @FXML
    private TableColumn total_pembayaran;

    @FXML
    private JFXButton addpembayaranpiutang_btn;

    ObservableList<pembayaranpiutang_class> datapp = FXCollections.observableArrayList();
    
    @FXML
    private TableView<returpenjualan_class> returpenjualan_table;

    @FXML
    private TableColumn id_returpenjualan;

    @FXML
    private TableColumn nama_customer_rp;

    @FXML
    private TableColumn tanggal_rp;

    @FXML
    private TableColumn total_rp;

    @FXML
    private TableColumn id_notapenjualan;
    
    @FXML
    private JFXButton addreturpenjualan_btn;
    
    @FXML
    private JFXButton deletepenjualan_btn;
    
    @FXML
    private JFXTextField searchpenjualan_tf;

    @FXML
    private JFXButton searchpenjualan_btn;

    @FXML
    private JFXTextField searchreturpenjualan_tf;

    @FXML
    private JFXButton searchreturpenjualan_btn;

    @FXML
    private JFXTextField searchpembayaranpiutang_tf;

    @FXML
    private JFXButton searchpembayaranpiutang_btn;
    
    @FXML
    private JFXButton editreturpenjualan_btn;

    @FXML
    private JFXButton deletereturpenjualan_btn;
    
    @FXML
    private JFXButton editpembayaranpiutang_btn;
    
    @FXML
    private JFXButton deletepembayaranpiutang_btn;
    

    ObservableList<returpenjualan_class> datarp = FXCollections.observableArrayList();
    
    @FXML
    public void initialize(){
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
                datarp.clear();
                datapp.clear();
         try{                
             
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM nota_penjualan p JOIN customer c ON p.id_customer=c.id WHERE p.status_aktif=1");
                
                while(rs.next()){
                    data.addAll(new notapenjualan_class(
                            rs.getInt("p.id"),
                            rs.getString("c.nama"),
                            rs.getDate("p.tanggal"),
                            rs.getInt("p.total_harga")
                    ));
                }
                id.setCellValueFactory(new PropertyValueFactory<notapenjualan_class, String>("id"));
                nama_customer.setCellValueFactory(new PropertyValueFactory<notapenjualan_class, String>("nama_customer"));
                tanggal.setCellValueFactory(new PropertyValueFactory<notapenjualan_class, String>("tanggal"));
                total_harga.setCellValueFactory(new PropertyValueFactory<notapenjualan_class, String>("total_harga"));
                notapenjualan_table.setItems(data);                    
                rs.close();
                
                rs = statement.executeQuery("SELECT DISTINCT * FROM pembayaran_piutang pp JOIN customer c ON pp.id_customer=c.id WHERE pp.status_aktif=1");
                while(rs.next()){
                    datapp.addAll(new pembayaranpiutang_class(
                            rs.getInt("pp.id"),
                            rs.getString("c.nama"),
                            rs.getDate("pp.tanggal"),
                            rs.getInt("pp.total")
                    ));
                }
                id_pembayaranpiutang.setCellValueFactory(new PropertyValueFactory<pembayaranpiutang_class, String>("id"));
                nama_customer_pp.setCellValueFactory(new PropertyValueFactory<pembayaranpiutang_class, String>("nama_customer"));
                tanggal_pp.setCellValueFactory(new PropertyValueFactory<pembayaranpiutang_class, String>("tanggal"));
                total_pembayaran.setCellValueFactory(new PropertyValueFactory<pembayaranpiutang_class, String>("total_pembayaran"));
                pembayaranpiutang_table.setItems(datapp);
                rs.close();
                
                rs = statement.executeQuery("SELECT DISTINCT * FROM retur_penjualan rp JOIN customer c ON rp.id_customer=c.id WHERE rp.status_aktif=1");
                while(rs.next()){
                    datarp.addAll(new returpenjualan_class(
                            rs.getInt("rp.id"),
                            rs.getString("c.nama"),
                            rs.getDate("rp.tanggal"),
                            rs.getInt("rp.total_harga"),
                            rs.getInt("rp.id_notapenjualan")
                    ));
                }
                id_returpenjualan.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("id"));
                nama_customer_rp.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("nama_customer"));
                tanggal_rp.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("tanggal"));
                total_rp.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("total"));
                id_notapenjualan.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("id_notapenjualan"));
                returpenjualan_table.setItems(datarp);
                rs.close();
                
            }catch(Exception e2){
                System.err.println(e2);
            }
    }
    
     @FXML
    public void penjualan_home_change(ActionEvent event){
        try{
                Stage stage = (Stage) penjualan_home.getScene().getWindow();
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
    public void penjualan_supplier_change(ActionEvent event){
        try{
                Stage stage = (Stage) penjualan_supplier.getScene().getWindow();
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
    public void penjualan_customer_change(ActionEvent event){
        try{
                Stage stage = (Stage) penjualan_customer.getScene().getWindow();
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
    public void penjualan_item_change(ActionEvent event){
        try{
                Stage stage = (Stage) penjualan_item.getScene().getWindow();
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
    public void penjualan_pembelian_change(ActionEvent event){
        try{
                Stage stage = (Stage) penjualan_pembelian.getScene().getWindow();
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
    public void penjualan_penjualan_change(ActionEvent event){
        try{
                Stage stage = (Stage) penjualan_laporan.getScene().getWindow();
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
    public void penjualan_suratjalan_change(ActionEvent event){
        try{
                Stage stage = (Stage) penjualan_suratjalan.getScene().getWindow();
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
    public void addpenjualan_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) addpenjualan_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/addpenjualan.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD PENJUALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void editpenjualan_btnClick(ActionEvent event){
        try{
                notapenjualan_class np = (notapenjualan_class) notapenjualan_table.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/editpenjualan.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                EditPenjualanController display = fxmlLoader.getController();
                display.initialize(np);
                Stage stage = (Stage) editpenjualan_btn.getScene().getWindow();
                stage.close();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("EDIT PENJUALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void deletepenjualan_btnClick(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            notapenjualan_class np = (notapenjualan_class) notapenjualan_table.getSelectionModel().getSelectedItem();
            int id = np.getId();
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM detail_nota_penjualan WHERE id_notapenjualan="+id);
            while (rs.next()){
                statement2.executeUpdate("UPDATE item SET stock=stock+" + rs.getInt("jumlah") + " WHERE id=" + rs.getInt("id_item"));
                statement2.executeUpdate("DELETE FROM detail_nota_penjualan WHERE id=" + rs.getInt("id"));
            }
            rs.close();
            statement.executeUpdate("UPDATE nota_penjualan SET status_aktif=0 WHERE id="+id);
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
    void searchpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
         try{                
             
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM nota_penjualan p JOIN customer c ON p.id_customer=c.id WHERE p.status_aktif=1 AND "
                                                    + "(p.id LIKE '%" + searchpenjualan_tf.getText() + "%' OR c.nama LIKE '%" + searchpenjualan_tf.getText() + "%' OR "
                                                    + "p.tanggal LIKE '%" + searchpenjualan_tf.getText() + "%' OR p.total_harga LIKE '%" + searchpenjualan_tf.getText() +"%')");
                
                while(rs.next()){
                    data.addAll(new notapenjualan_class(
                            rs.getInt("p.id"),
                            rs.getString("c.nama"),
                            rs.getDate("p.tanggal"),
                            rs.getInt("p.total_harga")
                    ));
                }
                id.setCellValueFactory(new PropertyValueFactory<notapenjualan_class, String>("id"));
                nama_customer.setCellValueFactory(new PropertyValueFactory<notapenjualan_class, String>("nama_customer"));
                tanggal.setCellValueFactory(new PropertyValueFactory<notapenjualan_class, String>("tanggal"));
                total_harga.setCellValueFactory(new PropertyValueFactory<notapenjualan_class, String>("total_harga"));
                notapenjualan_table.setItems(data);                    
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
    
    ////////////////////PEMBAYARAN PIUTANG//////////////////////////
    @FXML
    public void addpembayaranpiutang_btnClick(ActionEvent event) {
        try{
                Stage stage = (Stage) addpembayaranpiutang_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/addpembayaranpiutang.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD PEMBAYARAN PIUTANG");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void editpembayaranpiutang_btnClick(ActionEvent event){
        try{
                pembayaranpiutang_class pp = (pembayaranpiutang_class) pembayaranpiutang_table.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/editpembayaranpiutang.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                EditPembayaranPiutangController display = fxmlLoader.getController();
                display.initialize(pp);
                Stage stage = (Stage) editpembayaranpiutang_btn.getScene().getWindow();
                stage.close();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("EDIT RETUR PENJUALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void searchpembayaranpiutang_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                datapp.clear();
        try{      
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM pembayaran_piutang pp JOIN customer c ON pp.id_customer=c.id WHERE pp.status_aktif=1 AND "
                                                       + "(pp.id LIKE '%" + searchpembayaranpiutang_tf.getText() + "%' OR c.nama LIKE '%" + searchpembayaranpiutang_tf.getText() + "%' OR"
                                                       + " pp.tanggal LIKE '%" + searchpembayaranpiutang_tf.getText() + "%' OR pp.total LIKE '%" + searchpembayaranpiutang_tf.getText() + "%')");
                while(rs.next()){
                    datapp.addAll(new pembayaranpiutang_class(
                            rs.getInt("pp.id"),
                            rs.getString("c.nama"),
                            rs.getDate("pp.tanggal"),
                            rs.getInt("pp.total")
                    ));
                }
                id_pembayaranpiutang.setCellValueFactory(new PropertyValueFactory<pembayaranpiutang_class, String>("id"));
                nama_customer_pp.setCellValueFactory(new PropertyValueFactory<pembayaranpiutang_class, String>("nama_customer"));
                tanggal_pp.setCellValueFactory(new PropertyValueFactory<pembayaranpiutang_class, String>("tanggal"));
                total_pembayaran.setCellValueFactory(new PropertyValueFactory<pembayaranpiutang_class, String>("total_pembayaran"));
                pembayaranpiutang_table.setItems(datapp);
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
    
    @FXML
    void deletepembayaranpiutang_btnClick(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try {
            statement = con.createStatement();
            pembayaranpiutang_class pp = (pembayaranpiutang_class) pembayaranpiutang_table.getSelectionModel().getSelectedItem();
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM detail_pembayaran_piutang WHERE id_pembayaranpiutang="+pp.getId());
            while (rs.next()){
                statement2.executeUpdate("UPDATE nota_penjualan SET terbayar=terbayar-"+rs.getInt("pembayaran")+" WHERE id="+rs.getInt("id_notapenjualan"));
                statement2.executeUpdate("DELETE FROM detail_pembayaran_piutang WHERE id="+rs.getInt("id"));
            }
            rs.close();
            statement.executeUpdate("DELETE FROM pembayaran_piutang WHERE id="+pp.getId());
            initialize();
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
    
    //////////////////RETUR PENJUALAN/////////////////////////
    @FXML
    void addreturpenjualan_btnClick(ActionEvent event) {
        try{
                Stage stage = (Stage) addreturpenjualan_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/addreturpenjualan.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD RETUR PENJUALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void searchreturpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                datarp.clear();
         try{                
             
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM retur_penjualan rp JOIN customer c ON rp.id_customer=c.id WHERE rp.status_aktif=1"
                                                       + " AND (rp.id LIKE '%" + searchreturpenjualan_tf.getText() + "%' OR c.nama LIKE '%" + searchreturpenjualan_tf.getText()
                                                       + "%' OR rp.tanggal LIKE '%" + searchreturpenjualan_tf.getText() + "%' OR rp.total_harga LIKE '%" +
                                                       searchreturpenjualan_tf.getText() + "%')");
                while(rs.next()){
                    datarp.addAll(new returpenjualan_class(
                            rs.getInt("rp.id"),
                            rs.getString("c.nama"),
                            rs.getDate("rp.tanggal"),
                            rs.getInt("rp.total_harga"),
                            rs.getInt("rp.id_notapenjualan")
                    ));
                }
                id_returpenjualan.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("id"));
                nama_customer_rp.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("nama_customer"));
                tanggal_rp.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("tanggal"));
                total_rp.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("total"));
                id_notapenjualan.setCellValueFactory(new PropertyValueFactory<returpenjualan_class, String>("id_notapenjualan"));
                returpenjualan_table.setItems(datarp);
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
    
    @FXML
    void deletereturpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            returpenjualan_class rp = (returpenjualan_class) returpenjualan_table.getSelectionModel().getSelectedItem();
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM retur_penjualan WHERE id="+rp.getId());
            while (rs.next()){
                if (rs.getBoolean("potong_nota")){
                    statement2.executeUpdate("UPDATE nota_penjualan SET retur=retur-"+rs.getInt("total_harga")+" WHERE id="+rs.getInt("id_notapenjualan"));
                }
            }
            rs.close();
            statement.executeUpdate("DELETE FROM retur_penjualan WHERE id="+rp.getId());
            initialize();
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
    
    @FXML
    void editreturpenjualan_btnClick(ActionEvent event) {
        try{
                returpenjualan_class rp = (returpenjualan_class) returpenjualan_table.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/editreturpenjualan.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                EditReturPenjualanController display = fxmlLoader.getController();
                display.initialize(rp);
                Stage stage = (Stage) editreturpenjualan_btn.getScene().getWindow();
                stage.close();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("EDIT RETUR PENJUALAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
