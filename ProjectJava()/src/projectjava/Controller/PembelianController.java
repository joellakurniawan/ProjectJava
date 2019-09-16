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
public class PembelianController {
    
    @FXML
    private JFXButton pembelian_home;

    @FXML
    private JFXButton pembelian_supplier;

    @FXML
    private JFXButton pembelian_customer;

    @FXML
    private JFXButton pembelian_item;

    @FXML
    private JFXButton pembelian_pembelian;

    @FXML
    private JFXButton pembelian_penjualan;

    @FXML
    private JFXButton pembelian_suratjalan;

    @FXML
    private JFXButton addpembelian_btn;

    @FXML
    private JFXButton editpembelian_btn;
    
    @FXML
    private TableView<notapembelian_class> notapembelian_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn nama_supplier;

    @FXML
    private TableColumn tanggal;

    @FXML
    private TableColumn total_harga;

    ObservableList<notapembelian_class> data = FXCollections.observableArrayList();
    
    @FXML
    private TableView<pembayaranhutang_class> pembayaranhutang_table;

    @FXML
    private TableColumn id_pembayaranhutang;

    @FXML
    private TableColumn nama_supplier_ph;

    @FXML
    private TableColumn tanggal_ph;

    @FXML
    private TableColumn total_pembayaran;

    @FXML
    private JFXButton addpembayaranhutang_btn;

    @FXML
    private JFXButton editpembayaranhutang_btn;

    @FXML
    private JFXButton deletepembayaranhutang_btn;
    
    @FXML
    private JFXButton deletepembelian_btn;

    ObservableList<pembayaranhutang_class> dataph = FXCollections.observableArrayList();
    
    @FXML
    private JFXButton addreturpembelian_btn;
    
    @FXML
    private TableView<returpembelian_class> returpembelian_table;

    @FXML
    private TableColumn id_returpembelian;

    @FXML
    private TableColumn id_notapembelian;

    @FXML
    private TableColumn nama_supplier_rp;

    @FXML
    private TableColumn tanggal_rp;

    @FXML
    private TableColumn total_harga_rp;
    
    @FXML
    private JFXButton searchreturpembelian_btn;
    
    @FXML
    private JFXButton searchpembelian_btn;
    
    @FXML
    private JFXButton searchpembayaranhutang_btn;
    
    @FXML
    private JFXTextField searchpembelian_tf;
    
    @FXML
    private JFXTextField searchreturpembelian_tf;
    
    @FXML
    private JFXTextField searchpembayaranhutang_tf;
    
    ObservableList<returpembelian_class> datarp = FXCollections.observableArrayList();
    
