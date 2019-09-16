/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 *
 * @author JOELLA
 */
public class AddReturPenjualanController {
    @FXML
    private Label detailreturpenjualan_id;

    @FXML
    private JFXTextField jumlah_textfield;

    @FXML
    private JFXComboBox<String> customer_cb;

    @FXML
    private JFXComboBox<String> notapenjualan_cb;

    @FXML
    private JFXButton setcustomer_btn;

    @FXML
    private JFXButton adddetailreturpenjualan_btn;

    @FXML
    private JFXComboBox<String> item_cb;

    @FXML
    private Label returpenjualan_id;

    @FXML
    private JFXDatePicker tanggal;

    @FXML
    private TableView<addreturpenjualan_class> addreturpenjualan_table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn id_notapenjualan;

    @FXML
    private TableColumn nama_item;

    @FXML
    private TableColumn jumlah;

    @FXML
    private TableColumn harga_satuan;

    @FXML
    private TableColumn total;

    @FXML
    private Label total_txt;

    @FXML
    private Label customer_id;

    @FXML
    private JFXButton addreturpenjualan_btn;

    @FXML
    private JFXButton addreturpenjualan_cancel;
    
    @FXML
    private Label jumlah_txt;
    
    private ToggleGroup group;
    @FXML
    private JFXRadioButton potongnota_cb;
    @FXML
    private JFXRadioButton potongtunai_cb;
    
    ObservableList<addreturpenjualan_class> datarp = FXCollections.observableArrayList();
    ObservableList<String> datacustomer = FXCollections.observableArrayList();
    ObservableList<String> datanp = FXCollections.observableArrayList();
    ObservableList<String> dataitem = FXCollections.observableArrayList();
    
