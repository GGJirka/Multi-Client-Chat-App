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
    private LinkedList<ChatHandler>friendListChat; 
    
    public ChatManager(){
        friendListChat = new LinkedList<>();
    }
    public ChatHandler findChatByUser(String username){
        ChatHandler chat = null;
        for(ChatHandler user:friendListChat){
            chat = user;
            System.out.println(user.getUsername()+" == "+username);
            if(user.getUsername().equalsIgnoreCase(username)){
                return chat;
            }
        }
        System.out.println(chat.getUsername());
        return chat;
    }
    public void addChat(ChatHandler text){
        this.friendListChat.add(text);
    }
    public LinkedList<ChatHandler> getFriendListChat(){
        return this.friendListChat;
    }
    public ChatHandler getFriendChatAt(int index){
        return this.friendListChat.get(index);
    }
    public void append(int index, String text){
        this.friendListChat.get(index).getChat().append(text);
    }
}
