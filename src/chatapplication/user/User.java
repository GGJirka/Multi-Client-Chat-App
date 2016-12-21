/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.user;

/**
 *
 * @author root
 */
public class User {
    
    private int id;
    private String username, password, email;
    
    public User(int id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public void logout(){
        username = "";
        password="";
        email="";
        id = 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
}
