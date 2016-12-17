/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.database_connection;

import chatapplication.database_wrapper.SQLWrapper;
import chatapplication.user.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class DatabaseManager {
    
    public Connection connection;
    public SQLWrapper wrapper;
    public User user;
    
    public DatabaseManager(String database, String username, String password) throws SQLException{
        connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+database+"?user="+username+"&password="
                +password);
        wrapper = new SQLWrapper();
    }
    
    public PreparedStatement Select(Object[] columns ,String database, String where) throws SQLException{
        return  (PreparedStatement) connection.prepareStatement(wrapper.select(columns)
                .from(database).where(where).getQuery());
    }
    
    public PreparedStatement Delete(String table, String where) throws SQLException{
        return (PreparedStatement) connection.prepareStatement(wrapper.delete(table)
                .where(where).getQuery());       
    }
    
    public PreparedStatement Insert(String table, Object[] values) throws SQLException{
        return (PreparedStatement)connection.prepareStatement(wrapper.insert(table)
                .values(values).getQuery());
    }
    
    public PreparedStatement Update(String table, String word, String value, String where) throws SQLException{
        return (PreparedStatement)connection.prepareStatement(wrapper.update(table)
                .set(word, value).where(where).getQuery());
    }
    public PreparedStatement Select(Object[] columns, String table) throws SQLException{
        return (PreparedStatement) connection.prepareStatement(wrapper.select(columns).from(table).getQuery());
    }
    public User getUser(){
        return this.user;
    }
}   

