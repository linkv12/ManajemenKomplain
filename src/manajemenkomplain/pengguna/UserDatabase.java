/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.pengguna;

import manajemenkomplain.pengguna.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Akbar
 */
public class UserDatabase {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ArrayList<User> user = new ArrayList<>();

    public UserDatabase() {
        loadUser();
    }
    
    public void connect(){
        try {
            String url = "jdbc:mysql://localhost/tubes";
            String user = "root";
            String pass = "system";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }
    
    public void loadUser() {
        connect();
        try {
            String query = "SELECT * FROM user";
            rs = stmt.executeQuery(query);
            while (rs.next()){
                user.add(new User(rs.getString("idUser"), rs.getString("idLevel"), rs.getString("nama"),
                            rs.getString("password"), rs.getString("alamat"), rs.getInt("noTelp")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }

    public ArrayList<User> getUser() {
        return user;
    }
    
    public void addUser(User u) {
        connect();
        String query = "INSERT INTO user VALUES (";
        query += "'" + u.getIdUser() + "',";
        query += "'" + u.getIdLevel() + "',";
        query += "'" + u.getNamaUser() + "',";
        query += "'" + u.getPassword() + "',";
        query += "'" + u.getAlamat() + "',";
        query += "'" + u.getNoTelp() + "'";
        query += ")";
        if (manipulate(query)) user.add(u);
        disconnect();
    }
     
    public boolean cekDuplikatIdUser(String idUser){
        boolean cek = false;
        for (User u : user) {
            if (u.getIdUser().equals(idUser)){
                cek = true;
                break;
            }
        }
        return cek;
    }
    
    public void delUser(String idUser) {
        connect();
        String query = "DELETE FROM user WHERE idUser='" + idUser + "'";
        if (manipulate(query)){
            for (User u : user) {
                if (u.getIdUser().equals(idUser)){
                    user.remove(u);
                    break;
                }
            }
        }
        disconnect();
    }
    
    public User getUser(String idUser) {
        try {
            connect();
            User x = new User();
            String query = "select * FROM user WHERE idUser='" + idUser + "'";
            //System.out.println(query);
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                //System.out.println("goood");
                x.setIdUser(rs.getString("idUser"));
                x.setPassword(rs.getString("password"));
                x.setIdLevel(rs.getString("idLevel"));
                x.setNamaUser(rs.getString("nama"));
                x.setAlamat(rs.getString("alamat"));
                x.setNoTelp(rs.getInt("noTelp"));
                return (x);
            } else {
                //System.out.println("invalid");
                disconnect();
                return (null);
            }
        } catch (SQLException ex) {       
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
            disconnect();
            return (null);
        }
    
    }
    
    public void updateUser(User u) {
        connect();
        String query = "UPDATE user SET";
        query += "'" + u.getIdUser() + "',";
        query += "'" + u.getIdLevel() + "',";
        query += "'" + u.getNamaUser() + "',";
        query += "'" + u.getPassword() + "',";
        query += "'" + u.getAlamat() + "',";
        query += "'" + u.getNoTelp() + "'";
        if (manipulate(query)){
            for (User usr : user) {
                if (usr.getIdUser().equals(usr.getIdUser())){
                    usr.setIdLevel(usr.getIdLevel());
                    usr.setNamaUser(usr.getNamaUser());
                    usr.setPassword(usr.getPassword());
                    usr.setAlamat(usr.getAlamat());
                    usr.setNoTelp(usr.getNoTelp());
                    break;
                }
            }
        }
        disconnect();
    }
}
