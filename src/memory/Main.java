/*
 * Main.java
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

package memory;

import Font.Character;
import Interfaces.primaria.Game;
import Interfaces.primaria.Menu;
import Interfaces.primaria.Point;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author FRANCESCO
 */
public class Main extends JFrame implements Serializable{
    public static String nome;
    public static String punti;
    public static FileOutputStream cl;  //file classifica
    public static JFrame frame;
    public static JButton login;
    public static Game g;
    public static ImageIcon icon = new ImageIcon("src\\images_sounds\\Icon-Logo.png");
    public static Point p;
    public static Menu m;
    
    public static void main(String[] args) {
        start();
    }

//inserimento nome giocatore    
    public static void start() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        createGame(600, 4);
    //creazione interfaccia
        JFrame gui_name = new JFrame("Memory Card - NAME");
            gui_name.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui_name.getContentPane().setBackground(new Color(41, 54, 83));
            gui_name.setSize(new Dimension(400, 200));
            gui_name.setLocation(dim.width/2-gui_name.getSize().width/2, dim.height/2-gui_name.getSize().height/2);
            gui_name.setLayout(null);
            gui_name.setResizable(false);
            gui_name.setIconImage(icon.getImage());
  
        JLabel memory = new JLabel();
            memory.setText("MEMORY");
            memory.setFont(Character.getFont("Retro", 36)); //richiamo classe per impostare il font
            memory.setLocation(100, 10);
            memory.setForeground(Color.white);
            memory.setSize(new Dimension(180, 30));    
            
        JLabel name = new JLabel();
            name.setText("Player Name:");
            name.setFont(Character.getFont("Retro", 20));
            name.setLocation(20, 55);
            name.setForeground(Color.white);
            name.setSize(new Dimension(180, 30));
            
        JTextField insert_name = new JTextField();
            insert_name.setLocation(210, 50);
            insert_name.setOpaque(true);
            insert_name.setBorder(new LineBorder(new Color(41, 54, 83), 1));
            insert_name.setSize(new Dimension(150, 40));
            insert_name.setFont(Character.getFont("Classic", 18));
            
        login = new JButton();   
            login.setText("AVVIA");
            login.setFont(Character.getFont("Retro", 20));
            login.setLocation(240, 105);
            login.setBackground(new Color(110, 217, 181));
            login.setForeground(Color.white);
            login.setSize(new Dimension(120, 35));
    //evento pulsante AVVIA, per inserire il nome
        class login implements ActionListener{
            public void actionPerformed (ActionEvent e){
                if(insert_name.getText().equals("")){
                    p.getTPlayer1().setText("Player1:");
                    nome = "Player1:";
                    p.getTPlayer1().setVisible(true);
                    p.getPPlayer1().setText("0");
                    punti = "0";
                    p.getPPlayer1().setVisible(true);
                }else{
                    p.getTPlayer1().setText(insert_name.getText() + ":");
                    nome = insert_name.getText() + ":";
                    p.getTPlayer1().setVisible(true);
                    p.getPPlayer1().setText("0");
                    punti = "0";
                    p.getPPlayer1().setVisible(true);
                }
            gui_name.setVisible(false);
        //creazione file classifica
            File classifica = new File("classifica.txt");
                try {
                    cl = new FileOutputStream(classifica, true);
                } catch (FileNotFoundException ex) {}
            }
        }
        login.addActionListener(new login());
        insert_name.addKeyListener(new Event.KeyEvent()); //richiamo classe per avvio con "ENTER"
        
        JButton exit = new JButton();   
            exit.setText("ESCI");
            exit.setFont(Character.getFont("Retro", 20));
            exit.setLocation(20, 105);
            exit.setBackground(new Color(241, 65, 65));
            exit.setForeground(Color.white);
            exit.setSize(new Dimension(120, 35));
        //Evento bottone per uscire
        class esci implements ActionListener{
            public void actionPerformed (ActionEvent e){
                Main.exit(); //richiamo metodo per uscire
            }
        }
        exit.addActionListener(new esci());    

        gui_name.add(memory);
        gui_name.add(exit);    
        gui_name.add(login);
        gui_name.add(insert_name);
        gui_name.add(name);
        gui_name.setVisible(true);
    }

//creazione dell'interfaccia di gioco    
    public static void createGame(int size, int n_card){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //dimensione schermo
        frame = new JFrame("Memory Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(size, size));
        frame.setIconImage(icon.getImage());
        frame.setJMenuBar(new Menu());
        m = new Menu();
        p = new Point();
        g = new Game(size, n_card, cl);
        frame.getContentPane().add(m);
        frame.getContentPane().add(p, BorderLayout.NORTH);
        frame.getContentPane().add(g, BorderLayout.CENTER);
        /*if(Menu.mode == 0 || Menu.mode == 2){
            g = new Game(size, n_card, cl);
            frame.getContentPane().add(g, BorderLayout.CENTER);
        }else{
            frame.getContentPane().add(new Game(size, n_card, cl), BorderLayout.CENTER);
        }*/
        frame.pack();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); //centrale la finestra sullo schermo
        frame.setVisible(true);
        Menu.mode = 1;
    }
    
    public static void enter(){ //metodo per avvio con "ENTER"  
        login.doClick();
    }
    
    public static void exit(){ //metodo per uscire dal programma
    //uscire dal programma
        System.exit(0);
    }     
}