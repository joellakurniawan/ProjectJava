/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author JOELLA
 */
public class EditPenjualanController {
        
    @FXML
    private JFXButton editpenjualan_cancel;

    @FXML
    private Label detailpenjualan_id;

    @FXML
    private JFXTextField jumlah_textfield;

    @FXML
    private JFXButton adddetailpenjualan_btn;

    @FXML
    private JFXComboBox<String> item_cb;

    @FXML
    private Label notapenjualan_id;

    @FXML
    private JFXDatePicker tgl_notapenjualan;

    @FXML
    private TableView<addpenjualan_class> editnotapenjualan_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn id_item;

    @FXML
    private TableColumn nama_barang;

    @FXML
    private TableColumn jumlah;

    @FXML
    private TableColumn harga_satuan;

    @FXML
    private TableColumn total;

    @FXML
    private JFXButton editpenjualan_btn;

    @FXML
    private Label customer_id;

    @FXML
    private Label totalharga_txt;

    @FXML
    private JFXButton deletedetailpenjualan_btn;

    ObservableList<addpenjualan_class> data=FXCollections.observableArrayList();
    ObservableList<String> dataitem=FXCollections.observableArrayList();
    int masuk = 0;
    
    @FXML
    public void initialize(notapenjualan_class np){
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            this.notapenjualan_id.setText(String.valueOf(np.getId()));
            this.customer_id.setText(np.getNama_customer());
            String date = np.getTanggal().toString();
            LocalDate ld = LocalDate.parse(date);
            this.tgl_notapenjualan.setValue(ld);
            
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_nota_penjualan'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailpenjualan_id.setText(String.valueOf(masuk));
            rs.close();
            
            int totaltxt = 0;
            rs = statement.executeQuery("SELECT DISTINCT * FROM detail_nota_penjualan dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapenjualan=" + notapenjualan_id.getText());
            while (rs.next()){
                data.addAll(new addpenjualan_class(
                        rs.getInt("dnp.id"),
                        rs.getInt("i.id"),
                        rs.getString("i.nama"),
                        rs.getInt("dnp.jumlah"),
                        rs.getInt("i.harga_jual"),
                        rs.getInt("dnp.jumlah")*rs.getInt("i.harga_jual")
                ));
                totaltxt = totaltxt + (rs.getInt("dnp.jumlah")*rs.getInt("i.harga_jual"));
            }
            totalharga_txt.setText(String.valueOf(totaltxt));
            id.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("id"));
            id_item.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("id_item"));
            nama_barang.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("nama_barang"));
            jumlah.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("jumlah"));
            harga_satuan.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("harga_satuan"));
            total.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("total"));
            editnotapenjualan_table.setItems(data);
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
            System.out.println("error disana");
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
            editnotapenjualan_table.getItems().clear();
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            int masukk = 0;
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_nota_penjualan'");
            while(rs.next()){
                masukk=rs.getInt("AUTO_INCREMENT");
            }
            detailpenjualan_id.setText(String.valueOf(masukk));
            rs.close();
            
            int totaltxt = 0;
            rs = statement.executeQuery("SELECT DISTINCT * FROM detail_nota_penjualan dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapenjualan=" + notapenjualan_id.getText());
            while (rs.next()){
                data.addAll(new addpenjualan_class(
                        rs.getInt("dnp.id"),
                        rs.getInt("i.id"),
                        rs.getString("i.nama"),
                        rs.getInt("dnp.jumlah"),
                        rs.getInt("i.harga_jual"),
                        rs.getInt("dnp.jumlah")*rs.getInt("i.harga_jual")
                ));
                totaltxt = totaltxt + (rs.getInt("dnp.jumlah")*rs.getInt("i.harga_jual"));
            }
            totalharga_txt.setText(String.valueOf(totaltxt));
            id.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("id"));
            id_item.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("id_item"));
            nama_barang.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("nama_barang"));
            jumlah.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("jumlah"));
            harga_satuan.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("harga_satuan"));
            total.setCellValueFactory(new PropertyValueFactory<addpenjualan_class, String>("total"));
            editnotapenjualan_table.setItems(data);
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
    void adddetailpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
            if (item_cb.getValue().isEmpty()==false){
                if (jumlah_textfield.getText().isEmpty()==false){
                    con = ConnectionFactory.getConnection();
                        statement = con.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT * FROM item WHERE id_lokasi=1 AND nama='" + item_cb.getValue().toString() + "'");
                        int iditem=0;
                        int hargapokok=0;
                        int hargajual=0;
                        while(rs.next()){
                            iditem=rs.getInt("id");
                            hargapokok=rs.getInt("harga_pokok");
                            hargajual=rs.getInt("harga_jual");
                        }
                        rs.close();
                        statement.executeUpdate("INSERT INTO detail_nota_penjualan(jumlah, id_notapenjualan, id_item, harga_pokok, harga_jual) values ("+jumlah_textfield.getText().toString()+","+notapenjualan_id.getText().toString()+","+iditem+","+hargapokok+","+hargajual+")");
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
    void deletedetailpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    addpenjualan_class np = (addpenjualan_class) editnotapenjualan_table.getSelectionModel().getSelectedItem();
                    int data = np.getId();
                    int iditem = np.getId_item();
                    int jumlah = np.getJumlah();
                    statement = con.createStatement();
                    statement.executeUpdate("UPDATE item SET stock=stock+" + jumlah + " WHERE id=" +iditem);
                    statement.executeUpdate("DELETE FROM detail_nota_penjualan WHERE id="+data);
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
    void editpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM detail_nota_penjualan WHERE id_notapenjualan=" + notapenjualan_id.getText());
            while (rs.next()){
                if (rs.getInt("id") >= masuk){
                    statement2.executeUpdate("UPDATE item SET stock=stock-" + rs.getInt("jumlah") + " WHERE id="+rs.getInt("id_item"));
                }
            }
            rs.close();
            statement.executeUpdate("UPDATE nota_penjualan SET tanggal='" + tgl_notapenjualan.getValue().toString() + "', total_harga=" + totalharga_txt.getText() +" WHERE id="+notapenjualan_id.getText());
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
                                    Stage stage = (Stage) editpenjualan_cancel.getScene().getWindow();
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
    public void editpenjualan_cancel_btn(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                statement.executeUpdate("DELETE FROM detail_nota_penjualan WHERE id_notapenjualan="+notapenjualan_id.getText()+ " AND id >= "+masuk);
                Stage stage = (Stage) editpenjualan_cancel.getScene().getWindow();
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
