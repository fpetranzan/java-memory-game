/*
 * Rules.java
 *
 * Created on 3-giu-2020, 12.26.14
 *
 * Copyright(c) {2020} XYZ Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of XYZ Company.
 *
 */

/*
 *  Project: Memory
 *  Created by FRANCESCO
 */

package Interfaces.secondary_interfaces;

import Font.Character;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import memory.Main;

/**
 *
 * @author FRANCESCO
 */
public class Rules implements Serializable{

//regole di gioco    
    public Rules(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Memory Card - ABOUT");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(new Dimension(500, 550));
            frame.setResizable(false);
            frame.setIconImage(Main.icon.getImage());
            
        JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(110, 217, 181));
            
        JLabel title = new JLabel();
            title.setText("REGOLE DEL GIOCO");
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(Character.getFont("Retro", 20));
            title.setForeground(Color.white);
        
        JPanel panel2 = new JPanel();
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
            panel2.setLayout(new BorderLayout());
            panel2.setBackground(new Color(41, 54, 83));
            
        String testo = ("<html>Vengono disporre tutte le carte coperte sul tavolo." +
                         "<br/>A turno ciascun giocatore gira due carte facendole vedere anche agli " +
                         "avversari. <br/>Lo scopo è quello di abbinare due carte uguali." +
                         "<br/>Se le due carte girate costituiscono una coppia uguale il " +
                         "giocatore guadagna punti e procede scegliendo altre due carte, finché non sbaglia; " +
                         "se le due tessere non rappresentano una coppia uguale allora le" +
                         "carte si rigirano e tocca al giocatore successivo." +
                         "<br/>Il gioco termina quando non ci sono più carte sul tavolo: vince chi ha " +
                         "guadagnato più punti.");
        JLabel text = new JLabel("<html><div style='text-align: center;'>" + testo + "</div></html>");
            text.setFont(Character.getFont("Retro", 18));
            text.setForeground(Color.white);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            
        panel.add(title);
        panel2.add(text, JLabel.CENTER);
        
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(panel2, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
