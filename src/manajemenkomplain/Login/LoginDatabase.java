/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.Login;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
/**
 *
 * @author aktama
 */
public class LoginDatabase {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    public LoginDatabase() {
        
    }
    
    public void connect(){
        try {
            String url = "jdbc:mysql://localhost/tubes";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }

    public LoginModel checkLogin(LoginModel login) {
        connect();
        LoginModel fromdb = new LoginModel();
        try {
            String query = "SELECT * FROM user " 
                    + " where idUser like " + "'"
                    + login.getIdUser() + "'"
                    + " and password like  " + "'"
                    + login.getPassword() + "';";
            //System.out.println(query);
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                //System.out.println("goood");
                fromdb.setIdUser(rs.getString("idUser"));
                fromdb.setPassword(rs.getString("password"));
                fromdb.setidLevel(rs.getString("idLevel"));
                return (fromdb);
            } else {
                //System.out.println("invalid");
                return (null);
            }
            
            
            //System.out.println("username = " + fromdb.getUsername());
            //System.out.println("password = " + fromdb.getPassword());
            //System.out.println (fromdb instanceof modelLogin);
            //System.out.println(login instanceof modelLogin);
            //System.out.println(fromdb.getUsername() == login.getUsername());
            //disconnect();
            //System.out.println("dsajkdsav hdgbjh fnasf agfjag fjsagfjsg fshzfkab yb hfajg f,saa yfI Y");
            //return (fromdb.equals(login));      
        } catch (SQLException ex) {
            //System.out.println("sql thrown");
            Logger.getLogger(LoginDatabase.class.getName()).log(Level.SEVERE, null, ex);     
            disconnect();
            return null;
        }
    }

}
