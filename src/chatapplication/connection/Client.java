/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import chatapplication.frames.ChatFrame;
import chatapplication.frames.RoomFrame;
import chatapplication.frames.RoomsFrame;
import chatapplication.main.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Client {

    private BufferedWriter bw;
    private BufferedReader br;
    private Frame frame;
    private ChatFrame chat;
    private RoomFrame rooms;
    private Socket clientSocket;
    
    public Client(Frame frame){
        this.frame = frame;
        chat = frame.getChat();
        try {
            clientSocket = new Socket("localhost", 12345);
            bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            bw.write("user " + chat.getDatabase().user.getUsername() + "\r\n");
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public void userAdded(String type, String fromUser, String room) throws Exception{
        System.out.println(room);
        bw.write(type+" "+fromUser+" "+room+"\r\n");
        bw.flush();
    }
    public void writeMessage(String type,String fromUsername, String toUsername, String message) throws IOException {
        bw.write(type+" "+fromUsername + " " + toUsername + " " + message + "\r\n");
        bw.flush();
    }
    public void userLeft(String type, String user){
        try {
            bw.write(type+" "+user+"\r\n");
            bw.flush();
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void chatUserLeft(String type,String user, String room){
        try {
            bw.write(type+" "+user+" "+room+"\r\n");
            bw.flush();
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void writeMessage(String type, String fromUsername,String message, ArrayList<String> users){
        try {
            for(String username:users){
                System.out.println("ROOM: "+username);
                bw.write(type+" "+fromUsername + " " + username+ " " + message + "\r\n");
                bw.flush();
            }
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newReading(){
        Thread readingThread = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    try {
                        String message = br.readLine();
                        String[] data = message.split(" ");

                        if(data[0].equalsIgnoreCase("chat")){
                            chat = frame.getChat();
                            String fromUser = data[1];
                            String toUser = data[2];
                            StringBuilder builder = new StringBuilder();
                            for(int i=3;i<data.length;i++){
                                builder.append(data[i]+" ");
                            }
                            chat.sendMessage(fromUser.trim(), toUser.trim(), " " + builder.toString());
                        }
                        if(data[0].equalsIgnoreCase("room")){
                            System.out.println("YES");
                            rooms = frame.getRooms();
                            rooms.receiveMessage(data[1],data[2],data[3]);
                        }
                        if(data[0].equalsIgnoreCase("user")){
                            System.out.println("2 "+message);
                            if(!data[1].equalsIgnoreCase(chat.getDatabase().user.getUsername())){
                                System.out.println(data[1]+chat.getDatabase().user.getUsername());
                                chat.userConnected(data[1]);
                            }
                        }
                        if(data[0].equalsIgnoreCase("roomadd")){
                            frame.getRooms().userConnected(data[1],data[2]+" "+data[3]);
                        }
                        if(data[0].equalsIgnoreCase("roomremove")){
                            frame.getRooms().userDisconnected(data[1],data[2]+" "+data[3]);
                        }
                        if(data[0].equalsIgnoreCase("chatleft")){
                            chat.userDisconnected(data[1]);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            }
        });
        readingThread.start();
    }
    public void disconnect() {
        try {
            bw.close();
            br.close();
            clientSocket.close();
            clientSocket = null;
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

}
