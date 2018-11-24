/*
 * 
 *  LICENSE
 * 
 */

package manajemenkomplain.Login;

/**
 *
 * @author link_v12
 */
public class modelLogin {
    private String username;
    private String password;
    private String type;

    public modelLogin() {
    }

    public modelLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public modelLogin(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
