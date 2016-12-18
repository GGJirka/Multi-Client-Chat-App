/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import chatapplication.frames.ChatFrame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Client {

    private int port;
    private DataOutputStream os;
    private DataInputStream is;
    private String username;
    private ChatFrame chat;

    public Client(ChatFrame chat, String username, int port) {
        this.port = port;
        this.username = username;
        this.chat = chat;
        Socket clientSocket;
        try {
            clientSocket = new Socket("localhost", port);
            os = new DataOutputStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String toUser, String message) throws IOException {
        os.writeUTF(toUser + " " + message.trim());
    }

    public void readMessage() {
        Thread readingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(is.readUTF());
                    } catch (IOException ex) {
                    
                    }
                }
            }
        });
        readingThread.start();
    }
}
