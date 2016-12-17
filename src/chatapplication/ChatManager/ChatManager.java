/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.ChatManager;

import java.util.LinkedList;

/**
 *
 * @author root
 */
public class ChatManager {
    private LinkedList<StringBuffer>friendListChat; 
    
    public ChatManager(){
        friendListChat = new LinkedList<>();
    }
    public void addChat(StringBuffer text){
        this.friendListChat.add(text);
    }
    public LinkedList<StringBuffer> getFriendListChat(){
        return this.friendListChat;
    }
    public StringBuffer getFriendChatAt(int index){
        return this.friendListChat.get(index);
    }
    public void append(int index, String text){
        this.friendListChat.get(index).append(text);
    }
}
