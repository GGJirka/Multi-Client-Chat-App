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
public class RoomManager {
    private ArrayList<Room> rooms;
    
    public RoomManager(){
        rooms = new ArrayList<>();
    }
    public Room getRoomByName(String name){
        for(Room room:rooms){
            if(name == room.getRoom()){
                return room;
            }
        }
        return null;
    }
    public void addRoom(String roomName){
        rooms.add(new Room(roomName));
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    
}
