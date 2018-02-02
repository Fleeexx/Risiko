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
    
    private final String[] POINTS = new String[] {"100", "200", "300", "400", "500", "1000"};
    private final String[] FARBEN = new String[] {"_Black", "_Blue", "_Green", "_Orange"};
    public static final String[] KATEGORIEN = new String[] {"Computergrundlagen", "IT-Security", "Onlinegrundlagen", "Elektrotechnik"};
    private JLabel[][] b_main_points;
    private JLabel[] l_scoreboard_topten, b_question_question;
    private JLabel l_main_title, l_question_title, l_scoreboard_playerinfos, l_question_zurueck;
    private JButton b_newgame;
    private JPanel p_main, p_question, p_scoreboard;
    private Core core;
    private final int p_main_x = 980, height_frame = 700, p_scoreboard_x = 300;
    
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
        p_main.setBounds(0, 0, p_main_x, height_frame);
        this.add(p_main);
        p_main.setLayout(null);
        //Label setzen
        l_main_title = new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\images\\risiko.png"));
        l_main_title.setBounds(215, -10, 550, 200);
        p_main.add(l_main_title);
        b_newgame = new JButton("Neues Quiz");
        // TODO: Button Bounds definieren!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        b_newgame.setBounds();
        b_newgame.addMouseListener(this);
        initializeMainButtonPoints();
        p_main.setVisible(true);
    }
    
    public void initializePanelQuestion() {
        //Panel
        p_question = new JPanel();
        p_question.setBounds(0, 0, p_main_x, height_frame);
        this.add(p_question);
        p_question.setLayout(null);
        p_question.setVisible(false);
        //Label Frage
        l_question_title = new JLabel("Hallo...Dies ist eine Frage",JLabel.CENTER);
        l_question_title.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\Frage.png"));
        l_question_title.setHorizontalTextPosition(JLabel.CENTER);
        l_question_title.setBackground(Color.red);
        l_question_title.setOpaque(true);
        //l_question_title.setHorizontalAlignment(SwingConstants.CENTER);
        //l_question_title.setVerticalAlignment(SwingConstants.CENTER);
        l_question_title.setBounds(50, 110, 880, 50);
        p_question.add(l_question_title);
        //labelmitfragensetzen
        l_question_zurueck = new JLabel("", JLabel.CENTER);
        l_question_zurueck.addMouseListener(this);
        l_question_zurueck.setVisible(false);
        l_question_zurueck.setBackground(Color.red);
        l_question_zurueck.setOpaque(true);
        l_question_zurueck.setBounds(50, 550, 200, 50);
        l_question_zurueck.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\Zurueck.png"));
        p_question.add(l_question_zurueck);
        initialQuestionButton();
    }
    
    public void initializePanelScoreboard() {
        //Panel
        p_scoreboard = new JPanel();
        p_scoreboard.setBounds(p_main_x, 0, p_scoreboard_x, height_frame);
        this.add(p_scoreboard);
        p_scoreboard.setLayout(null);
        p_scoreboard.setVisible(true);
        //p_scoreboard.setBackground(Color.red);
        initialScoreboardInfos();
    }
    
    public void initialScoreboardInfos() {
        //l_scoreboard_playerinfos = new JLabel("<html>"+ core.getSpielerName() + "<br>" + core.getSpielerScore() + "</html>");
        l_scoreboard_playerinfos = new JLabel("<html>Felix<br>100</html>");
        // Bounds setzen!!!!!!!!!!!!!!!!!!!
        l_scoreboard_playerinfos.setBounds(50, 20, p_scoreboard_x - 80, 50);
        l_scoreboard_playerinfos.setHorizontalAlignment(JLabel.CENTER);
        p_scoreboard.add(l_scoreboard_playerinfos);
        l_scoreboard_topten = new JLabel[10];
        int startwertY = 100, verschiebungY = 40;
        int startwertX = 10; 
        for (int i = 0; i < l_scoreboard_topten.length; i++){
            if (i >= 10) {
                verschiebungY += 30;
            }
            l_scoreboard_topten[i] = new JLabel("-");
            l_scoreboard_topten[i].setBounds(startwertX, i * verschiebungY + startwertY, p_scoreboard_x - 20, 20);
            p_scoreboard.add(l_scoreboard_topten[i]);
        }
    }

    
    private void initialQuestionButton() {
        b_question_question = new JLabel[4];
        int x = 0, y = 0, abstandY = 120 + 30,abstandX = 415 + 50;
        int startWertX = 50, startWertY = 250;
        int i2 = 0;
        int stufeY = 0;
        for (int i = 0 ; i < b_question_question.length; i++) {              
            b_question_question[i] = new JLabel("num " + i);
            b_question_question[i].setBounds(i * abstandX + startWertX, stufeY * abstandY + startWertY, 415, 50);
            
            //Color
            b_question_question[i].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\Antwort.png"));
            b_question_question[i].setHorizontalTextPosition(JLabel.CENTER);
            b_question_question[i].addMouseListener(this);              
            p_question.add(b_question_question[i]);
            if (i >= 2){  
                stufeY++;
                b_question_question[i].setBounds( i2 * abstandX + startWertX,abstandY + startWertY, 415, 50);
                i2++;
            }     
        }
    }
    
    private void initializeFrameMain() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        setLayout(null);
        int w = p_main_x + p_scoreboard_x, h = height_frame;
        int x = (int) ((d.getWidth() - w) / 2);
        int y = (int) ((d.getHeight()- h) / 2);
        setBounds(x, y, w, h);
        UIManager.getLookAndFeelDefaults().put("Label.font", new FontUIResource(new Font("Segoe UI", Font.PLAIN, 20)));
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    private void initializeMainButtonPoints() {
        // initialisiert die Buttons um die Fragen aufzurufen
        b_main_points = new JLabel[4][7];
        int x = 0, y = 0, abstandY = 60,abstandX = 240;
        int startWertX = 10, startWertY = 230;
        int abstandUmBildCentrieren = (abstandX - 84) / 2;
        for (int i = 0 ; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (j > 0) {
                     //b_main_points[i][j] = new JLabel(points[j - 1]);
                    //
                    b_main_points[i][j] = new JLabel();
                    b_main_points[i][j].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\" + POINTS[j - 1] + FARBEN[i] + ".png"));
                    
                    b_main_points[i][j].addMouseListener(this);
                    b_main_points[i][j].setBounds(i * abstandX + (startWertX + abstandUmBildCentrieren), j * abstandY + startWertY, 80, 60);
                } else {
                    b_main_points[i][j] = new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\images\\" + KATEGORIEN[i] + ".png"));
                    b_main_points[i][j].setBounds(i * abstandX + startWertX, j * abstandY + startWertY - 55, 230, 122);
                }                   
                
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
        for(int i = 0; i < werte.length; i++){
            l_scoreboard_topten[i].setText(werte[i]);
        }
    }
       
    public void setScore(int spielerScore){
        l_scoreboard_playerinfos.setText("<html>Felix<br>" + spielerScore +"</html>");
    }
    
    public void resetButtonsMainPoints() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                b_main_points[i][j].addMouseListener(this);
            }
        }
    }
    
    public void getNewPlayerName(Boolean forced) {
        String name = null;
        do {
            name = JOptionPane.showInputDialog(this, "Spielername erforderlich.\nHier Ihren Spielernamen eingeben\num das Quiz durchzuführen", "Neues Quiz starten", JOptionPane.WARNING_MESSAGE);
            if (name != null && !name.equals("") && !name.equals(" ")) {
                forced = false;
            } else {
                JOptionPane.showMessageDialog(this, "Um ein neues Quiz zu starten müssen Sie einen gültigen Spielernamen eingeben,\ndamit wir Sie evtl. in unserer Bestenliste anzeigen können!", "Neues Quiz starten", JOptionPane.ERROR_MESSAGE);
            }
        } while (forced);
        core.createNeuenSpieler(name);
        resetButtonsMainPoints();
    }
            
    @Override
    public void mouseClicked(MouseEvent me) {
        if (b_newgame == me.getSource()) {
            // TODO: NEWGAME
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 6; j++) {
                    if (b_main_points[i][j] == me.getSource()) {
                        // wenn auf eine Frage geklickt wurde
                        p_main.setVisible(false);
                        p_question.setVisible(true);
                        b_main_points[i][j].removeMouseListener(this);
                        b_main_points[i][j].setVisible(false);
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                if (b_question_question[i] == me.getSource()) {
                    if (core.isSpielerAntwortRichtig(i)) {
                        core.addSpielerScore();
                        b_question_question[i].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\Richtig.png"));
                        b_question_question[i].setHorizontalTextPosition(JLabel.CENTER);
                        b_question_question[i].setText("");
                    } else {
                        b_question_question[i].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\Falsch.png"));
                        b_question_question[i].setHorizontalTextPosition(JLabel.CENTER);
                        b_question_question[i].setText("");
                    }
                    questionButtonRemoveMouseListener(true);
                    l_question_zurueck.setVisible(true);
                    core.refreshScoreboardInfo();
                    //p_main.setVisible(true);
                    //p_question.setVisible(false);
                }
            }
        }
        if(l_question_zurueck == me.getSource()){
            p_main.setVisible(true);
            p_question.setVisible(false);
            l_question_zurueck.setVisible(false);
            questionButtonRemoveMouseListener(false);
        }
    }
    
    public void questionButtonRemoveMouseListener(Boolean trueorfalse){
        for(int i = 0; i < b_question_question.length; i++){
            if(trueorfalse){
                b_question_question[i].removeMouseListener(this);
            } else {
                b_question_question[i].addMouseListener(this);
            }
            
        }
    }
    
    public void QuestionButtonRemoveIcon(){
        for(int i = 0; i < b_question_question.length; i++){
            b_question_question[i].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\Antwort.png"));
            b_question_question[i].setHorizontalTextPosition(JLabel.CENTER);
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
