/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

/**
 *
 * @author Max
 */
public class Antwort {
    private String answer;
    private boolean isAnswerCorrect;
    
    private Antwort(String answer, boolean isAnswerCorrect){
        this.answer = answer;
        this.isAnswerCorrect = isAnswerCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getIsAnswerCorrect() {
        return isAnswerCorrect;
    }

    public void setIsAnswerCorrect(boolean isAnswerCorrect) {
        this.isAnswerCorrect = isAnswerCorrect;
    }
    
}
