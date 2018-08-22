
package model.bean;


public class Login {
    
    private String username;
    private String pass;
    private String cargo;

    public Login(String username, String pass,String cargo) {
        this.username = username;
        this.pass = pass;
        this.cargo = cargo;
    }
    public Login(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getCargo() {
        return cargo;
    }

}
