/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.security.MessageDigest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author JOELLA
 */
public class JavaController {
    
    Admin admin = new Admin();
    
    @FXML
    private JFXButton close_btn;
    
    @FXML
    private JFXButton login_btn;
    
    @FXML
    private JFXTextField user_text;
    
    @FXML
    private JFXPasswordField passw_text;

    
    
//    @FXML
//    public void initialize() {
//        
//    }
    
    @FXML
	public void login(ActionEvent event) {
            try{
        	MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        	String passw = passw_text.getText().toString();
            	messageDigest.update(passw.getBytes());
            	byte[] digest=messageDigest.digest();
            	StringBuffer sb = new StringBuffer();
            	for (byte b : digest) {
                	sb.append(Integer.toHexString((int) (b & 0xff)));
            	}
        	//System.out.println(sb.toString());
        	boolean check = admin.login(user_text.getText(), sb.toString());
        	if(check){
            	System.out.println("Masuk yey");
                Admin dataadmin = new Admin(user_text.getText(), sb.toString());
            	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectjava/view/home.fxml"));
            	Parent root1 = (Parent) fxmlLoader.load();
                HomeController display = fxmlLoader.getController();
                display.initialize(dataadmin);
                Stage stage = (Stage) login_btn.getScene().getWindow();
            	stage.close();
            	stage = new Stage();
            	Scene scene = new Scene(root1);
            	stage.setTitle("HOME");
            	stage.setScene(scene);
            	stage.show();
        	}
        	else{
            	System.out.println("Oh no");
        	}
            } catch (Exception e){
        	System.out.println(e.getMessage());
            }
	}

    
    @FXML
    public void closeProgram() {
        Stage stage = (Stage) close_btn.getScene().getWindow();
        stage.close();
    }
}