    @FXML
    public void initialize(){
        //batas
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
         try{      
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM nota_pembelian p JOIN supplier s ON p.id_supplier=s.id WHERE p.status_aktif=1");
                
                while(rs.next()){
                    data.addAll(new notapembelian_class(
                            rs.getInt("p.id"),
                            rs.getString("s.nama"),
                            rs.getDate("p.tanggal"),
                            rs.getInt("p.total_harga")
                    ));
                }
                id.setCellValueFactory(new PropertyValueFactory<notapembelian_class, String>("id"));
                nama_supplier.setCellValueFactory(new PropertyValueFactory<notapembelian_class, String>("nama_supplier"));
                tanggal.setCellValueFactory(new PropertyValueFactory<notapembelian_class, String>("tanggal"));
                total_harga.setCellValueFactory(new PropertyValueFactory<notapembelian_class, String>("total_harga"));
                notapembelian_table.setItems(data);                    
                rs.close();
                
                rs=statement.executeQuery("SELECT DISTINCT * FROM pembayaran_hutang p JOIN supplier s ON p.id_supplier=s.id WHERE p.status_aktif=1");
                while(rs.next()){
                    dataph.addAll(new pembayaranhutang_class(
                            rs.getInt("p.id"),
                            rs.getString("s.nama"),
                            rs.getDate("p.tanggal"),
                            rs.getInt("p.total")
                    ));
                }
                id_pembayaranhutang.setCellValueFactory(new PropertyValueFactory<pembayaranhutang_class, String>("id"));
                nama_supplier_ph.setCellValueFactory(new PropertyValueFactory<pembayaranhutang_class, String>("nama_supplier"));
                tanggal_ph.setCellValueFactory(new PropertyValueFactory<pembayaranhutang_class, String>("tanggal"));
                total_pembayaran.setCellValueFactory(new PropertyValueFactory<pembayaranhutang_class, String>("total_pembayaran"));
                pembayaranhutang_table.setItems(dataph);
                rs.close();
                
                rs = statement.executeQuery("SELECT DISTINCT * FROM retur_pembelian rp JOIN supplier c ON rp.id_supplier=c.id WHERE rp.status_aktif=1");
                while(rs.next()){
                    datarp.addAll(new returpembelian_class(
                            rs.getInt("rp.id"),
                            rs.getInt("rp.id_notapembelian"),
                            rs.getString("c.nama"),
                            rs.getDate("rp.tanggal"),
                            rs.getInt("rp.total_harga")
                    ));
                }
                id_returpembelian.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("id"));
                nama_supplier_rp.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("nama_supplier"));
                tanggal_rp.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("tanggal"));
                total_harga_rp.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("total_harga"));
                id_notapembelian.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("id_notapembelian"));
                returpembelian_table.setItems(datarp);
                rs.close();
            }catch(Exception e2){
                System.err.println(e2);
            }
    }
    
    @FXML
    public void pembelian_home_change(ActionEvent event){
        try{
                Stage stage = (Stage) pembelian_home.getScene().getWindow();
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
    public void pembelian_supplier_change(ActionEvent event){
        try{
                Stage stage = (Stage) pembelian_supplier.getScene().getWindow();
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
    public void pembelian_customer_change(ActionEvent event){
        try{
                Stage stage = (Stage) pembelian_customer.getScene().getWindow();
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
    public void pembelian_item_change(ActionEvent event){
        try{
                Stage stage = (Stage) pembelian_item.getScene().getWindow();
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
    public void pembelian_pembelian_change(ActionEvent event){
        try{
                Stage stage = (Stage) pembelian_pembelian.getScene().getWindow();
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
    public void pembelian_penjualan_change(ActionEvent event){
        try{
                Stage stage = (Stage) pembelian_supplier.getScene().getWindow();
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
    public void pembelian_suratjalan_change(ActionEvent event){
        try{
                Stage stage = (Stage) pembelian_suratjalan.getScene().getWindow();
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
    public void addpembelian_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) addpembelian_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/addpembelian.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD PEMBELIAN");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void editpembelian_btnClick(ActionEvent event){
        try{
            notapembelian_class np = (notapembelian_class) notapembelian_table.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/editpembelian.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            EditPembelianController display = fxmlLoader.getController();
            display.initialize(np);
            Stage stage = (Stage) editpembelian_btn.getScene().getWindow();
            stage.close();
            stage = new Stage();
            Scene scene = new Scene(root1);
            stage.setTitle("EDIT NOTA PEMBELIAN");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void deletepembelian_btnClick(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            notapembelian_class np = (notapembelian_class) notapembelian_table.getSelectionModel().getSelectedItem();
            Statement statement2=con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM detail_nota_pembelian WHERE id_notapembelian="+np.getId());
            while(rs.next()){
                ResultSet rs2 = statement2.executeQuery("SELECT * FROM item WHERE id="+rs.getInt("id_item"));
                String nama_barang = "";
                int hargaitem=0;
                while (rs2.next()){
                    nama_barang=rs2.getString("nama");
                    hargaitem=rs2.getInt("harga_pokok");
                }
                rs2.close();
                int jumlahdnp=rs.getInt("jumlah");
                int dnp=jumlahdnp*rs.getInt("harga_satuan");
                int stockitem=0;
                rs2 = statement2.executeQuery("SELECT * FROM item WHERE nama='"+nama_barang+"'");
                while (rs2.next()){
                    stockitem=stockitem+rs2.getInt("stock");
                }
                rs2.close();
                int item=stockitem*hargaitem;
                int hasil=(item-dnp)/(stockitem-jumlahdnp); 
                statement2.executeUpdate("UPDATE item SET harga_pokok="+hasil+" WHERE nama='"+nama_barang+"'");
                statement2.executeUpdate("UPDATE item SET stock=stock-"+jumlahdnp+" WHERE id="+rs.getInt("id_item"));
                statement2.executeUpdate("DELETE FROM detail_nota_pembelian WHERE id="+rs.getInt("id"));
            }
            rs.close();
            statement.executeUpdate("UPDATE nota_pembelian SET status_aktif=0 WHERE id="+np.getId());
            initialize();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void searchpembelian_btnClick(ActionEvent event){
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                data.clear();
        try{      
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM nota_pembelian p JOIN supplier s ON p.id_supplier=s.id WHERE p.status_aktif=1 AND "
                                                       +"(p.id LIKE '%"+ searchpembelian_tf.getText() +"%' OR s.nama LIKE '%"+ searchpembelian_tf.getText() +"%' OR p.tanggal LIKE '%"+searchpembelian_tf.getText()+"%'"
                                                       +" OR p.total_harga LIKE '%"+searchpembelian_tf.getText()+"%')");
                
                while(rs.next()){
                    data.addAll(new notapembelian_class(
                            rs.getInt("p.id"),
                            rs.getString("s.nama"),
                            rs.getDate("p.tanggal"),
                            rs.getInt("p.total_harga")
                    ));
                }
                id.setCellValueFactory(new PropertyValueFactory<notapembelian_class, String>("id"));
                nama_supplier.setCellValueFactory(new PropertyValueFactory<notapembelian_class, String>("nama_supplier"));
                tanggal.setCellValueFactory(new PropertyValueFactory<notapembelian_class, String>("tanggal"));
                total_harga.setCellValueFactory(new PropertyValueFactory<notapembelian_class, String>("total_harga"));
                notapembelian_table.setItems(data);                    
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
    
    /////////////PEMBAYARAN HUTANG//////////////////
    
    @FXML
    void addpembayaranhutang_btnClick(ActionEvent event) {
        try{
                Stage stage = (Stage) addpembelian_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/addpembayaranhutang.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD PEMBAYARAN HUTANG");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void editpembayaranhutang_btnClick(ActionEvent event) {
        try{
            pembayaranhutang_class ph = (pembayaranhutang_class) pembayaranhutang_table.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/editpembayaranhutang.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            EditPembayaranHutangController display = fxmlLoader.getController();
            display.initialize(ph);
            Stage stage = (Stage) editpembayaranhutang_btn.getScene().getWindow();
            stage.close();
            stage = new Stage();
            Scene scene = new Scene(root1);
            stage.setTitle("EDIT PEMBAYARAN HUTANG");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void deletepembayaranhutang_btnClick (ActionEvent event){
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try {
            statement = con.createStatement();
            pembayaranhutang_class ph = (pembayaranhutang_class) pembayaranhutang_table.getSelectionModel().getSelectedItem();
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM detail_pembayaran_hutang WHERE id_pembayaranhutang="+ph.getId());
            while (rs.next()){
                statement2.executeUpdate("UPDATE nota_pembelian SET terbayar=terbayar-"+rs.getInt("pembayaran")+" WHERE id="+rs.getInt("id_notapembelian"));
                statement2.executeUpdate("DELETE FROM detail_pembayaran_hutang WHERE id="+rs.getInt("id"));
            }
            rs.close();
            statement.executeUpdate("DELETE FROM pembayaran_hutang WHERE id="+ph.getId());
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
    void searchpembayaranhutang_btnClick(ActionEvent event){
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                dataph.clear();
        try{      
                statement = con.createStatement();
                ResultSet rs=statement.executeQuery("SELECT DISTINCT * FROM pembayaran_hutang p JOIN supplier s ON p.id_supplier=s.id WHERE p.status_aktif=1 AND "
                                                       +"(p.id LIKE '%"+ searchpembayaranhutang_tf.getText() +"%' OR s.nama LIKE '%"+ searchpembayaranhutang_tf.getText() +"%' OR p.tanggal LIKE '%"+searchpembayaranhutang_tf.getText()+"%'"
                                                       +" OR p.total_harga LIKE '%"+searchpembayaranhutang_tf.getText()+"%')");
                while(rs.next()){
                    dataph.addAll(new pembayaranhutang_class(
                            rs.getInt("p.id"),
                            rs.getString("s.nama"),
                            rs.getDate("p.tanggal"),
                            rs.getInt("p.total")
                    ));
                }
                id_pembayaranhutang.setCellValueFactory(new PropertyValueFactory<pembayaranhutang_class, String>("id"));
                nama_supplier_ph.setCellValueFactory(new PropertyValueFactory<pembayaranhutang_class, String>("nama_supplier"));
                tanggal_ph.setCellValueFactory(new PropertyValueFactory<pembayaranhutang_class, String>("tanggal"));
                total_pembayaran.setCellValueFactory(new PropertyValueFactory<pembayaranhutang_class, String>("total_pembayaran"));
                pembayaranhutang_table.setItems(dataph);
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
    
    //////////////////RETUR PEMBELIAN//////////////////////////
    @FXML
    void addreturpembelian_btnClick(ActionEvent event){
        try{
                Stage stage = (Stage) addreturpembelian_btn.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/addreturpembelian.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setTitle("ADD RETUR HUTANG");
                stage.setScene(scene);
                stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void editreturpembelian_btnClick(ActionEvent event){
        try{
            returpembelian_class rp = (returpembelian_class) returpembelian_table.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/editreturpembelian.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            EditReturPembelianController display = fxmlLoader.getController();
            display.initialize(rp);
            Stage stage = (Stage) editpembayaranhutang_btn.getScene().getWindow();
            stage.close();
            stage = new Stage();
            Scene scene = new Scene(root1);
            stage.setTitle("EDIT PEMBAYARAN HUTANG");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void deletereturpembelian_btnClick(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            returpembelian_class rp = (returpembelian_class) returpembelian_table.getSelectionModel().getSelectedItem();
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM retur_pembelian WHERE id="+rp.getId());
            while (rs.next()){
                if (rs.getBoolean("potong_nota")){
                    statement2.executeUpdate("UPDATE nota_pembelian SET retur=retur-"+rs.getInt("total_harga")+" WHERE id="+rs.getInt("id_notapembelian"));
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
    void searchreturpembelian_btnClick(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        datarp.clear();
        try {
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM retur_pembelian rp JOIN supplier c ON rp.id_supplier=c.id WHERE rp.status_aktif=1 AND "
                                                       +"(rp.id LIKE '%"+ searchreturpembelian_tf.getText() +"%' OR rp.id_notapembelian LIKE '%"+searchreturpembelian_tf.getText()+"%' OR c.nama LIKE '%"+ searchreturpembelian_tf.getText() +"%' OR rp.tanggal LIKE '%"+searchreturpembelian_tf.getText()+"%'"
                                                       +" OR rp.total_harga LIKE '%"+searchreturpembelian_tf.getText()+"%')");
                while(rs.next()){
                    datarp.addAll(new returpembelian_class(
                            rs.getInt("rp.id"),
                            rs.getInt("rp.id_notapembelian"),
                            rs.getString("c.nama"),
                            rs.getDate("rp.tanggal"),
                            rs.getInt("rp.total_harga")
                    ));
                }
                id_returpembelian.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("id"));
                nama_supplier_rp.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("nama_supplier"));
                tanggal_rp.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("tanggal"));
                total_harga_rp.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("total_harga"));
                id_notapembelian.setCellValueFactory(new PropertyValueFactory<returpembelian_class, String>("id_notapembelian"));
                returpembelian_table.setItems(datarp);
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
