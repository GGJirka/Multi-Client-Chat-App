/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ArrayList<ClientHandler> clients;
    private ArrayList<BufferedWriter> writer;
    private static Server server;

    public static void main(String[] args) {
        server = new Server();
    }

    public Server() {
        try {
            this.serverSocket = new ServerSocket(12345);
            this.clients = new ArrayList<>();
            this.writer = new ArrayList<>();
            this.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        Thread listenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        clientSocket = serverSocket.accept();
                        ClientHandler client = new ClientHandler(server, new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream())),
                                new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
                        clients.add(client);
                        writer.add(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
                        Thread clientThread = new Thread(client);
                        clientThread.start();
                        System.out.println("Client connected: " + clientSocket.getInetAddress());
                    } catch (IOException ex) {
                    
                    }
                }
            }
        });
        listenThread.start();
    }

    public void sendToAllClients(String message) {
        if (!"".equals(message)) {
            for (BufferedWriter bw : writer) {
                try {
                    bw.write(message + "\r\n");
                    bw.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void tryToReconnect() throws IOException {
        this.serverSocket.close();
        clientSocket = null;
        System.gc();
        clientSocket = serverSocket.accept();
    }

    public ArrayList<ClientHandler> getClients() {
        return this.clients;
    }
}
