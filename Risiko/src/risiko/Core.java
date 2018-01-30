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
    private int label_correctAnwser;
    private Random rnd;
    
    public Core(Gui gui) {
        this.gui = gui;
        client = new Client();
        rnd = new Random();
    }
    
    public void givePlayerNewQuestion(int points, int kategorie) {
        Frage frage = client.getFrage(Gui.KATEGORIEN[kategorie], points);
        ArrayList<Antwort> antworten_list = client.getAntworten(frage.getId());
        String[] antworten = new String[4];
        for (int i = 0; i < 4; i++) {
            int r = rnd.nextInt(4);
            // TODO
            if (antworten[r] == null) {
                antworten[r] = antworten_list.get(i).getAnswer();
            } else {
                i--;
            }
        }
        gui.setAntworten(antworten);
        // Gui ansteuern, Fragen und Antworten mit Setter setzen
        //setFrage
        //setAntworten (String[])
    }
    
    
}
