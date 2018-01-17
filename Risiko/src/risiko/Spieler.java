/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

/**
 *
 * @author Administrator
 */
public class Spieler {
    
    private String name;
    private int score;
    
    public Spieler(String name){
        this.name = name;
        score = 0;
    }
    public Spieler(String name, int score){
        this.name = name;
        this.score = score;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void addScore(int score) {
        this.score = score + this.score;
    }
    
    
}
