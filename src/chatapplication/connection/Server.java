/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Server {

    private int port;
    private DataInputStream is;
    private DataOutputStream os;
    private ArrayList<DataOutputStream> clients;
    private Scanner scanner;
    
    public static void main(String[] args){
        Server server = new Server(12345);
    }
    public Server(int port) {
        this.port = port;
        clients = new ArrayList<>();
        scanner = new Scanner(System.in);
        createServer();
    }
    
    public void createServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            
            System.out.println("server started");
            while(true){              
                Socket socket = serverSocket.accept();
                
                System.out.println("connected" + socket.getInetAddress());
                
                os = new DataOutputStream(socket.getOutputStream());
                clients.add(os);   
                readServer();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void readServer(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                while (true) {
                   Iterator<DataOutputStream> it = clients.iterator();
                   if(clients.size()>=2){
                   while(it.hasNext()){
                       DataOutputStream os = it.next();
                       Scanner scanner = new Scanner(System.in);
                       System.out.println("write something to clients");
                       try {
                           os.writeUTF(scanner.nextLine());
                           os.flush();
                       } catch (IOException ex) {
                           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                   }
                }
            }
        });
        thread.start();
    }
}
