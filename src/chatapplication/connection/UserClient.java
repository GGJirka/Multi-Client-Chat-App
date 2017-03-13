/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

/**
 *
 * @author root
 */
public class UserClient {
    
    private String username;
    private Client client;
    
    public UserClient(String username, Client client){
        this.username = username;
        this.client = client;
    }
    public String getUsername(){
        return username;
    }
    public Client getClient(){
        return client;
    }
}
