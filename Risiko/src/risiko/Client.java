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

/**
 *
 * @author Max
 */
public class Client {
    
   private DBConnection dbConnection;
    
   
    public Client(){
        dbConnection = new DBConnection();
        dbConnection.startConnection("db/chinook.db");
    }
    
    public ArrayList<Antwort> getAntworten(int id){
        ResultSet rs = null;
        ArrayList<Antwort> antworten = new ArrayList();
        try {
            rs = dbConnection.executeSQLQuery("SELECT * FROM antwort WHERE f_id = '" + id + "';");
        } catch(Exception e) {
        }
            if (rs!=null) {
                try{
                    while (rs.next()) {
                        if(rs.getString("frage") != null){
                            antworten.add(new Antwort(rs.getString("frage"), rs.getBoolean("iscorrect")));   //besprechen wegen string
                        }    
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
                        if(rs.getString("frage") != null) {
                            frage = new Frage(rs.getString("kategorie"), rs.getInt("punkte"), rs.getInt("a_id"));
                        }
                    }
                    rs.getStatement().close();
                    rs.close();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return frage;
    }
    
    public void insertSpieler(Spieler spieler) {
        dbConnection.insert("INSERT INTO spieler (score, name) VALUES (" + spieler.getScore() + ", '" + spieler.getName() + "');");
    }
    
    public Spieler[] getTopZehn(String kategorie, int points) {
    Spieler[] spieler = new Spieler[10];
    ResultSet rs = null;
    try{
        rs = dbConnection.executeSQLQuery("SELECT * FROM spieler ORDER BY points DESC LIMIT 10;");
    }catch(Exception e){  
    }
        if (rs!=null){
            try{
                int i = 0;
                while (rs.next()) {
                    if(rs.getString("points") != null){
                        spieler[i] = new Spieler(rs.getString("name"), rs.getInt("points"));
                    }  
                    i++;
                }
                rs.getStatement().close();
                rs.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return spieler;
    }
    
}
