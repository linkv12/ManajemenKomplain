/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.keluhan;

import manajemenkomplain.keluhan.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Akbar
 */
public class KeluhanDatabase {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ArrayList<Keluhan> keluhan = new ArrayList<>();

    public KeluhanDatabase() {
        //System.out.println("init database keluhan");
        loadKeluhan();
        //System.out.println("done ..................init database keluhan");
    }
    
    public void connect(){
        try {
            String url = "jdbc:mysql://localhost/tubes";
            String user = "root";
            String pass = "system";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(KeluhanDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(KeluhanDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(KeluhanDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }
    
    public void loadKeluhan() {
        connect();
        try {
            //System.out.println("leadKeluhan");
            String query = "SELECT * FROM keluhan;";
            rs = stmt.executeQuery(query);
            while (rs.next()){
                keluhan.add(new Keluhan(rs.getString("idKeluhan"), rs.getString("idUser"), rs.getString("temaKeluhan"), rs.getString("deskripsi"), rs.getBoolean("keluhanMendesak")));
                //System.out.println(rs.getString("idKeluhan"));
            }
        } catch (SQLException ex) {
            System.out.println("NOT FINE");
            Logger.getLogger(KeluhanDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        //System.out.println("leadKeluhan Fine");
    }

    public ArrayList<Keluhan> getKeluhan() {
        return keluhan;
    }
    
    public void addKeluhan(Keluhan k) {
        connect();
        String query = "INSERT INTO keluhan VALUES (";
        query += "'" + k.getIdKeluhan() + "',";
        query += "'" + k.getIdUser() + "',";
        query += "'" + k.getTemaKeluhan() + "',";
        query += "'" + k.getDeskripsi() + "',";
        query += "'" + k.isKeluhanMendesak() + "',";
        query += ")";
        if (manipulate(query)) keluhan.add(k);
        disconnect();
    }
     
    public boolean cekDuplikatIdKeluhan(String idKeluhan){
        boolean cek = false;
        for (Keluhan k : keluhan) {
            if (k.getIdKeluhan().equals(idKeluhan)){
                cek = true;
                break;
            }
        }
        return cek;
    }
    
    public void delKeluhan(String idKeluhan) {
        connect();
        String query = "DELETE FROM keluhan WHERE idKeluhan='" + idKeluhan + "'";
        if (manipulate(query)){
            for (Keluhan k : keluhan) {
                if (k.getIdKeluhan().equals(idKeluhan)){
                    keluhan.remove(k);
                    break;
                }
            }
        }
        disconnect();
    }
    
    public void updateKeluhan(Keluhan k) {
        connect();
        String query = "UPDATE keluhan SET";
        query += "'" + k.getIdKeluhan() + "',";
        query += "'" + k.getIdUser() + "',";
        query += "'" + k.getTemaKeluhan() + "',";
        query += "'" + k.getDeskripsi() + "',";
        query += "'" + k.isKeluhanMendesak() + "',";
        if (manipulate(query)){
            for (Keluhan klh : keluhan) {
                if (klh.getIdKeluhan().equals(klh.getIdKeluhan())){
                    klh.setIdUser(klh.getIdUser());
                    klh.setTemaKeluhan(klh.getTemaKeluhan());
                    klh.setDeskripsi(klh.getDeskripsi());
                    klh.setKeluhanMendesak(klh.isKeluhanMendesak());
                    break;
                }
            }
        }
        disconnect();
    }
}
