/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Core {
    
    private Gui gui;
    private DBConnection dbconnection;
    
    public Core(Gui gui) {
        this.gui = gui;
        dbconnection = new DBConnection();
        dbconnection.startConnection("risiko.db");
    }
    
    public void getFrage(int points, int kategorie) {
        ResultSet rs = dbconnection.executeSQLQuery("SELECT * FROM frage WHERE points = " + (points + 100) + " AND kategorie = '" + Gui.KATEGORIEN[kategorie] + "';");
        Frage f = null;
        try {
            f = new Frage(rs.getString("kategorie"), rs.getInt("points"), rs.getInt("id"));
            rs.close();
        } catch (SQLException ex) {
            System.out.println("[FEHLER] Frage konnte nicht aus der Datenbank geholt werden!");
        }
        
        rs = dbconnection.executeSQLQuery("SELECT * FROM antworten as a, fragen as f WHERE a.f_id = f.f_id");
        try {
            
        } catch (SQLException ex) {
            System.out.println("[FEHLER] Antworten konnten nicht aus der Datenbank geholt werden!");
        }
        // Gui ansteuern, Fragen und Antworten mit Setter setzen
    }
    
    
}
