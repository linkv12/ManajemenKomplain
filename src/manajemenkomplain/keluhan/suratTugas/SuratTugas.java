/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.keluhan.suratTugas;

/**
 *
 * @author Akbar
 */
public class SuratTugas {
    private String idSuratTugas;
    private String idKeluhan;

    public SuratTugas(String idSuratTugas, String idKeluhan) {
        this.idSuratTugas = idSuratTugas;
        this.idKeluhan = idKeluhan;
    }

    public String getIdSuratTugas() {
        return idSuratTugas;
    }

    public void setIdSuratTugas(String idSuratTugas) {
        this.idSuratTugas = idSuratTugas;
    }

    public String getIdKeluhan() {
        return idKeluhan;
    }

    public void setIdKeluhan(String idKeluhan) {
        this.idKeluhan = idKeluhan;
    }

    
}

