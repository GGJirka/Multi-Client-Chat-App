/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Server {
    
    private int port;
    private DataInputStream is;
    
    public Server(int port){
        this.port = port;
    }
    public void createServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server started");
            Socket socket = serverSocket.accept();
            System.out.println("connected"+socket.getInetAddress());
            is = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void readServer(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println("from client> "+is.readUTF());
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }        
        });
        thread.start();
    }
}
