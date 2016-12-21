/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ClientHandler extends Thread {

    private BufferedReader br;
    private BufferedWriter bw;
    private Server server;
    private String receivedMessage = "";

    public ClientHandler(Server server, BufferedReader br, BufferedWriter bw) {
        this.server = server;
        this.br = br;
        this.bw = bw;
    }

    @Override
    public void run() {
        while (true) {  
            try {
                receivedMessage = br.readLine();
                System.out.println("received: " + receivedMessage);
                server.sendToAllClients(receivedMessage);
            } catch (IOException ex) {
                disconnect();
            }
        }
    }
    public void send() throws IOException{
        bw.write(receivedMessage);
        bw.flush();
    }

    public String getMessage() {
        return receivedMessage;
    }
    public void disconnect(){
        server.getClients().remove(this);
    }
}
