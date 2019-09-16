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
public class EditPembayaranPiutangController {
    @FXML
    private Label detailpembayaranpiutang_id;

    @FXML
    private JFXTextField pembayaran_textfield;

    @FXML
    private JFXComboBox<String> notapenjualan_cb;

    @FXML
    private Label saldopiutang_txt;

    @FXML
    private JFXButton adddetailppiutang_btn;

    @FXML
    private Label customer_id2;

    @FXML
    private Label pembayaranpiutang_id;

    @FXML
    private JFXDatePicker tanggal;

    @FXML
    private TableView<addpembayaranpiutang_class> editpembayaranpiutang_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn id_notapenjualan;

    @FXML
    private TableColumn saldo_piutang;

    @FXML
    private TableColumn pembayaran;

    @FXML
    private Label totalpembayaran_txt;

    @FXML
    private Label customer_id;

    @FXML
    private JFXButton editpembayaranpiutang_btn;

    @FXML
    private JFXButton editpembayaranpiutang_cancel;

    @FXML
    private JFXButton deletedetailpembayaranpiutang_btn;

    ObservableList<String> datanotapenjualan = FXCollections.observableArrayList();
    ObservableList<addpembayaranpiutang_class> data = FXCollections.observableArrayList();
    
    int masuk = 0;
    @FXML
    void initialize(pembayaranpiutang_class pp){
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            this.pembayaranpiutang_id.setText(String.valueOf(pp.getId()));
            this.customer_id.setText(pp.getNama_customer());
            this.customer_id2.setText(pp.getNama_customer());
            String date = pp.getTanggal().toString();
            LocalDate ld = LocalDate.parse(date);
            this.tanggal.setValue(ld);
            
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_pembayaran_piutang'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailpembayaranpiutang_id.setText(String.valueOf(masuk));
            rs.close();
            
            rs = statement.executeQuery("SELECT * FROM customer WHERE status_aktif=1 AND nama='"+customer_id.getText().toString()+"'");
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
            editpembayaranpiutang_table.setItems(data);
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
    void initialize2(){
        Connection con = null;
        Statement statement = null;
        try {
            editpembayaranpiutang_table.getItems().clear();
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            int masukk=0;
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_pembayaran_piutang'");
            while(rs.next()){
                masukk=rs.getInt("AUTO_INCREMENT");
            }
            detailpembayaranpiutang_id.setText(String.valueOf(masukk));
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
            editpembayaranpiutang_table.setItems(data);
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
    void adddetailppiutang_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement=null;
        con = ConnectionFactory.getConnection();
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

    @FXML
    void change(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM nota_pembelian WHERE id="+notapenjualan_cb.getValue().toString());
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
    void deletedetailpembayaranpiutang_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    addpembayaranpiutang_class pp = (addpembayaranpiutang_class) editpembayaranpiutang_table.getSelectionModel().getSelectedItem();
                    int data = pp.getId();
                    statement = con.createStatement();
                    statement.executeUpdate("UPDATE nota_penjualan SET terbayar=terbayar-"+pp.getPembayaran()+" WHERE id="+pp.getId_notapenjualan());
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
    void editpembayaranpiutang_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM detail_pembayaran_piutang WHERE id_pembayaranpiutang=" + pembayaranpiutang_id.getText() + " AND id>="+ masuk);
            while (rs.next()){
                statement2.executeUpdate("UPDATE nota_penjualan SET terbayar=terbayar+"+rs.getInt("pembayaran")+" WHERE id="+rs.getInt("id_notapenjualan"));
            }
            statement.executeUpdate("UPDATE pembayaran_piutang SET total="+totalpembayaran_txt.getText()+", tanggal='"+tanggal.getValue().toString()+"' WHERE id="+pembayaranpiutang_id.getText());
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
                                    Stage stage = (Stage) editpembayaranpiutang_cancel.getScene().getWindow();
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
    void editpembayaranpiutang_cancel_btn(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                statement.executeUpdate("DELETE FROM detail_pembayaran_piutang WHERE id_pembayaranpiutang="+pembayaranpiutang_id.getText()+ " AND id >= "+masuk);
                Stage stage = (Stage) editpembayaranpiutang_cancel.getScene().getWindow();
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

}
