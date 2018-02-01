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
    private Frage gui_frage;
    
    public Core() {
        gui = new Gui(this);
        client = new Client();
        rnd = new Random();
        gui.getNewPlayerName(true);
    }
    
    // Spieler-Methoden
    //  client.insertSpieler --> Neuen Spieler erstellen
    //  client.saveSpieler --> den zuletzt angelegten Spieler speichern
    //  addSpielerScore --> Spieler Punkte der aktuellen Frage hinzufügen
    //  getSpielerScore --> ~
    //  getSpielerName --> ~
    
    
    
    public void createNeuenSpieler(String name) {
        gui_sp = new Spieler(name);
        client.insertSpieler(gui_sp);
    }
    
    public void giveSpielerNeueFrage(int points, int kategorie) {
        gui_frage = client.getFrage(Gui.KATEGORIEN[kategorie], points);
        ArrayList<Antwort> antworten_list = client.getAntworten(gui_frage.getId());
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
        gui.setFrage(gui_frage.getFrage());
        gui.setAntworten(antworten);
    }
    
    public Boolean isSpielerAntwortRichtig(int pos) {
        return (pos == gui_correctAnwser);
    }
    
    public void addSpielerScore() {
        gui_sp.addScore(gui_frage.getPoints());
    }
    
    public String getSpielerName() {
        return gui_sp.getName();
    }
    
    public int getSpielerScore() {
        return gui_sp.getScore();
    }
    
    public int getCorrectAntwort() {
        return gui_correctAnwser;
    }
    
    
}
