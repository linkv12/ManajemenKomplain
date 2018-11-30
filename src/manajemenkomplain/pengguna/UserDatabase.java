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
            String pass = "";
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
                            rs.getString("password"), rs.getString("alamat"), rs.getString("noTelp")));
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
        String query = "INSERT INTO `user` VALUES (";
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
                x.setNoTelp(rs.getString("noTelp"));
                disconnect();
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
        String query = "UPDATE user SET ";
        query += "idLevel='" + u.getIdLevel() + "',";
        query += "nama='" + u.getNamaUser() + "',";
        System.out.println("in updateUser " + u.getNamaUser());
        query += "password='" + u.getPassword() + "',";
        query += "alamat ='" + u.getAlamat() + "',";
        query += "noTelp ='" + u.getNoTelp() + "'";
        query += "where idUser='" + u.getIdUser() + "'";
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
    
    
    public boolean isPasswordCorrect (String idUser,String password) {
        connect();
        try {
            String query = "select * FROM user WHERE idUser='" + idUser + "' and password='"+password+"';";
            ResultSet x = stmt.executeQuery(query);
            if (x.next()){
                disconnect();
                return true;
            } else {
                disconnect();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
            disconnect();
            return false;
        }
    }

    public void updatePassword(User u, String pass) {
       // connect();
        /*
        UPDATE `user` SET `password`=[value-4] WHERE `idUser`=[value-1],`idLevel`=[value-2],`nama`=[value-3],`alamat`=[value-5],`noTelp`=[value-6]
        */
        /*
        String query = "UPDATE `user` SET";
        query += "`password`='" + pass + "' Where ";
        query += "`idUser`='" + u.getIdUser() + "',";
        query += "`idLevel`='" + u.getIdLevel() + "',";
        query += "`nama`='" + u.getNamaUser() + "',";
        query += "`alamat`='" + u.getAlamat() + "',";
        query += "`noTelp`='" + u.getNoTelp() + "';";
       */
        //System.out.println(u.getPassword());
        u.setPassword(pass);
        //System.out.println(u.getPassword());
        this.updateUser(u);
       // disconnect();
    }
    
    public User getUser(ArrayList<User> ArrayListusr, String idUser, String idLevel ) {
        for (User u : ArrayListusr) {
            if (u.getIdUser().equalsIgnoreCase(idUser) && u.getIdLevel().equalsIgnoreCase(idLevel)) {
                return u;
            }
        }
        return null;
    }
}
