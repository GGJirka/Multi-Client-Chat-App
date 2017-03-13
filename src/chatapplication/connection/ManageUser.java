/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author root
 */
public class ManageUser extends Thread{
    
    private String username;
    private DataInputStream is;
    private DataOutputStream os;
    
    public ManageUser(Socket client) throws IOException{
        is = new DataInputStream(client.getInputStream());
        os = new DataOutputStream(client.getOutputStream());
        
    }
    @Override
    public void run(){
        
    }
    public String getUsername(){
        return username;
    }
}
