/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Max
 */
public class Frage {
    
    private ArrayList<Antwort> antworten;
   
    public Frage(){
        
    }
    
    public ArrayList<Antwort> antwortenHolen(String abfrage){
        ResultSet rs = null;
        try{
            rs = executeSQLQuery(abfrage);
        }catch(Exception e){  
        }
            if (rs!=null){
                try{
                    while (rs.next()) {
                        antworten.add(new Antwort(rs.getString("antwort")), rs.getString("isItTrue"));
                    }
                    rs.getStatement().close();
                    rs.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        return antworten;
    } 
    
}
