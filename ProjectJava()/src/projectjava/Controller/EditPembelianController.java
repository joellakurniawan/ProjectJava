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
public class EditPembelianController {
    
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
    private TableView<addpembelian_class> editnotapembelian_table;

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
    private JFXButton editpembelian_btn;

    @FXML
    private JFXButton editpembelian_cancel;

    @FXML
    private Label totalharga_txt;

    @FXML
    private Label supplier_id;

    @FXML
    private JFXButton deletedetailpembelian_btn;

    ObservableList<addpembelian_class> datanp = FXCollections.observableArrayList();
    ObservableList<String> dataitem = FXCollections.observableArrayList();
    int masuk = 0;
    
    @FXML
    void initialize(notapembelian_class np){
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            this.notapembelian_id.setText(String.valueOf(np.getId()));
            this.supplier_id.setText(np.getNama_supplier());
            String date = np.getTanggal().toString();
            LocalDate ld = LocalDate.parse(date);
            this.tanggal.setValue(ld);
            
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_nota_penjualan'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailnotapembelian_id.setText(String.valueOf(masuk));
            rs.close();
            
            int totaltxt=0;
                    rs = statement.executeQuery("SELECT DISTINCT dnp.id, i.id, i.nama, dnp.jumlah, dnp.harga_satuan FROM detail_nota_pembelian dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapembelian="+notapembelian_id.getText().toString());
                    while(rs.next()){
                        datanp.addAll(new addpembelian_class(
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
                    editnotapembelian_table.setItems(datanp);
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
            editnotapembelian_table.getItems().clear();
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            int masukk=0;
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_nota_penjualan'");
            while(rs.next()){
                masukk=rs.getInt("AUTO_INCREMENT");
            }
            detailnotapembelian_id.setText(String.valueOf(masukk));
            rs.close();
            
            int totaltxt=0;
                    rs = statement.executeQuery("SELECT DISTINCT dnp.id, i.id, i.nama, dnp.jumlah, dnp.harga_satuan FROM detail_nota_pembelian dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapembelian="+notapembelian_id.getText().toString());
                    while(rs.next()){
                        datanp.addAll(new addpembelian_class(
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
                    editnotapembelian_table.setItems(datanp);
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
    void deletedetailpembelian_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            addpembelian_class np = (addpembelian_class) editnotapembelian_table.getSelectionModel().getSelectedItem();
            ResultSet rs2 = statement.executeQuery("SELECT * FROM item WHERE id="+np.getId_item());
            String nama_barang = "";
            int hargaitem=0;
            while (rs2.next()){
                nama_barang=rs2.getString("nama");
                hargaitem=rs2.getInt("harga_pokok");
            }
            rs2.close();
            int jumlahdnp=np.getJumlah();
            int dnp=jumlahdnp*np.getHarga_satuan();
            int stockitem=0;
            rs2 = statement.executeQuery("SELECT * FROM item WHERE nama='"+nama_barang+"'");
            while (rs2.next()){
                stockitem=stockitem+rs2.getInt("stock");
            }
            rs2.close();
            int item=stockitem*hargaitem;
            int hasil=(item-dnp)/(stockitem-jumlahdnp); 
            statement.executeUpdate("UPDATE item SET harga_pokok="+hasil+" WHERE nama='"+nama_barang+"'");
            statement.executeUpdate("UPDATE item SET stock=stock-"+jumlahdnp+" WHERE id="+np.getId_item());
            statement.executeUpdate("DELETE FROM detail_nota_pembelian WHERE id="+np.getId());
            
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
    void editpembelian_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM detail_nota_pembelian WHERE id_notapembelian="+notapembelian_id.getText().toString());
            while (rs.next()){
                if (rs.getInt("id") >= masuk){
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
            }
            rs.close();
            
            statement.executeUpdate("UPDATE nota_pembelian SET tanggal='" + tanggal.getValue().toString() + "', total_harga=" + totalharga_txt.getText() +" WHERE id="+notapembelian_id.getText());
            
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
                                    Stage stage = (Stage) editpembelian_cancel.getScene().getWindow();
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
    void editpembelian_cancel_btn(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        try{
                con = ConnectionFactory.getConnection();
                statement = con.createStatement();
                statement.executeUpdate("DELETE FROM detail_nota_pembelian WHERE id_notapembelian="+notapembelian_id.getText()+ " AND id >= "+masuk);
                Stage stage = (Stage) editpembelian_cancel.getScene().getWindow();
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
