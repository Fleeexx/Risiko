/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max
 */
public class Client {
    
   private DBConnection dbConnection;
    
   
    public Client(){
        dbConnection = new DBConnection();
        dbConnection.startConnection("risiko.db");
    }
    
    public ArrayList<Antwort> getAntworten(int id){
        ResultSet rs = null;
        ArrayList<Antwort> antworten = new ArrayList();
        try {
            rs = dbConnection.executeSQLQuery("SELECT * FROM antwort WHERE f_id = '" + id + "';");
        } catch(Exception e) {
        }
            if (rs != null) {
                try{
                    while (rs.next()) {
                        antworten.add(new Antwort(rs.getString("antwort"), rs.getBoolean("iscorrect")));
                    }
                    rs.getStatement().close();
                    rs.close();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return antworten;
    }
    
    public Frage getFrage(String kategorie, int points) {
        Frage frage = null;
        ResultSet rs = null;
        ArrayList<Antwort> antworten = new ArrayList();
        try {
            rs = dbConnection.executeSQLQuery("SELECT * FROM frage WHERE kategorie = '" + kategorie + "' AND points = '" + points + "';");
        } catch(Exception e) {  
        }
            if (rs!=null){
                try{
                    while (rs.next()) {
                        frage = new Frage(rs.getString("frage"), rs.getString("kategorie"), rs.getInt("points"), rs.getInt("f_id"));
                    }
                    rs.getStatement().close();
                    rs.close();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return frage;
    }
    
    public void saveSpieler(Spieler spieler) {
        ResultSet rs = dbConnection.executeSQLQuery("SELECT s_id as id FROM spieler ORDER BY s_id DESC LIMIT 1;");
        int id = -1;
       try {
           rs.next();
           id = rs.getInt("id");
       } catch (SQLException ex) {
       }
        System.out.println(id);
        dbConnection.insert("UPDATE spieler SET score = " + spieler.getScore() + " WHERE s_id = " + id + ";");
    }
    
    public void insertSpieler(Spieler spieler) {
        dbConnection.insert("INSERT INTO spieler (score, name) VALUES (" + spieler.getScore() + ", '" + spieler.getName() + "');");
    }
    
    public Spieler[] getTopZehn() {
    Spieler[] spieler = new Spieler[10];
    ResultSet rs = null;
    try {
        rs = dbConnection.executeSQLQuery("SELECT * FROM spieler ORDER BY score DESC LIMIT 10;");
    } catch(Exception e) {  
    }
        if (rs!=null) {
            try {
                int i = 0;
                while (rs.next()) {
                    spieler[i] = new Spieler(rs.getString("name"), rs.getInt("score"));
                    i++;
                }
                rs.getStatement().close();
                rs.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        return spieler;
    }
    
}
