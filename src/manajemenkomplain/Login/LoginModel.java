/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manajemenkomplain.Login;

/**
 *
 * @author aktama
 */
public class LoginModel {

    private String idUser;
    private String idLevel;
    private String password;

    public LoginModel(String idUser, String idLevel, String password) {
        this.idUser = idUser;
        this.idLevel = idLevel;
        this.password = password;
    }

    public LoginModel(String idUser, String password) {
        this.idUser = idUser;
        this.password = password;
    }

    public LoginModel() {
        
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

    public void setidLevel(String idLevel) {
        this.idLevel = idLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return this.idLevel.equals("adm");
    }
}
