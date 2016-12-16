/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.database_wrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jimmy
 */
public class SQLWrapper implements ISQLWrapper{
    
    private StringBuilder query;
    
    public SQLWrapper select(Object[] columns) {
        query = new StringBuilder();
        query.append("SELECT ");
        if(columns != null){
            for(Object column : columns){
                query.append(column);
                query.append(", ");
            }
            query.deleteCharAt(query.lastIndexOf(","));
        }else{
            query.append("*");
        }       
        return this;
    }

    @Override
    public SQLWrapper delete(String table) {
        query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(table);
        return this;
    }

    @Override
    public SQLWrapper where(String charSequence) {
        query.append(" WHERE ");
        query.append(charSequence);
        return this;
    }
    
    @Override
    public SQLWrapper from(String table){
        query.append(" FROM ");
        query.append(table);
        return this;
    }
    
    @Override
    public SQLWrapper update(String table) {
        query = new StringBuilder();
        query.append("UPDATE ");
        query.append(table);
        return this;
    }

    @Override
    public SQLWrapper set(Object[] words) {
        if(words.length == 0){
            try {
                throw new Exception("words length is 0");
            } catch (Exception ex) {
                Logger.getLogger(SQLWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.append(" SET ");
        for(Object word:words){
            query.append(word);
            query.append("=");
            query.append("?");
            query.append(", ");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        return this;
    }
    
    @Override
    public SQLWrapper set(String word){
        query.append(" SET ");
        query.append(word);
        query.append("=");
        query.append("?");
        return this;
    }
    
    @Override
    public SQLWrapper set(String word, String newValue){
        query.append(" SET ");
        query.append(word);
        query.append("=");
        query.append(newValue);
        return this;
    }

    @Override
    public SQLWrapper insert(String table) {
        query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(table);
        query.append(" (id, username, password, mail)");
        return this;
    }

    @Override
    public SQLWrapper values(Object[] values) {
        query.append(" VALUES");
        query.append("(");
        for(Object value:values){
            if(value instanceof String){
                query.append("'");
                query.append(value);
                query.append("'");
            }else{
                query.append(value);
            }
            query.append(", ");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        query.deleteCharAt(query.lastIndexOf(" "));
        query.append(");");
        return this;
    }

    @Override
    public String getQuery() {
        return query.toString();
    }
    
}
