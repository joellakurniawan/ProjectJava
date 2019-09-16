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
import java.time.LocalDate;
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
public class EditPembayaranHutangController {
    @FXML
    private Label detailpembayaranhutang_id;

    @FXML
    private JFXButton adddetailpembayaranhutang_btn;

    @FXML
    private JFXTextField pembayaran_textfield;

    @FXML
    private JFXComboBox<String> notapembelian_cb;

    @FXML
    private Label saldohutang_txt;

    @FXML
    private Label supplier_id2;

    @FXML
    private Label pembayaranhutang_id;

    @FXML
    private JFXDatePicker tanggal;

    @FXML
    private TableView<addpembayaranhutang_class> editpembayaranhutang_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn id_notapembelian;

    @FXML
    private TableColumn saldo_hutang;

    @FXML
    private TableColumn pembayaran;

    @FXML
    private JFXButton editpembayaranhutang_btn;

    @FXML
    private JFXButton editpembayaranhutang_cancel;

    @FXML
    private Label totalpembayaran_txt;

    @FXML
    private Label supplier_id;

    @FXML
    private JFXButton deletedetailpembayaranhutang_btn1;

    ObservableList<String> datanp = FXCollections.observableArrayList();
    ObservableList<addpembayaranhutang_class> data = FXCollections.observableArrayList();
    
