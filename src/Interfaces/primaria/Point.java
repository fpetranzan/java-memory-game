/*
 * Point.java
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

package Interfaces.primaria;

import Card.Card;
import Font.Character;
import static Interfaces.primaria.Game.cards;
import Player.Tempo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import memory.Main;
/**
 *
 * @author FRANCESCO
 */
public class Point extends JPanel implements Serializable{
    private JLabel PPlayer1;
    private JLabel TPlayer1;
    private JLabel PPlayer2;
    private JLabel TPlayer2;
    public static JLabel timer;
    
//pannello per i punti    
    public Point(){
        this.setOpaque(true);
        this.setBackground(new Color(110, 217, 181));
        this.setPreferredSize(new Dimension(0, 30));     
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 2));
                
        JLabel TPunti = new JLabel();
            TPunti.setText("PUNTI");            
            TPunti.setFont(Character.getFont("Retro", 20));
            TPunti.setForeground(Color.white);
        this.add(TPunti);

//nome del primo giocatore
        TPlayer1 = new JLabel();
            TPlayer1.setText(Main.nome);
            TPlayer1.setFont(Character.getFont("Retro", 20));
            TPlayer1.setForeground(Color.white);
            TPlayer1.setVisible(true);
        this.add(TPlayer1);

//punti del primo giocatore        
        PPlayer1 = new JLabel();
            PPlayer1.setText(Main.punti);
            PPlayer1.setFont(Character.getFont("Retro", 20));
            PPlayer1.setForeground(Color.white);
            PPlayer1.setVisible(true);
        this.add(PPlayer1);

//nome del secondo giocatore        
        TPlayer2 = new JLabel("Computer:");
            TPlayer2.setFont(Character.getFont("Retro", 20));
            TPlayer2.setForeground(Color.white);
            TPlayer2.setVisible(false);
            this.add(TPlayer2);

//punti del secondo giocatore        
        PPlayer2 = new JLabel("0");
            PPlayer2.setFont(Character.getFont("Retro", 20));
            PPlayer2.setForeground(Color.white);
            PPlayer2.setVisible(false);
            this.add(PPlayer2);
        
        try {//tempo per eseguire la mossa (10sec)
            BufferedImage timage = ImageIO.read(new File("src\\images_sounds\\" + Tempo.time + ".png"));
                timer = new JLabel(new ImageIcon(timage));
                this.add(timer);
        } catch (IOException ex) {}
            
        class start implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                Tempo t = new Tempo();
                    t.start();  //avvia il tempo
                    
                for (Card c : cards){
                    if(c.getAccoppiata() == false){
                        c.setEnabled(true);
                    }
                }
            }
        }   
        
        JButton b = new JButton("START");
            b.setFont(Character.getFont("Retro", 13));
            b.setForeground(Color.black);
            b.setVisible(true);
            b.addActionListener(new start());
            this.add(b);
    }

    //metodi setter
    public void setPPlayer1(JLabel PPlayer1) {
        this.PPlayer1 = PPlayer1;
    }

    public void setTPlayer1(JLabel TPlayer1) {
        this.TPlayer1 = TPlayer1;
    }

    public void setPPlayer2(JLabel PPlayer2) {
        this.PPlayer2 = PPlayer2;
    }

    public void setTPlayer2(JLabel TPlayer2) {
        this.TPlayer2 = TPlayer2;
    }

//metodi getter
    public JLabel getPPlayer1() {
        return PPlayer1;
    }

    public JLabel getTPlayer1() {
        return TPlayer1;
    }

    public JLabel getPPlayer2() {
        return PPlayer2;
    }

    public JLabel getTPlayer2() {
        return TPlayer2;
    }
    
    
}