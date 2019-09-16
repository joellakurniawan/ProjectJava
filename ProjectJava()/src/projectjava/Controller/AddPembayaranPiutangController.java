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
public class AddPembayaranPiutangController {
    @FXML
    private Label detailpembayaranpiutang_id;

    @FXML
    private JFXButton adddetailppiutang_btn;

    @FXML
    private JFXTextField pembayaran_textfield;

    @FXML
    private JFXComboBox<String> customer_cb;

    @FXML
    private JFXComboBox<String> notapenjualan_cb;

    @FXML
    private Label saldopiutang_txt;

    @FXML
    private JFXButton setsupplier_btn;

    @FXML
    private Label pembayaranpiutang_id;

    @FXML
    private JFXDatePicker tanggal;

    @FXML
    private TableView<addpembayaranpiutang_class> addpembayaranpiutang_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn id_notapenjualan;

    @FXML
    private TableColumn saldo_piutang;

    @FXML
    private TableColumn pembayaran;

    @FXML
    private JFXButton addpembayaranpiutang_btn;

    @FXML
    private JFXButton addpembayaranpiutang_cancel;

    @FXML
    private Label totalpembayaran_txt;

    @FXML
    private Label customer_id;
    
    ObservableList<String> datacustomer=FXCollections.observableArrayList();
    ObservableList<String> datanotapenjualan=FXCollections.observableArrayList();
    ObservableList<addpembayaranpiutang_class> data=FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            saldopiutang_txt.setText("0");
            totalpembayaran_txt.setText("0");
            
            int masuk=0;
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_pembayaran_piutang'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailpembayaranpiutang_id.setText(String.valueOf(masuk));
            rs.close();
            
            rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'pembayaran_piutang'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            pembayaranpiutang_id.setText(String.valueOf(masuk));
            rs.close();
            
            rs = statement.executeQuery("SELECT * FROM customer WHERE status_aktif=1");
            while(rs.next()){
                datacustomer.add(
                    new String(rs.getString("nama"))
                );
            }
            customer_cb.setItems(datacustomer);
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
    public void initialize2(){
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            addpembayaranpiutang_table.getItems().clear();
            statement = con.createStatement();
            
            int masuk=0;
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_pembayaran_piutang'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailpembayaranpiutang_id.setText(String.valueOf(masuk));
            rs.close();
            
            int totaltxt=0;
            rs = statement.executeQuery("SELECT DISTINCT * FROM detail_pembayaran_piutang dpp JOIN nota_penjualan np ON np.id=dpp.id_notapenjualan WHERE dpp.id_pembayaranpiutang="+pembayaranpiutang_id.getText());
            while (rs.next()){
                data.addAll(new addpembayaranpiutang_class(
                        rs.getInt("dpp.id"),
                        rs.getInt("np.id"),
                        rs.getInt("np.total_harga")-rs.getInt("np.terbayar")-rs.getInt("np.retur"),
                        rs.getInt("dpp.pembayaran")
                ));
                totaltxt=totaltxt+rs.getInt("dpp.pembayaran");
            }
            totalpembayaran_txt.setText(String.valueOf(totaltxt));
            id.setCellValueFactory(new PropertyValueFactory<addpembayaranpiutang_class, String>("id"));
            id_notapenjualan.setCellValueFactory(new PropertyValueFactory<addpembayaranpiutang_class, String>("id_notapenjualan"));
            saldo_piutang.setCellValueFactory(new PropertyValueFactory<addpembayaranpiutang_class, String>("saldo_piutang"));
            pembayaran.setCellValueFactory(new PropertyValueFactory<addpembayaranpiutang_class, String>("pembayaran"));
            addpembayaranpiutang_table.setItems(data);
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
    public void adddetailppiutang_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement=null;
        con = ConnectionFactory.getConnection();
        if (customer_cb.getValue().isEmpty()==false){
            if (notapenjualan_cb.getValue().isEmpty()==false){
                if (pembayaran_textfield.getText()!=""){
                    try{
                        statement=con.createStatement();
                        statement.executeUpdate("INSERT INTO detail_pembayaran_piutang(id_notapenjualan, pembayaran, id_pembayaranpiutang) VALUES ("+notapenjualan_cb.getValue().toString()+", "+pembayaran_textfield.getText()+", "+pembayaranpiutang_id.getText()+")");
                        initialize2();
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
        }
    }

    @FXML
    public void addpembayaranpiutang_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        if (tanggal.getValue().toString()!=""){
            try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                
                Statement statement2=con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM detail_pembayaran_piutang WHERE id_pembayaranpiutang="+pembayaranpiutang_id.getText());
                while(rs.next()){
                    statement2.executeUpdate("UPDATE nota_penjualan SET terbayar=terbayar+"+rs.getInt("pembayaran")+" WHERE id="+rs.getInt("id_notapenjualan"));
                }
                rs.close();
                
                int idcustomer=0;
                rs = statement.executeQuery("SELECT * FROM customer WHERE nama='"+customer_id.getText()+"'");
                while(rs.next()){
                    idcustomer=rs.getInt("id");
                }
                rs.close();
                
                statement.executeUpdate("INSERT INTO pembayaran_piutang(id_customer, total, tanggal, status_aktif) VALUES ("+idcustomer+", "+totalpembayaran_txt.getText()+", '"+tanggal.getValue().toString()+"', 1)");
                
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
                                    Stage stage = (Stage) addpembayaranpiutang_cancel.getScene().getWindow();
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

    @FXML
    public void addpembayaranpiutang_cancel_btn(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                statement.executeUpdate("DELETE FROM detail_pembayaran_piutang WHERE id_pembayaranpiutang="+pembayaranpiutang_id.getText().toString());
                Stage stage = (Stage) addpembayaranpiutang_cancel.getScene().getWindow();
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
    public void change(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM nota_penjualan WHERE id="+notapenjualan_cb.getValue().toString());
            int saldo = 0;
            while (rs.next()){
                saldo=rs.getInt("total_harga")-rs.getInt("terbayar")-rs.getInt("retur");
            }
            rs.close();
            
            saldopiutang_txt.setText(String.valueOf(saldo));
            
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
    public void setsupplier_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            customer_cb.setDisable(true);
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE status_aktif=1 AND nama='"+customer_cb.getValue().toString()+"'");
            int idcustomer = 0;
            while (rs.next()){
                idcustomer=rs.getInt("id");
            }
            rs.close();
            
            rs = statement.executeQuery("SELECT * FROM nota_penjualan WHERE status_aktif=1 AND total_harga-terbayar-retur!=0 AND id_customer="+idcustomer);
            while(rs.next()){
                datanotapenjualan.add(
                    new String(rs.getString("id"))
                );
            }
            notapenjualan_cb.setItems(datanotapenjualan);
            rs.close();
            
            customer_id.setText(customer_cb.getValue().toString());
            
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
