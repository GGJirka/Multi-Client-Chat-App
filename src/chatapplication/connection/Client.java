/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.connection;

import chatapplication.frames.ChatFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author root
 */
public class Client {
    
    private BufferedWriter bw; 
    private BufferedReader br;
    private ChatFrame chat;
    private Socket clientSocket;

    
    public Client(ChatFrame chat) {
        this.chat = chat;
        try {
            clientSocket = new Socket("localhost",12345);
            bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void writeMessage(String fromUsername,String toUsername, String message) throws IOException{
        bw.write(fromUsername+" "+toUsername+" "+message+"\r\n");
        bw.flush();
    }
    
    public void reading(){
        Thread readingThread = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    try{
                        String message = br.readLine();
                        String[] data = message.split(" ");
                        StringBuilder fromUser = new StringBuilder();
                        StringBuilder toUser = new StringBuilder();
                        StringBuilder text = new StringBuilder();
                        int counter = 0;
                        for(char c:message.toCharArray()){
                            if(counter<=1){
                                if(c!=' '){
                                    if(counter==0){
                                        fromUser.append(c);
                                    }else if(counter==1){
                                        toUser.append(c);
                                    }
                                }else{
                                    counter++;
                                }
                            }else{
                                text.append(c);
                            }
                        }
                        chat.sendMessage(fromUser.toString().trim(), toUser.toString().trim(), " "+text.toString());
                        System.out.println("got message"+fromUser.toString()+" "+ toUser.toString()+" "+text.toString());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        readingThread.start();
    }
}
