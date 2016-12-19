/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.ChatManager;

/**
 *
 * @author root
 */
public class ChatHandler {
    
    private String username;
    private StringBuilder chat;

    public ChatHandler(String username, StringBuilder chat) {
        this.username = username;
        this.chat = chat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public StringBuilder getChat() {
        return chat;
    }

    public void setChat(StringBuilder chat) {
        this.chat= chat;
    }
    
    
}
