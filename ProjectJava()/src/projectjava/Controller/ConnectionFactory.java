/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JOELLA
 */
public class ConnectionFactory {
    private static String url = "jdbc:mysql://localhost:3306/jesselland";
    private static String username = "root";
    private static String password = "";
    
    public static Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
