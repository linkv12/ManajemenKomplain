/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.keluhan.progress;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

/**
 *
 * @author Akbar
 */
public class ProgresDatabase {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ArrayList<Progres> progres;

    public ProgresDatabase() {
        loadProgres();
    }
    
    public void connect(){
        try {
            String url = "jdbc:mysql://localhost/tubes";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProgresDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProgresDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(ProgresDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }
    
    public void loadProgres() {
        connect();
        try {
            String query = "SELECT * FROM progres ";
            rs = stmt.executeQuery(query);
            progres = new ArrayList<>();
            while(rs.next()) {
                progres.add(new Progres(rs.getString("idKeluhan"), rs.getString("idSuratTugas"), rs.getString("idUser"), rs.getString("status")));   
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProgresDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }

    public ArrayList<Progres> getProgres() {
        return progres;
    }
    
    public void addPogres(Progres p) {
        connect();
        //System.out.println(p.getIdKeluhan() + "   =  " + p.getIdSuratTugas() + "   =  "+p.getIdUser() + "  =  " + p.getStatus());
        String query = "INSERT INTO progres VALUES (";
        query += "'" + p.getIdKeluhan() + "',";
        query += "'" + p.getIdSuratTugas() + "',";
        query += "'" + p.getIdUser() + "',";
        query += "'" + p.getStatus() + "'";
        query += ")";
        if (manipulate(query)) progres.add(p);
        disconnect();
    }
    
    
    /*
     private String idKeluhan;
    private String idSuratTugas;
    private String idUser;
    private String status;*/
    public void updateProgres(Progres p) {
        connect();
        String query = "UPDATE progres SET ";
        query += "idSuratTugas = '" + p.getIdSuratTugas() + "',";
        query += "idUser = '" + p.getIdUser() + "',";
        query += "status = '" + p.getStatus() + "'";
        query += " where idKeluhan : '" + p.getIdKeluhan() + "',";
        if (manipulate(query)){
            for (Progres pq : this.progres) {
                if (pq.getIdKeluhan().equals(p.getIdKeluhan())) {
                    pq.setIdSuratTugas(p.getIdSuratTugas());
                    pq.setIdUser(p.getIdUser());
                    pq.setStatus(p.getStatus());
                }
            }
        }
        disconnect();
    }
    
    
    public Progres getProgres(String idKeluhan) {
        try {
            connect();
            Progres x = new Progres();
            String query = "select * FROM progres WHERE idKeluhan ='" + idKeluhan + "'";
            //System.out.println(query);
            /*
             private String idKeluhan;
    private String idSuratTugas;
    private String idUser;
    private String status;
            */
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                //System.out.println("goood");
                x.setIdUser(rs.getString("idUser"));
                x.setIdKeluhan(rs.getString("idKeluhan"));
                x.setIdSuratTugas(rs.getString("idSuratTugas"));
                x.setStatus(rs.getString("status"));

                disconnect();
                return (x);
            } else {
                //System.out.println("invalid");
                disconnect();
                return (null);
            }
        } catch (SQLException ex) {       
            Logger.getLogger(ProgresDatabase.class.getName()).log(Level.SEVERE, null, ex);
            disconnect();
            return (null);
        }
    
    }
}
