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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author JOELLA
 */
public class EditReturPenjualanController {
    @FXML
    private Label detailreturpenjualan_id;

    @FXML
    private JFXTextField jumlah_textfield;

    @FXML
    private JFXButton adddetailreturpenjualan_btn;

    @FXML
    private JFXComboBox<String> item_cb;

    @FXML
    private Label jumlah_txt;

    @FXML
    private Label customer_id;

    @FXML
    private Label notapenjualan_id;

    @FXML
    private Label returpenjualan_id;

    @FXML
    private JFXDatePicker tanggal;

    @FXML
    private TableView<addreturpenjualan_class> editreturpenjualan_table;

    @FXML
    private TableColumn id;

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
    private Label customer_id2;

    @FXML
    private JFXButton editreturpenjualan_btn;

    @FXML
    private JFXButton addreturpenjualan_cancel;

    @FXML
    private JFXRadioButton potongnota_cb;

    @FXML
    private JFXRadioButton potongtunai_cb;

    @FXML
    private JFXButton deletedetailreturpenjualan_btn;
    
    ObservableList<addreturpenjualan_class> datarp = FXCollections.observableArrayList();
    ObservableList<String> dataitem = FXCollections.observableArrayList();

    private int masuk = 0;
    private int totalmasuk = 0;
    private ToggleGroup group;
    @FXML
    void initialize(returpenjualan_class rp){
        group = new ToggleGroup();
        potongnota_cb.setToggleGroup(group);
        potongtunai_cb.setToggleGroup(group);
        Connection con = null;
        Statement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
          
            this.notapenjualan_id.setText(String.valueOf(rp.getId_notapenjualan()));
            this.customer_id.setText(rp.getNama_customer());
            this.customer_id2.setText(rp.getNama_customer());
            this.returpenjualan_id.setText(String.valueOf(rp.getId()));
            String date = rp.getTanggal().toString();
            LocalDate ld = LocalDate.parse(date);
            this.tanggal.setValue(ld);
            
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_retur_penjualan'");
            while(rs.next()){
                masuk=rs.getInt("AUTO_INCREMENT");
            }
            detailreturpenjualan_id.setText(String.valueOf(masuk));
            rs.close();
            
            rs = statement.executeQuery("SELECT * FROM retur_penjualan WHERE id="+rp.getId());
            while (rs.next()){
                if(rs.getBoolean("potong_nota")){
                    potongnota_cb.setSelected(true);
                    potongtunai_cb.setSelected(false);
                    totalmasuk = rp.getTotal();
                }
                else{
                    potongnota_cb.setSelected(false);
                    potongtunai_cb.setSelected(true);
                }
            }
            rs.close();
            
            rs = statement.executeQuery("SELECT DISTINCT * FROM detail_nota_penjualan dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapenjualan=" + notapenjualan_id.getText().toString());
            while(rs.next()){
                dataitem.add(new String(rs.getString("i.nama")));
            }
            item_cb.setItems(dataitem);
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
            editreturpenjualan_table.setItems(datarp);
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
            editreturpenjualan_table.getItems().clear();
            con = ConnectionFactory.getConnection();
            statement = con.createStatement();
            
            int masukk=0;
            ResultSet rs = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'jesselland' AND TABLE_NAME = 'detail_retur_penjualan'");
            while(rs.next()){
                masukk=rs.getInt("AUTO_INCREMENT");
            }
            detailreturpenjualan_id.setText(String.valueOf(masukk));
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
            editreturpenjualan_table.setItems(datarp);
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
    void adddetailreturpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
                    if(item_cb.getValue().isEmpty()==false){
                        //if(Integer.parseInt(jumlah_txt.getText()) > Integer.parseInt(jumlah_textfield.getText())){
                            statement = con.createStatement();
                            int iditem=0;
                            ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM detail_nota_penjualan dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapenjualan="+notapenjualan_id.getText()+" AND i.nama='"+item_cb.getValue().toString()+"'");
                            while(rs.next()){
                                iditem=rs.getInt("i.id");
                            }
                            rs.close();
                            statement.executeUpdate("INSERT INTO detail_retur_penjualan(id_returpenjualan, id_item, jumlah) VALUES ("+returpenjualan_id.getText()+", "+iditem+", "+jumlah_textfield.getText()+")");
                            initialize2();
                        //} 
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
                statement.executeUpdate("DELETE FROM detail_retur_penjualan WHERE id_returpenjualan="+returpenjualan_id.getText()+ " AND id >= "+masuk);
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
    void deletedetailreturpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
                Statement statement = null;
                con = ConnectionFactory.getConnection();
                try {
                    addreturpenjualan_class rp = (addreturpenjualan_class) editreturpenjualan_table.getSelectionModel().getSelectedItem();
                    int data = rp.getId();
                    statement = con.createStatement();
                    statement.executeUpdate("DELETE FROM detail_retur_penjualan WHERE id="+data);
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
    void editreturpenjualan_btnClick(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            if (potongnota_cb.isSelected()){
                statement.executeUpdate("UPDATE nota_penjualan SET retur=retur-"+totalmasuk+"+"+total_txt.getText()+" WHERE id="+notapenjualan_id.getText());
                statement.executeUpdate("UPDATE retur_penjualan SET tanggal='"+tanggal.getValue().toString()+"', total_harga="+total_txt.getText()+", potong_nota=TRUE WHERE id="+returpenjualan_id.getText());
            }
            else if (potongtunai_cb.isSelected()){
                statement.executeUpdate("UPDATE retur_penjualan SET tanggal='"+tanggal.getValue().toString()+"', total_harga="+total_txt.getText()+", potong_nota=FALSE WHERE id="+returpenjualan_id.getText());
                if (totalmasuk != 0){
                    statement.executeUpdate("UPDATE nota_penjualan SET retur=retur-"+totalmasuk+" WHERE id="+notapenjualan_id.getText());
                }
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
    void itemchange(ActionEvent event) {
        Connection con = null;
        Statement statement = null;
        con = ConnectionFactory.getConnection();
        try{
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM detail_nota_penjualan dnp JOIN item i ON dnp.id_item=i.id WHERE dnp.id_notapenjualan="+notapenjualan_id.getText().toString()+" AND i.nama='"+item_cb.getValue().toString()+"'");
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
}
