/*
 * InsertName.java
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

package Player;

import Computer.Computer;
import Font.Character;
import static Interfaces.primaria.Menu.mode;
import computer.Semaforo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import static memory.Main.icon;
import static memory.Main.p;

/**
 *
 * @author FRANCESCO
 */
public class InsertName implements Serializable{
    
    public static void setInsertName(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    //creazione e modifica dell'interfaccia
        JFrame gui_name = new JFrame("Memory Card - NAME");
            gui_name.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            gui_name.getContentPane().setBackground(new Color(41, 54, 83));
            gui_name.setSize(new Dimension(400, 200));
            gui_name.setLocation(dim.width/2-gui_name.getSize().width/2, dim.height/2-gui_name.getSize().height/2);
            gui_name.setLayout(null);
            gui_name.setResizable(false);
            gui_name.setIconImage(icon.getImage());
            
        JLabel name = new JLabel();
            name.setText("Player Name:");
            name.setFont(Character.getFont("Retro", 20));
            name.setLocation(30, 20);
            name.setForeground(Color.white);
            name.setSize(new Dimension(180, 30));
            
        JTextField insert_name = new JTextField();
            insert_name.setLocation(210, 10);
            insert_name.setOpaque(true);
            insert_name.setBorder(new LineBorder(new Color(41, 54, 83), 1));
            insert_name.setSize(new Dimension(150, 40));
        
        JButton login = new JButton();   
            login.setText("AVVIA");
            login.setFont(Character.getFont("Retro", 20));
            login.setLocation(240, 65);
            login.setBackground(new Color(110, 217, 181));
            login.setForeground(Color.white);
            login.setSize(new Dimension(120, 35));

        class login implements ActionListener{
            public void actionPerformed (ActionEvent e){
                if(insert_name.getText().equals("")){
                    p.getTPlayer2().setText("Player2:");
                    p.getTPlayer1().setVisible(true);
                    p.getPPlayer1().setVisible(true);
                    p.getTPlayer2().setVisible(true);
                    p.getPPlayer2().setVisible(true);
                }else{
                    p.getTPlayer2().setText(insert_name.getText() + ":");
                    p.getTPlayer1().setVisible(true);
                    p.getPPlayer1().setVisible(true);
                    p.getTPlayer2().setVisible(true);
                    p.getPPlayer2().setVisible(true);
                }
            gui_name.setVisible(false);
            startGame();
            }
        }
        login.addActionListener(new login());
        
        JButton exit = new JButton();   
            exit.setText("ANNULLA");
            exit.setFont(Character.getFont("Retro", 20));
            exit.setLocation(30, 65);
            exit.setBackground(new Color(241, 65, 65));
            exit.setForeground(Color.white);
            exit.setSize(new Dimension(150, 35));
        
        class esci implements ActionListener{
            public void actionPerformed (ActionEvent e){
                gui_name.setVisible(false);
            }
        }
        exit.addActionListener(new esci());    
            
        gui_name.add(exit);    
        gui_name.add(login);
        gui_name.add(insert_name);
        gui_name.add(name);
        gui_name.setVisible(true);
    }

//avvio dei thread    
    private static void startGame(){
        p.getPPlayer2().setText("0");
        mode = 2;
        Semaforo pieno = new Semaforo(0);
        Semaforo vuoto = new Semaforo(1);
        Player p1 = new Player(pieno, vuoto, "Player1");
        Player p2 = new Player(pieno, vuoto, "Player2");
        Computer.setExit(false);
            p1.start();
            p2.start();
    }
}
