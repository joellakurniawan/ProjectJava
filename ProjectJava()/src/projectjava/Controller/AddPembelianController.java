/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author JOELLA
 */
public class AddPembelianController {
    
    @FXML
    private JFXButton addpembelian_cancel;

    @FXML
    private Label detailnotapembelian_id;

    @FXML
    private JFXButton adddetailpembelian_btn;

    @FXML
    private JFXTextField jumlah_textfield;

    @FXML
    private JFXTextField harga_textfield;

    @FXML
    private JFXComboBox<String> item_cb;

    @FXML
    private Label notapembelian_id;

    @FXML
    private JFXDatePicker tanggal;

    @FXML
    private TableView<addpembelian_class> addnotapembelian_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn id_item;

    @FXML
    private TableColumn nama_item;

    @FXML
    private TableColumn jumlah;

    @FXML
    private TableColumn harga_satuan;

    @FXML
    private TableColumn total;

    @FXML
    private JFXButton addpembelian_btn;

    @FXML
    private JFXComboBox<String> supplier_cb;

    @FXML
    private Label totalharga_txt;
    
    ObservableList<String> datasupplier=FXCollections.observableArrayList();
    ObservableList<String> dataitem=FXCollections.observableArrayList();
    ObservableList<addpembelian_class> datapembelian=FXCollections.observableArrayList();
    
    @FXML
    public void initialize(){
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    statement = con.createStatement();
                    datasupplier.clear();
                    dataitem.clear();
                    totalharga_txt.setText("0");
                    int masuk=0;
                    ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_nota_pembelian'");
                    while(rs.next()){
                        masuk=rs.getInt("AUTO_INCREMENT");
                    }
                    detailnotapembelian_id.setText(String.valueOf(masuk));
                    rs.close();
                    
                    rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'nota_pembelian'");
                    while(rs.next()){
                        masuk=rs.getInt("AUTO_INCREMENT");
                    }
                    notapembelian_id.setText(String.valueOf(masuk));
                    rs.close();
                    
                    rs = statement.executeQuery("SELECT * FROM supplier WHERE status_aktif=1");
                    while(rs.next()){
                        datasupplier.add(
                            new String(rs.getString("nama"))
                        );
                    }
                    supplier_cb.setItems(datasupplier);
                    rs.close();
                    
                    rs = statement.executeQuery("SELECT * FROM item WHERE status_aktif=1 AND id_lokasi=1");
                    while(rs.next()){
                        dataitem.add(
                            new String(rs.getString("nama"))
                        );
                    }
                    item_cb.setItems(dataitem);
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
    public void initialize2(){
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    addnotapembelian_table.getItems().clear();
                    statement = con.createStatement();
                    int masuk=0;
                    
                    ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_nota_pembelian'");
                    while(rs.next()){
                        masuk=rs.getInt("AUTO_INCREMENT");
                    }
                    detailnotapembelian_id.setText(String.valueOf(masuk));
                    rs.close();
                    
                    int totaltxt=0;
                    rs = statement.executeQuery("SELECT DISTINCT dnp.id, i.id, i.nama, dnp.jumlah, dnp.harga_satuan FROM detail_nota_pembelian dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapembelian="+notapembelian_id.getText().toString());
                    while(rs.next()){
                        datapembelian.addAll(new addpembelian_class(
                                rs.getInt("dnp.id"),
                                rs.getInt("i.id"),
                                rs.getString("i.nama"),
                                rs.getInt("dnp.jumlah"),
                                rs.getInt("dnp.harga_satuan"),
                                rs.getInt("dnp.jumlah")*rs.getInt("dnp.harga_satuan"))
                        );
                        totaltxt=totaltxt+(rs.getInt("dnp.jumlah")*rs.getInt("dnp.harga_satuan"));
                    }
                    totalharga_txt.setText(String.valueOf(totaltxt));
                    id.setCellValueFactory(new PropertyValueFactory<addpembelian_class, String>("id"));
                    id_item.setCellValueFactory(new PropertyValueFactory<addpembelian_class, String>("id_item"));
                    nama_item.setCellValueFactory(new PropertyValueFactory<addpembelian_class, String>("nama_item"));
                    jumlah.setCellValueFactory(new PropertyValueFactory<addpembelian_class, String>("jumlah"));
                    harga_satuan.setCellValueFactory(new PropertyValueFactory<addpembelian_class, String>("harga_satuan"));
                    total.setCellValueFactory(new PropertyValueFactory<addpembelian_class, String>("total"));
                    addnotapembelian_table.setItems(datapembelian);
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
    public void addpembelian_cancel_btn(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                statement.executeUpdate("DELETE FROM detail_nota_pembelian WHERE id_notapembelian="+notapembelian_id.getText().toString());
                Stage stage = (Stage) addpembelian_cancel.getScene().getWindow();
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
    void adddetailpembelian_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
            if (item_cb.getValue().isEmpty()==false){
                if (jumlah_textfield.getText().isEmpty()==false){
                    if(harga_textfield.getText().isEmpty()==false){
                        con = ConnectionFactory.getConnection();
                        statement = con.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT * FROM item WHERE id_lokasi=1 AND nama='" + item_cb.getValue().toString() + "'");
                        int iditem=0;
                        while(rs.next()){
                            iditem=rs.getInt("id");
                        }
                        rs.close();
                        statement.executeUpdate("INSERT INTO detail_nota_pembelian(jumlah, id_notapembelian, id_item, harga_satuan) values ("+jumlah_textfield.getText().toString()+","+notapembelian_id.getText().toString()+","+iditem+","+harga_textfield.getText().toString()+")");
                        initialize2();
                        Alert success = new Alert(Alert.AlertType.INFORMATION);
                        success.setHeaderText(null);
                        success.setContentText("Berhasil");
                        success.show();
                    }
                }
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
    void addpembelian_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
            if (supplier_cb.getValue().isEmpty()==false){
                if (tanggal.getValue().toString()!=""){
                    con = ConnectionFactory.getConnection();
                    statement = con.createStatement();
                    Statement statement2=con.createStatement();
                    Statement statement3=con.createStatement();
                    
                    ResultSet rs = statement.executeQuery("SELECT * FROM detail_nota_pembelian WHERE id_notapembelian="+notapembelian_id.getText().toString());
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
                        int hasil=(dnp+item)/(jumlahdnp+stockitem);
                        statement2.executeUpdate("UPDATE item SET harga_pokok="+hasil+" WHERE nama='"+nama_barang+"'");
                        statement2.executeUpdate("UPDATE item SET stock=stock+"+jumlahdnp+" WHERE id="+rs.getInt("id_item"));
                    }
                    rs.close();
                    
                    rs = statement.executeQuery("SELECT * FROM supplier WHERE nama='"+supplier_cb.getValue().toString()+"'");
                    int idsupplier=0;
                    while(rs.next()){
                        idsupplier=rs.getInt("id");
                    }
                    rs.close();
                    
                    statement.executeUpdate("INSERT INTO nota_pembelian(tanggal, total_harga, id_supplier, status_aktif) VALUES ('"+tanggal.getValue().toString()+"', "
                                            +totalharga_txt.getText().toString()+", "+idsupplier+", 1)");
                    
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
                                    Stage stage = (Stage) addpembelian_cancel.getScene().getWindow();
                                    stage.close();
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/pembelian.fxml"));
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
                }
            }
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
