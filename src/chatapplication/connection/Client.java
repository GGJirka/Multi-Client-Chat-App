/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import chatapplication.frames.ChatFrame;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author root
 */
public class Client {
    private int port;
    private ChatFrame chat;
    private DataOutputStream os;
    
    public Client(ChatFrame chat, int port){
        this.chat = chat;
        this.port = port;
    }
    public void createClient() throws IOException{
        Socket clientSocket = new Socket("localhost", port);
        os = new DataOutputStream(clientSocket.getOutputStream());
    }
    public void sendMessage(String message) throws IOException{
        os.writeUTF(message.trim());
        //os.flush();
    }
}
