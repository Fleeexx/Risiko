/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Core {
    
    private Gui gui;
    private Client client;
    private int gui_correctAnwser;
    private Random rnd;
    private Spieler gui_sp;
    
    public Core() {
        gui = new Gui(this);
        client = new Client();
        rnd = new Random();
    }
    
    // Spieler-Methoden
    //  insertSpieler --> Neuen Spieler erstellen
    //  saveSpieler --> den zuletzt angelegten Spieler speichern
    
    
    
    public void createNeuenSpieler(String name) {
        gui_sp = new Spieler(name);
        client.insertSpieler(gui_sp);
    }
    
    public void giveSpielerNeueFrage(int points, int kategorie) {
        Frage frage = client.getFrage(Gui.KATEGORIEN[kategorie], points);
        ArrayList<Antwort> antworten_list = client.getAntworten(frage.getId());
        String[] antworten = new String[4];
        for (int i = 0; i < 4; i++) {
            int r = rnd.nextInt(4);
            // TODO
            if (antworten[r] == null) {
                antworten[r] = antworten_list.get(i).getAnswer();
                if (antworten_list.get(i).isAnswerCorrect()) {
                    gui_correctAnwser = r;
                }
            } else {
                i--;
            }
        }
        gui.setFrage(frage.getFrage());
        gui.setAntworten(antworten);
    }
    
    public Boolean isSpielerAntwortRichtig(int pos) {
        return (pos == gui_correctAnwser);
    }
    
    
    
    
}
