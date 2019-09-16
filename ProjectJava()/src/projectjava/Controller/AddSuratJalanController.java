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
import javafx.scene.control.Alert.AlertType;
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
public class AddSuratJalanController {
    
    @FXML
    private Label detailsuratjalan_id;

    @FXML
    private Label suratjalan_id;
    
    @FXML
    private JFXButton addsuratjalan_cancel;
    
    @FXML
    private JFXButton addsuratjalan_add;
    
    @FXML
    private JFXComboBox<String> lokasiawal_cb;

    @FXML
    private JFXComboBox<String> lokasitujuan_cb;
    
    @FXML
    private JFXComboBox<String> item_cb;
    
    @FXML
    private JFXButton adddetailsuratjalan_btn;
    
    @FXML
    private JFXTextField jumlah_textfield;
    
    @FXML
    private JFXDatePicker tglsuratjalan;
    
    @FXML
    private TableView addsuratjalan_table;
    
    @FXML
    private TableColumn id_detailsuratjalan;
    
    @FXML
    private TableColumn nama_barang;
    
    @FXML
    private TableColumn jumlah;
    
    @FXML
    private TableColumn harga_satuan;
    
    
    ObservableList<String> datalokasi=FXCollections.observableArrayList();
    ObservableList<String> dataitem=FXCollections.observableArrayList();
    ObservableList<addsuratjalan_class> datasuratjalan=FXCollections.observableArrayList();
    ObservableList<String> datanamaitem=FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    statement = con.createStatement();
                    datalokasi.clear();
                    dataitem.clear();
                    datasuratjalan.clear();
                    int masuk=0;
                    ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'surat_jalan'");
                    while(rs.next()){
                        masuk=rs.getInt("AUTO_INCREMENT");
                    }
                    suratjalan_id.setText(String.valueOf(masuk));
                    rs.close();
                    rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_surat_jalan'");
                    while(rs.next()){
                        masuk=rs.getInt("AUTO_INCREMENT");
                    }
                    detailsuratjalan_id.setText(String.valueOf(masuk));
                    rs.close();
                    
                    rs = statement.executeQuery("SELECT * FROM lokasi");
                    while(rs.next()){
                        datalokasi.add(
                            new String(rs.getString("nama"))
                        );
                    }
                    lokasiawal_cb.setItems(datalokasi);
                    lokasitujuan_cb.setItems(datalokasi);
                    rs.close();
                    
                    rs = statement.executeQuery("SELECT DISTINCT nama FROM item WHERE status_aktif=1");
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
                    addsuratjalan_table.getItems().clear();
                    statement = con.createStatement();
                    int masuk=0;
                    ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_surat_jalan'");
                    while(rs.next()){
                        masuk=rs.getInt("AUTO_INCREMENT");
                    }
                    detailsuratjalan_id.setText(String.valueOf(masuk));
                    rs.close();
                    
