/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.rooms;

import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Room {
    private String room;
    private ArrayList<String> users;
    private StringBuilder chat;
    
    public Room(String room){
        this.room = room;
        users = new ArrayList<>();
        chat = new StringBuilder();
    }
    
    public String getRoom() {
        return room;
    }
    public void setChat(StringBuilder chat){
        this.chat = chat;
    }
    public StringBuilder getChat(){
        return chat;
    }
    public void setRoom(String room) {
        this.room = room;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
    
    
}
