/*
 * About.java
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

package Interfaces.secondaria;

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
public class About implements Serializable{
    
    public About(){
    //informazioni sul gioco
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Memory Card - ABOUT");
        JPanel panel = new JPanel();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(new Dimension(450, 350));
            frame.setResizable(false);
            panel.setBackground(new Color(110, 217, 181));
            
        JLabel title = new JLabel();
            title.setText("INFORMAZIONI SUL GIOCO");
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(Character.getFont("Retro", 20));
            title.setForeground(Color.white);
            
        JLabel programmer = new JLabel();
            programmer.setText("gioco realizzato da Francesco Petranzan.");
            programmer.setHorizontalAlignment(JLabel.CENTER);
            programmer.setFont(Character.getFont("Retro", 14));
            programmer.setForeground(Color.white);
            
        JPanel panel2 = new JPanel();    
            panel2.setBackground(new Color(41, 54, 83));
            
        String testo = ("<html>Progetto interdisciplinare<br/>tra le materie: "
                       + "<br/>Informatica e Tecnologie e <br/>Progettazione di Sistemi <br/>Informatici di Telecomunicazione <br/>(TPSI), "
                       + "per la creazione <br/>del gioco di carte: ”Memory”. "
                       + "<br/>Sviluppato in ambiente Java, <br/>con interfaccia grafica.");
        
            JLabel text = new JLabel("<html><div style='text-align: center;'>" + testo + "</div></html>");
            text.setFont(Character.getFont("Retro", 18));
            text.setForeground(Color.white);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            
        panel.add(title);
        panel.add(programmer);
        panel2.add(text);
            panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
            panel2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        frame.setIconImage(Main.icon.getImage());
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(panel2, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