                    rs = statement.executeQuery("SELECT DISTINCT dsj.id, i.nama, dsj.jumlah, i.harga_jual FROM detail_surat_jalan dsj JOIN item i ON dsj.nama_item=i.nama WHERE dsj.id_suratjalan="+suratjalan_id.getText().toString());
                    while(rs.next()){
                        datasuratjalan.addAll(new addsuratjalan_class(
                                rs.getInt("dsj.id"),
                                rs.getString("i.nama"),
                                rs.getInt("dsj.jumlah"),
                                rs.getInt("i.harga_jual"))
                        );
                    }
                    id_detailsuratjalan.setCellValueFactory(new PropertyValueFactory<addsuratjalan_class, String>("id"));;
                    nama_barang.setCellValueFactory(new PropertyValueFactory<addsuratjalan_class, String>("nama_barang"));
                    jumlah.setCellValueFactory(new PropertyValueFactory<addsuratjalan_class, String>("jumlah"));
                    harga_satuan.setCellValueFactory(new PropertyValueFactory<addsuratjalan_class, String>("harga_satuan"));
                    addsuratjalan_table.setItems(datasuratjalan);
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
    public void addsuratjalan_cancel_btn(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                statement.executeUpdate("DELETE FROM detail_surat_jalan WHERE id_suratjalan="+suratjalan_id.getText().toString());
                Stage stage = (Stage) addsuratjalan_cancel.getScene().getWindow();
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
    void adddetailsuratjalan_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
            if (item_cb.getValue().isEmpty()==false){
                if (jumlah_textfield.getText().isEmpty()==false){
                    con = ConnectionFactory.getConnection();
                    statement = con.createStatement();
                    statement.executeUpdate("INSERT INTO detail_surat_jalan(jumlah, id_suratjalan, nama_item) values ("+jumlah_textfield.getText().toString()+","+suratjalan_id.getText().toString()+",'"+item_cb.getValue().toString()+"')");
                    initialize2();
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setHeaderText(null);
                    success.setContentText("Berhasil");
                    success.show();
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
    void addsuratjalan_add_btn(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
            if (lokasiawal_cb.getValue().isEmpty()==false){
                if (lokasitujuan_cb.getValue().isEmpty()==false){
                    if (tglsuratjalan.getValue().toString()!=""){
                        con = ConnectionFactory.getConnection();
                        statement = con.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT * FROM lokasi WHERE nama='"+lokasiawal_cb.getValue().toString()+"'");
                        int idlokasiawal=0;
                        while(rs.next()){
                            idlokasiawal=rs.getInt("id");
                        }
                        System.out.println(idlokasiawal);
                        rs.close();
                        rs = statement.executeQuery("SELECT * FROM lokasi WHERE nama='"+lokasitujuan_cb.getValue().toString()+"'");
                        int idlokasitujuan=0;
                        while (rs.next()){
                            idlokasitujuan=rs.getInt("id");
                        }
                        System.out.println(idlokasitujuan);
                        rs.close();
                        
                        Statement statementcek=null;
                        statementcek=con.createStatement();
                        Statement statement2=null;
                        statement2=con.createStatement();
                        Statement statement3=null;
                        statement3=con.createStatement();
                        rs = statement.executeQuery("SELECT * FROM detail_surat_jalan WHERE id_suratjalan="+suratjalan_id.getText().toString()) ;
                        while (rs.next()){
                            String namaitem=rs.getString("nama_item");
                            System.out.println(namaitem);
                            System.out.println(idlokasitujuan);
                            ResultSet rs2 = statementcek.executeQuery("SELECT * FROM item WHERE nama='"+namaitem+"' AND id_lokasi="+idlokasitujuan);
                            if (rs2.next()==false){
                                System.out.println("masuk if");
                                ResultSet rs3 = statement3.executeQuery("SELECT * FROM item WHERE nama='"+namaitem+"' AND id_lokasi="+idlokasiawal);
                                while(rs3.next()){
                                    statement2.executeUpdate("INSERT INTO item(nama, merk, stock, harga_pokok, harga_jual, id_lokasi, status_aktif) VALUES ('"
                                                             + rs3.getString("nama")+"', '" + rs3.getString("merk") + "'," + rs.getInt("jumlah") + ","
                                                             + rs3.getInt("harga_pokok") +","+ rs3.getInt("harga_jual") +","+ idlokasitujuan+",1)");
                                    statement2.executeUpdate("UPDATE item SET stock=stock-"+rs.getInt("jumlah")+" WHERE nama='"+namaitem+"' AND id_lokasi="+idlokasiawal);
                                }
                                rs3.close();
                            }
                            else{
                                System.out.println("masuk else");
                                do{
                                statement2.executeUpdate("UPDATE item SET stock=stock-"+rs.getInt("jumlah")+" WHERE nama='"+namaitem+"' AND id_lokasi="+idlokasiawal);
                                statement2.executeUpdate("UPDATE item SET stock=stock+"+rs.getInt("jumlah")+" WHERE nama='"+namaitem+"' AND id_lokasi="+idlokasitujuan);
                                } while(rs2.next());
                            }
                            rs2.close();
                        }
                        rs.close();
                        
                        statement.executeUpdate("INSERT INTO surat_jalan(tanggal, lokasi_awal, lokasi_tujuan, status_aktif) values ('"+tglsuratjalan.getValue().toString()+"',"+idlokasiawal+","+idlokasitujuan+", 1)");
                        
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("");
                        alert.setHeaderText("Berhasil!");
                        
                        ButtonType buttonTypeOK = new ButtonType("OK");
                        alert.getButtonTypes().setAll(buttonTypeOK);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTypeOK){
                            try{
                                    con = ConnectionFactory.getConnection();
                                    statement = con.createStatement();
                                    Stage stage = (Stage) addsuratjalan_cancel.getScene().getWindow();
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
}
