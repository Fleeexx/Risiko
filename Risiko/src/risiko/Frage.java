/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Frage {
    
    private String kategorie;
    private int points;
    private int id;
    private String frage;
    private ArrayList<Antwort> antworten;
    
    public Frage(String kategorie, int points, int id) {
        this.kategorie = kategorie;
        this.points = points;
        this.id = id;
    }

    /**
     * @return the kategorie
     */
    public String getKategorie() {
        return kategorie;
    }

    /**
     * @param kategorie the kategorie to set
     */
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the frage
     */
    public String getFrage() {
        return frage;
    }

    /**
     * @param frage the frage to set
     */
    public void setFrage(String frage) {
        this.frage = frage;
    }

    /**
     * @return the antworten
     */
    public ArrayList<Antwort> getAntworten() {
        return antworten;
    }

    /**
     * @param antworten the antworten to set
     */
    public void setAntworten(ArrayList<Antwort> antworten) {
        this.antworten = antworten;
    }
    
    
    
}
