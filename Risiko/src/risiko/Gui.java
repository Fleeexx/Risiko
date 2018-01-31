package risiko;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lukas
 */
public class Gui extends JFrame implements MouseListener {
    
    private final String[] POINTS = new String[] {"100", "200", "300", "400", "500", "600"};
    private final String[] FARBEN = new String[] {"_Black","_Blue","_Green","_Orange"};
    public static final String[] KATEGORIEN = new String[] {"ETL","GEIER","LARCHER","KEVIN"};
    private JLabel[][] b_main_points;
    private JLabel[] l_scoreboard_infos, b_question_question;
    private JLabel l_main_title, l_question_title;
    private JPanel p_main, p_question, p_scoreboard;
    private Core core;
    
    public Gui(Core core) {
        this.core = core;
        initializeFrameMain();
        initializePanelMain();
        initializePanelQuestion();
        initializePanelScoreboard();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Core core = new Core();
    }
    
    public void initializePanelMain() {
        //Panel
        p_main = new JPanel();
        p_main.setBounds(0, 0, 598, 600);
        this.add(p_main);
        p_main.setLayout(null);
        //Label setzen
        l_main_title = new JLabel();
        l_main_title.setBounds(20, 50, 200, 30);
        l_main_title.setText("Risiko");
        p_main.add(l_main_title);
        initializeMainButtonPoints();
        p_main.setVisible(true);
    }
    
    public void initializePanelQuestion() {
        //Panel
        p_question = new JPanel();
        p_question.setBounds(0, 0, 598, 600);
        this.add(p_question);
        p_question.setLayout(null);
        p_question.setVisible(false);
        //Label Frage
        l_question_title = new JLabel("-");
        l_question_title.setBounds(40, 50, 400, 30);
        p_question.add(l_question_title);
        //labelmitfragensetzen
        initialQuestionButton();
    }
    
    public void initializePanelScoreboard() {
        //Panel
        p_scoreboard = new JPanel();
        p_scoreboard.setBounds(598, 0, 200, 600);
        this.add(p_scoreboard);
        p_scoreboard.setLayout(null);
        p_scoreboard.setVisible(true);
        //p_scoreboard.setBackground(Color.red);
        initialScoreboardInfos();
    }
    
    public void initialScoreboardInfos() {
        l_scoreboard_infos = new JLabel[15];
        int startwertX = 30, verschiebungX = 20;
        for (int i = 0; i < l_scoreboard_infos.length; i++){
            if (i >= 10) {
                verschiebungX = 30;
            }
            l_scoreboard_infos[i] = new JLabel("hhhh" + i);
            l_scoreboard_infos[i].setBounds(10, i * verschiebungX + startwertX, 180, 20);
            p_scoreboard.add(l_scoreboard_infos[i]);
        }
    }
    
    private void initialQuestionButton() {
        b_question_question = new JLabel[4];
        /* TODO: Koordinaten der Points definieren */
        int x = 0, y = 0, abstandY = 50,abstandX = 1;
        int startWertX = 33, startWertY = 150;
        int i2 = 0;
        for (int i = 0 ; i < b_question_question.length; i++) {              
            b_question_question[i] = new JLabel("num " + i);
            b_question_question[i].setBounds(i * abstandX + startWertX, i * abstandY + startWertY, 84, 30);
            b_question_question[i].addMouseListener(this);
            b_question_question[i].setForeground(Color.red);               
            p_question.add(b_question_question[i]);
            if (i >= 2){
                abstandX = 150;                   
                b_question_question[i].setBounds(1 * abstandX + startWertX, i2 * abstandY + startWertY, 84, 30);
                i2 = 1;
            }     
        }
    }
    
    private void initializeFrameMain() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        setLayout(null);
        int w = 798, h = 600;
        int x = (int) ((d.getWidth() - w) / 2);
        int y = (int) ((d.getHeight()- h) / 2);
        setBounds(x, y, w, h);
        UIManager.getLookAndFeelDefaults().put("Label.font", new FontUIResource(new Font("Segoe UI", Font.PLAIN, 20)));
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    private void initializeMainButtonPoints() {
        // initialisiert die Buttons um die Fragen aufzurufen
        b_main_points = new JLabel[4][7];
        /* TODO: Koordinaten der Points definieren */
        int x = 0, y = 0, abstandY = 60,abstandX = 84+66;
        int startWertX = 33, startWertY = 180;
        for (int i = 0 ; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (j > 0) {
                     //b_main_points[i][j] = new JLabel(points[j - 1]);
                    //
                    b_main_points[i][j] = new JLabel();
                    b_main_points[i][j].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\" + POINTS[j - 1] + FARBEN[i] + ".png"));
                    b_main_points[i][j].addMouseListener(this);
                } else {
                    b_main_points[i][j] = new JLabel(KATEGORIEN[i]);
                }                   
                b_main_points[i][j].setBounds(i * abstandX + startWertX, j * abstandY + startWertY, 80, 60);
                b_main_points[i][j].setForeground(Color.red);               
                p_main.add(b_main_points[i][j]);
            }
        }
    }
    
    public void setFrage(String frage) {
        l_question_title.setText(frage);
    }
    
    public void setAntworten(String[] antworten) {
        for (int i = 0; i < b_question_question.length; i++) {
            b_question_question[i].setText(antworten[i]);
        }
    }
    
    public void setBackgroundColorYellow(int angecklickterButton) {
        b_question_question[angecklickterButton ].setBackground(Color.yellow);
    }
    
    public void setBackgroundColorGreen(int angecklickterButton) { 
        b_question_question[angecklickterButton].setBackground(Color.green);
    }
    
    public void setBackgroundColorRed(int angecklickterButton) {
        b_question_question[angecklickterButton].setBackground(Color.red);
    }
    
    public void setScoreboardValues(String[] werte) {
        for(int i = 0; i < l_scoreboard_infos.length; i++){
            l_scoreboard_infos[i].setText(werte[i]);
        }
    }
            
    @Override
    public void mouseClicked(MouseEvent me) {
        Object o = me.getSource();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (b_main_points[i][j] == me.getSource()) {
                    // wenn auf eine Frage geklickt wurde
                    p_main.setVisible(false);
                    p_question.setVisible(true);
                    b_main_points[i][j].removeMouseListener(this);
                }
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if(b_question_question[i] == me.getSource()) {
                if (core.isSpielerAntwortRichtig(i)) {
                    
                }
                p_main.setVisible(true);
                p_question.setVisible(false);
            }
        }
    }

    

    // Useless sh**
    
    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    
    
}
