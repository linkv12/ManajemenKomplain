/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.keluhan;

/**
 *
 * @author Akbar
 */
public class Keluhan {
    private String idKeluhan;
    private String idUser;
    private String temaKeluhan;
    private String deskripsi;
    private boolean keluhanMendesak;

    public Keluhan () {}
    
    public Keluhan(String idKeluhan, String idUser, String temaKeluhan, String deskripsi, boolean keluhanMendesak) {
        this.idKeluhan = idKeluhan;
        this.idUser = idUser;
        this.temaKeluhan = temaKeluhan;
        this.deskripsi = deskripsi;
        this.keluhanMendesak = keluhanMendesak;
    }

    public String getIdKeluhan() {
        return idKeluhan;
    }

    public void setIdKeluhan(String idKeluhan) {
        this.idKeluhan = idKeluhan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTemaKeluhan() {
        return temaKeluhan;
    }

    public void setTemaKeluhan(String temaKeluhan) {
        this.temaKeluhan = temaKeluhan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public boolean isKeluhanMendesak() {
        return keluhanMendesak;
    }

    public void setKeluhanMendesak(boolean keluhanMendesak) {
        this.keluhanMendesak = keluhanMendesak;
    }

    
}

