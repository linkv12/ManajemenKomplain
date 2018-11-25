/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author link_v12
 */
public class databaseLogin {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ArrayList<modelLogin> mahasiswa = new ArrayList<>();
    
    public void connect(){
        try {
            String url = "jdbc:mysql://localhost/tubes";
            String user = "root";
            String pass = "system";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(databaseLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(databaseLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(databaseLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }
    
    public modelLogin checkLogin(modelLogin login) {
        connect();
        modelLogin fromdb = new modelLogin();
        try {
            String query = "SELECT * FROM dataLogin " 
                    + " where username like " + "'"
                    + login.getUsername() + "'"
                    + " and password like  " + "'"
                    + login.getPassword() + "';";
            //System.out.println(query);
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                //System.out.println("goood");
                fromdb.setUsername(rs.getString("username"));
                fromdb.setPassword(rs.getString("password"));
                fromdb.setType(rs.getString("type"));
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
            Logger.getLogger(databaseLogin.class.getName()).log(Level.SEVERE, null, ex);     
            disconnect();
            return null;
        }
    }
}
