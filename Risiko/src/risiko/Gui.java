package risiko;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

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
    
    private final String[] points = new String[] {"100", "200", "300", "400", "500", "600"};
    private JLabel[][] buttons;
    private JLabel title, highscore;
    private JPanel p_main;
    
    public Gui() {
    
        initializeFrame();
        initializeButtonArray();
        
    }
    
    private void initializeFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        /* TODO: Höhe, Breite des Frames definieren */
        int w = 0, h = 0;
        int x = (int) ((d.getWidth() - w) / 2);
        int y = (int) ((d.getHeight()- h) / 2);
        setBounds(x, y, w, h);
    }
    
    private void initializeButtonArray() {
        // initialisiert die Buttons um die Fragen aufzurufen
        
        /* TODO: Koordinaten der Points definieren */
        int x = 0, y = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new JLabel(points[j]);
                buttons[i][j].setBounds(0, 0, 0, 0);
                buttons[i][j].addMouseListener(this);
                p_main.add(buttons[i][j]);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Object o = me.getSource();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (buttons[i][j] == o) {
                    // wenn auf eine Frage geklickt wurde
                    
                    
                    
                    j = 6;
                    i = 4;
                }
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