    @FXML
    void initialize(){
        group = new ToggleGroup();
        potongnota_cb.setToggleGroup(group);
        potongtunai_cb.setToggleGroup(group);
        
        Connection con = null;
            Statement statement = null;
            con = ConnectionFactory.getConnection();
        try{                
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE status_aktif=1");
            while(rs.next()){
                datacustomer.add(new String(rs.getString("nama")));
            }
            customer_cb.setItems(datacustomer);
            rs.close();
            
            int masuk=0;
            rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_retur_penjualan'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailreturpenjualan_id.setText(String.valueOf(masuk));
            rs.close();
            
            rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'retur_penjualan'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            returpenjualan_id.setText(String.valueOf(masuk));
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
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            addreturpenjualan_table.getItems().clear();
            
            int masuk=0;
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_retur_penjualan'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailreturpenjualan_id.setText(String.valueOf(masuk));
            rs.close();
            
            int totaltxt=0;
            rs = statement.executeQuery("SELECT DISTINCT * FROM detail_retur_penjualan drp JOIN item i ON drp.id_item=i.id WHERE drp.id_returpenjualan="+returpenjualan_id.getText());
            while(rs.next()){
                datarp.addAll(new addreturpenjualan_class(
                        rs.getInt("drp.id"),
                        rs.getString("i.nama"),
                        rs.getInt("drp.jumlah"),
                        rs.getInt("i.harga_jual"),
                        rs.getInt("drp.jumlah")*rs.getInt("i.harga_jual")
                ));
                totaltxt=totaltxt+(rs.getInt("drp.jumlah")*rs.getInt("i.harga_jual"));
            }
            total_txt.setText(String.valueOf(totaltxt));
            id.setCellValueFactory(new PropertyValueFactory<addreturpenjualan_class, String>("id"));
            nama_item.setCellValueFactory(new PropertyValueFactory<addreturpenjualan_class, String>("nama_item"));
            jumlah.setCellValueFactory(new PropertyValueFactory<addreturpenjualan_class, String>("jumlah"));
            harga_satuan.setCellValueFactory(new PropertyValueFactory<addreturpenjualan_class, String>("harga_satuan"));
            total.setCellValueFactory(new PropertyValueFactory<addreturpenjualan_class, String>("total"));
            addreturpenjualan_table.setItems(datarp);
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
    void adddetailreturpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            if(customer_cb.getValue().isEmpty()==false){
                if(notapenjualan_cb.getValue().isEmpty()==false){
                    if(item_cb.getValue().isEmpty()==false){
                        //if(Integer.parseInt(jumlah_txt.getText()) > Integer.parseInt(jumlah_textfield.getText())){
                            notapenjualan_cb.setDisable(true);
                            statement = con.createStatement();
                            int iditem=0;
                            ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM detail_nota_penjualan dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapenjualan="+notapenjualan_cb.getValue().toString()+" AND i.nama='"+item_cb.getValue().toString()+"'");
                            while(rs.next()){
                                iditem=rs.getInt("i.id");
                            }
                            rs.close();
                            statement.executeUpdate("INSERT INTO detail_retur_penjualan(id_returpenjualan, id_item, jumlah) VALUES ("+returpenjualan_id.getText()+", "+iditem+", "+jumlah_textfield.getText()+")");
                            Alert success = new Alert(Alert.AlertType.INFORMATION);
                            success.setHeaderText(null);
                            success.setContentText("Berhasil");
                            success.show();
                            initialize2();
                        //} 
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
    void addreturpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE status_aktif=1 AND nama='"+customer_cb.getValue().toString()+"'");
                int idcustomer = 0;
                while (rs.next()){
                    idcustomer=rs.getInt("id");
                }
                rs.close();
                if (potongnota_cb.isSelected()){
                    statement.executeUpdate("UPDATE nota_penjualan SET retur=retur+"+total_txt.getText()+" WHERE id="+notapenjualan_cb.getValue().toString());
                    statement.executeUpdate("INSERT INTO retur_penjualan (id_customer, id_notapenjualan, tanggal, total_harga, potong_nota, status_aktif)"
                                            + " VALUES ("+idcustomer+", "+notapenjualan_cb.getValue().toString()+", '"+tanggal.getValue().toString()+"', "+total_txt.getText()
                                            + ", TRUE, 1)");
                }   
                else if (potongtunai_cb.isSelected()){
                    statement.executeUpdate("INSERT INTO retur_penjualan (id_customer, id_notapenjualan, tanggal, total_harga, potong_nota, status_aktif)"
                                            + " VALUES ("+idcustomer+", "+notapenjualan_cb.getValue().toString()+", '"+tanggal.getValue().toString()+"', "+total_txt.getText()
                                            + ", FALSE, 1)");
                }
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
                                    Stage stage = (Stage) addreturpenjualan_cancel.getScene().getWindow();
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
    void addreturpenjualan_cancel_btn(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                statement.executeUpdate("DELETE FROM detail_retur_penjualan WHERE id_returpenjualan="+returpenjualan_id.getText());
                Stage stage = (Stage) addreturpenjualan_cancel.getScene().getWindow();
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
    void itemchange(ActionEvent event){
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM detail_nota_penjualan dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapenjualan="+notapenjualan_cb.getValue().toString()+" AND i.nama='"+item_cb.getValue().toString()+"'");
            int jumlah=0;
            while(rs.next()){
                jumlah=rs.getInt("dnp.jumlah");
            }
            jumlah_txt.setText(jumlah+" bj");
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
    void change(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            item_cb.getItems().clear();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM detail_nota_penjualan dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapenjualan=" + notapenjualan_cb.getValue().toString());
            while(rs.next()){
                dataitem.add(new String(rs.getString("i.nama")));
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
    void setcustomer_btnClick(ActionEvent event) {
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
            
            rs = statement.executeQuery("SELECT * FROM nota_penjualan WHERE status_aktif=1 AND id_customer="+idcustomer);
            while(rs.next()){
                datanp.add(
                    new String(rs.getString("id"))
                );
            }
            notapenjualan_cb.setItems(datanp);
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
