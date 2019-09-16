/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JOELLA
 */
public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
    public boolean login(String user, String psw) {
        //ArrayList<Barang> data = new ArrayList();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        int count = 0;
        con = ConnectionFactory.getConnection();
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM "
                    + "admin where username = '" + user + "' and passw = '" + psw +"'");
            if (rs.next()) {
                System.out.println("Success");
                count++;
            }
            else{
                System.out.println("Username/password salah");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(count > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