    int masuk = 0;
    @FXML
    void initialize(pembayaranhutang_class ph){
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            this.pembayaranhutang_id.setText(String.valueOf(ph.getId()));
            this.supplier_id.setText(ph.getNama_supplier());
            this.supplier_id2.setText(ph.getNama_supplier());
            String date = ph.getTanggal().toString();
            LocalDate ld = LocalDate.parse(date);
            this.tanggal.setValue(ld);
            
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_pembayaran_hutang'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailpembayaranhutang_id.setText(String.valueOf(masuk));
            rs.close();
            
            rs = statement.executeQuery("SELECT * FROM supplier WHERE status_aktif=1 AND nama='"+supplier_id.getText().toString()+"'");
            int idsupplier = 0;
            while (rs.next()){
                idsupplier=rs.getInt("id");
            }
            rs.close();
            
            rs = statement.executeQuery("SELECT * FROM nota_pembelian WHERE status_aktif=1 AND total_harga-terbayar-retur!=0 AND id_supplier="+idsupplier);
            while(rs.next()){
                datanp.add(
                    new String(rs.getString("id"))
                );
            }
            notapembelian_cb.setItems(datanp);
            rs.close();
            
            int totaltxt=0;
            rs = statement.executeQuery("SELECT DISTINCT * FROM detail_pembayaran_hutang dph JOIN nota_pembelian np ON np.id=dph.id_notapembelian WHERE dph.id_pembayaranhutang="+pembayaranhutang_id.getText());
            while (rs.next()){
                data.addAll(new addpembayaranhutang_class(
                        rs.getInt("dph.id"),
                        rs.getInt("np.id"),
                        rs.getInt("np.total_harga")-rs.getInt("np.terbayar")-rs.getInt("np.retur"),
                        rs.getInt("dph.pembayaran")
                ));
                totaltxt=totaltxt+rs.getInt("dph.pembayaran");
            }
            totalpembayaran_txt.setText(String.valueOf(totaltxt));
            id.setCellValueFactory(new PropertyValueFactory<addpembayaranhutang_class, String>("id"));
            id_notapembelian.setCellValueFactory(new PropertyValueFactory<addpembayaranhutang_class, String>("id_notapembelian"));
            saldo_hutang.setCellValueFactory(new PropertyValueFactory<addpembayaranhutang_class, String>("saldo_hutang"));
            pembayaran.setCellValueFactory(new PropertyValueFactory<addpembayaranhutang_class, String>("pembayaran"));
            editpembayaranhutang_table.setItems(data);
            
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
    void initialize2(){
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            int masukk=0;
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_pembayaran_hutang'");
            while(rs.next()){
                masukk=rs.getInt("AUTO_INCREMENT");
            }
            detailpembayaranhutang_id.setText(String.valueOf(masukk));
            rs.close();
            
            int totaltxt=0;
            rs = statement.executeQuery("SELECT DISTINCT * FROM detail_pembayaran_hutang dph JOIN nota_pembelian np ON np.id=dph.id_notapembelian WHERE dph.id_pembayaranhutang="+pembayaranhutang_id.getText());
            while (rs.next()){
                data.addAll(new addpembayaranhutang_class(
                        rs.getInt("dph.id"),
                        rs.getInt("np.id"),
                        rs.getInt("np.total_harga")-rs.getInt("np.terbayar")-rs.getInt("np.retur"),
                        rs.getInt("dph.pembayaran")
                ));
                totaltxt=totaltxt+rs.getInt("dph.pembayaran");
            }
            totalpembayaran_txt.setText(String.valueOf(totaltxt));
            id.setCellValueFactory(new PropertyValueFactory<addpembayaranhutang_class, String>("id"));
            id_notapembelian.setCellValueFactory(new PropertyValueFactory<addpembayaranhutang_class, String>("id_notapembelian"));
            saldo_hutang.setCellValueFactory(new PropertyValueFactory<addpembayaranhutang_class, String>("saldo_hutang"));
            pembayaran.setCellValueFactory(new PropertyValueFactory<addpembayaranhutang_class, String>("pembayaran"));
            editpembayaranhutang_table.setItems(data);
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
    void adddetailpembayaranhutang_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement=null;
        con = ConnectionFactory.getConnection();
            if (notapembelian_cb.getValue().isEmpty()==false){
                if (pembayaran_textfield.getText()!=""){
                    try{
                        statement=con.createStatement();
                        statement.executeUpdate("INSERT INTO detail_pembayaran_hutang(id_notapembelian, pembayaran, id_pembayaranhutang) VALUES ("+notapembelian_cb.getValue().toString()+", "+pembayaran_textfield.getText()+", "+pembayaranhutang_id.getText()+")");
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

    @FXML
    void change(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM nota_pembelian WHERE id="+notapembelian_cb.getValue().toString());
            int saldo = 0;
            while (rs.next()){
                saldo=rs.getInt("total_harga")-rs.getInt("terbayar")-rs.getInt("retur");
            }
            rs.close();
            
            saldohutang_txt.setText(String.valueOf(saldo));
            
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
    void deletedetailpembayaranhutang_btnClick(ActionEvent event) {
                Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    addpembayaranhutang_class ph = (addpembayaranhutang_class) editpembayaranhutang_table.getSelectionModel().getSelectedItem();
                    int data = ph.getId();
                    statement = con.createStatement();
                    statement.executeUpdate("UPDATE nota_pembelian SET terbayar=terbayar-"+ph.getPembayaran()+" WHERE id="+ph.getId_notapembelian());
                    statement.executeUpdate("DELETE FROM detail_pembayaran_piutang WHERE id="+data);
                    initialize2();
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
    void editpembayaranhutang_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM detail_pembayaran_hutang WHERE id_pembayaranhutang=" + pembayaranhutang_id.getText() + " AND id>="+ masuk);
            while (rs.next()){
                statement2.executeUpdate("UPDATE nota_pembelian SET terbayar=terbayar+"+rs.getInt("pembayaran")+" WHERE id="+rs.getInt("id_notapembelian"));
            }
            statement.executeUpdate("UPDATE pembayaran_hutang SET total="+totalpembayaran_txt.getText()+", tanggal='"+tanggal.getValue().toString()+"' WHERE id="+pembayaranhutang_id.getText());
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
                                    Stage stage = (Stage) editpembayaranhutang_cancel.getScene().getWindow();
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
        }  catch (SQLException ex) {
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
    void editpembayaranhutang_cancel_btn(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                statement.executeUpdate("DELETE FROM detail_pembayaran_hutang WHERE id_pembayaranhutang="+pembayaranhutang_id.getText()+ " AND id >= "+masuk);
                Stage stage = (Stage) editpembayaranhutang_cancel.getScene().getWindow();
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
}
