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
    private int gui_correctAnswer;
    private Random rnd;
    private Spieler gui_sp;
    private Frage gui_frage;
    
    public Core() {
        gui = new Gui(this);
        client = new Client();
        rnd = new Random();
        gui.setScoreboardValues(getTopTenSpieler());
        gui.getNewPlayerName(true);
        refreshScoreboardInfo();
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
                    gui_correctAnswer = r;
                }
            } else {
                i--;
            }
        }
        gui.setFrage(gui_frage.getFrage());
        gui.setAntworten(antworten);
    }
    
    public String[] getTopTenSpieler() {
        Spieler[] topten_sp = client.getTopZehn();
        String[] topten = new String[topten_sp.length];
        for (int i = 0; i < topten_sp.length; i++) {
            if (topten_sp[i] != null) {
                topten[i] = (i + 1) + ". " + topten_sp[i].getName() + "   "+ topten_sp[i].getScore();
            }
        }
        return topten;
    }
    
    public void refreshScoreboardInfo() {
        gui.setScore(gui_sp.getName(), gui_sp.getScore());
        gui.setScoreboardValues(getTopTenSpieler());
    }
    
    public Boolean isSpielerAntwortRichtig(int pos) {
        return (pos == gui_correctAnswer);
    }
    
    public void addSpielerScore() {
        gui_sp.addScore(gui_frage.getPoints());
        client.saveSpieler(gui_sp);
    }
    
    public void removeSpielerScore() {
        gui_sp.addScore((int) (gui_frage.getPoints() * -0.25));
        client.saveSpieler(gui_sp);
    }
    
    public String getSpielerName() {
        return gui_sp.getName();
    }
    
    public int getSpielerScore() {
        return gui_sp.getScore();
    }
    
    public int getCorrectAntwort() {
        return gui_correctAnswer;
    }
    
    
}
