/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.keluhan.suratTugas;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Akbar
 */
public class SuratTugasDatabase {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ArrayList<SuratTugas> surattugas = new ArrayList<>();

    public SuratTugasDatabase() {
        loadSuratTugas();
    }
    
    public void connect(){
        try {
            String url = "jdbc:mysql://localhost/tubes";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SuratTugasDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuratTugasDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(SuratTugasDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }
    
    public void loadSuratTugas() {
        connect();
        try {
            String query = "SELECT * FROM surattugas";
            rs = stmt.executeQuery(query);
            this.surattugas = new ArrayList<> ();
            while (rs.next()){
                surattugas.add(new SuratTugas(rs.getString("isSuraTugas"), rs.getString("idKeluhan")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuratTugasDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }

    public ArrayList<SuratTugas> getSuratTugas() {
        return surattugas;
    }
    
    public void addSuratTugas(SuratTugas st) {
        connect();
        String query = "INSERT INTO surattugas VALUES (";
        query += "'" + st.getIdSuratTugas() + "',";
        query += "'" + st.getIdKeluhan() + "'";
        query += ")";
        if (manipulate(query)) surattugas.add(st);
        disconnect();
    }
     
    public boolean cekDuplikatIdSuratTugas(String idSuratTugas){
        boolean cek = false;
        for (SuratTugas st : surattugas) {
            if (st.getIdSuratTugas().equals(idSuratTugas)){
                cek = true;
                break;
            }
        }
        return cek;
    }
    
    public void delSuratTugas(String idSuratTugas) {
        connect();
        String query = "DELETE FROM surattugas WHERE idSuratTugas='" + idSuratTugas + "'";
        if (manipulate(query)){
            for (SuratTugas st : surattugas) {
                if (st.getIdSuratTugas().equals(idSuratTugas)){
                    surattugas.remove(st);
                    break;
                }
            }
        }
        disconnect();
    }
    
    public String getMaxIdSuratTugas () {
        int max = 0;
        if (!surattugas.isEmpty()) {
            for (SuratTugas kel : surattugas) {
                if (Integer.valueOf(kel.getIdSuratTugas()) > max) {
                    max = Integer.valueOf(kel.getIdSuratTugas());
                }
            }
            max += 1;
            String idSuratTugas = String.valueOf(max);
            while (idSuratTugas.length() < 5) {
                idSuratTugas = "0" + idSuratTugas;
            }
            return "S"+idSuratTugas;
        } else {
            return "S00000";
        }
    }
}
