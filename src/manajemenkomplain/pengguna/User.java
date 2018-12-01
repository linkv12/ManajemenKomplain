/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.pengguna;

/**
 *
 * @author Akbar
 */
public class User {
    private String idUser;
    private String idLevel;
    private String namaUser;
    private String password;
    private String alamat;
    private String noTelp;

    public User(String idUser, String idLevel, String namaUser, String password, String alamat, String noTelp) {
        this.idUser = idUser;
        this.idLevel = idLevel;
        this.namaUser = namaUser;
        this.password = password;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public User() {
       
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(String idLevel) {
        this.idLevel = idLevel;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}

