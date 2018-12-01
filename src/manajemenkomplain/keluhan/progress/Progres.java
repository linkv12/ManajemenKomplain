/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.keluhan.progress;

/**
 *
 * @author Akbar
 */
public class Progres {
    private String idKeluhan;
    private String idSuratTugas;
    private String idUser;
    private String status;

    public Progres(String idKeluhan, String idSuratTugas, String idUser, String status) {
        this.idKeluhan = idKeluhan;
        this.idSuratTugas = idSuratTugas;
        this.idUser = idUser;
        this.status = status;
    }

    public Progres() {}

    public String getIdKeluhan() {
        return idKeluhan;
    }

    public void setIdKeluhan(String idKeluhan) {
        this.idKeluhan = idKeluhan;
    }

    public String getIdSuratTugas() {
        return idSuratTugas;
    }

    public void setIdSuratTugas(String idSuratTugas) {
        this.idSuratTugas = idSuratTugas;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

