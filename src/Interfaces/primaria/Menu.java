/*
 * Menu.java
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

import Interfaces.secondaria.Salva;
import Interfaces.secondaria.Rules;
import Interfaces.secondaria.Classifica;
import Interfaces.secondaria.About;
import Computer.Computer;
import Player.InsertName;
import Player.Player;
import Player.Tempo;
import computer.Semaforo;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.swing.*;
import memory.Main;
import static memory.Main.p;

/**
 *
 * @author FRANCESCO
 */
public class Menu extends JMenuBar {
    public static int mode; //modalità
    private Salva s;
//creazione del menu
    public Menu(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //menu e sottomenu
        JMenu file = new JMenu("File");
                JMenuItem new_game = new JMenuItem("Nuova Partita");
                    new_game.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
                JMenuItem load = new JMenuItem("Carica Partita");
                    load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
                JMenuItem restart = new JMenuItem("Ricomincia Partita");
                    restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
                JMenuItem save = new JMenuItem("Salva Partita");
                    save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
                JMenuItem exit = new JMenuItem("Esci");
            file.add(new_game);
            file.add(load);
            file.add(restart);
            file.add(save);
            file.add(exit);
                
        JMenu modifica = new JMenu("Modifica");

            JMenu resolution = new JMenu("Risoluzione");
                JMenuItem size1 = new JMenuItem("600x600");
                JMenuItem size2 = new JMenuItem("720x720");
                JMenuItem size3 = new JMenuItem("800x800");
                JMenuItem size4 = new JMenuItem("900x900");
                JMenuItem size5 = new JMenuItem("1024x1024");
            resolution.add(size1);
            resolution.add(size2);
            resolution.add(size3);
            resolution.add(size4);
            resolution.add(size5);

            JMenu modes = new JMenu("Modalità");
                JMenuItem solo = new JMenuItem("Giocatore Singolo");
                JMenuItem coop = new JMenuItem("Due Giocatori");
                JMenuItem computer = new JMenuItem("Computer");
            modes.add(solo);
            modes.add(coop);
            modes.add(computer);

            JMenu difficulty = new JMenu("Difficoltà");
                JMenuItem easy = new JMenuItem("Facile");
                JMenuItem medium = new JMenuItem("Medio");
                JMenuItem hard = new JMenuItem("Difficile");
            difficulty.add(easy);
            difficulty.add(medium);
            difficulty.add(hard);
            
            JMenu sound = new JMenu("Suoni");
                JMenuItem on = new JMenuItem("Accesi");
                JMenuItem off = new JMenuItem("Spenti");
            sound.add(on);
            sound.add(off);

            
        modifica.add(resolution);
        modifica.add(modes);
        modifica.add(difficulty);
        modifica.add(sound);

        JMenu visualizza = new JMenu("Visualizza");
            JMenuItem rules = new JMenuItem("Regole Gioco");
            JMenuItem classifica = new JMenuItem("Classifica");
        visualizza.add(rules);     
        visualizza.add(classifica);

        JMenu ask = new JMenu("?");
            JMenuItem about = new JMenuItem("About");
        ask.add(about);
            
        this.add(file);    
        this.add(modifica);
        this.add(visualizza);
        this.add(Box.createHorizontalGlue());
        ask.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        this.add(ask);
            
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(200, 20));

//eventi per ogni voce del menu        
        class risoluzione implements ActionListener, Serializable{
            private int x;
            private int y;
            
            public risoluzione(int x, int y){
                this.x = x;
                this.y = y;
            }
            
            public void actionPerformed (ActionEvent e){
                Main.frame.setSize(new Dimension(x, y));
                Main.frame.setLocation(dim.width/2-Main.frame.getSize().width/2, dim.height/2-Main.frame.getSize().height/2);
            }
        }
        
        class Save implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                if(mode == 0 || mode == 2){
                    JOptionPane.showMessageDialog(null, "Funzione disponibile solo in solitario", "ATTENZIONE", JOptionPane.DEFAULT_OPTION);
                }else{
                    s = new Salva();
                    s.createSalva();
                }
            }
        }
        
        class Load implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                s = new Salva();
                s.caricaSalva();
            }
        }
        
        class exit implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                Main.exit();
            }
        }
        
        class SinglePlayer implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                Main.frame.setVisible(false);
                Computer.setExit(true);
                Tempo.setExit(true);
                Tempo.setStop(true);
                mode = 1;
                Main.createGame(600, 4);
                p.getTPlayer1().setVisible(true);
                p.getPPlayer1().setVisible(true);
            }
        }
        
        class Difficulty implements ActionListener, Serializable{
            private int size;
            private int n_card;

            public Difficulty(int size, int n_card) {
                this.size = size;
                this.n_card = n_card;
            }
            
            public void actionPerformed (ActionEvent e){
                Main.frame.setVisible(false);
                Main.createGame(size, n_card);
            }
        }
        
        class NewGame implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                Main.frame.setVisible(false);
                Computer.setExit(true);
                Tempo.setExit(true);
                Tempo.setStop(true);
                mode = 1;
                Main.start();
            }
        }
        
        class Classifica_Panel implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                Classifica cl = new Classifica();
                    cl.createClassifica();
            }
        }
        
        class computer implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                mode = 0;
                Tempo.setExit(true);
                Tempo.setStop(true);
                Main.frame.setVisible(false);
                    Main.createGame(Game.size + 120, Game.n_card);
                p.getTPlayer1().setVisible(true);
                p.getPPlayer1().setVisible(true);
                p.getTPlayer2().setVisible(true);
                p.getPPlayer2().setVisible(true);
                mode = 0;
                Semaforo pieno = new Semaforo(0);
                Semaforo vuoto = new Semaforo(1);
                Player p = new Player(pieno, vuoto, "Player1");
                Computer c = new Computer(pieno, vuoto);
                    p.start();
                    c.start(); 
            }
        }
        
        class TwoPlayer implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                mode = 2;
                Computer.setExit(true);
                Tempo.setExit(true);
                Tempo.setStop(true);
                Main.frame.setVisible(false);
                    Main.createGame(Game.size + 120, Game.n_card);
                InsertName i = new InsertName();
                    i.setInsertName();
            }
        }
        
        class info implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                About b = new About();
            }
        }
        
        class regole implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                Rules r = new Rules();
            }
        }
        
        class suono implements ActionListener, Serializable{
            private boolean flag;
            
            public suono(boolean flag){ //suono acceso o spento (true o false)
                this.flag = flag;
            }
            
            public void actionPerformed (ActionEvent e){
                Game.suono = flag;
            }
        }

//assegnazione degli aventi alle variabili        
        new_game.addActionListener(new NewGame());
        load.addActionListener(new Load());
        restart.addActionListener(new SinglePlayer());
        save.addActionListener(new Save());
        exit.addActionListener(new exit());
        size1.addActionListener(new risoluzione(600, 600));
        size2.addActionListener(new risoluzione(720, 720));
        size3.addActionListener(new risoluzione(800, 800));
        size4.addActionListener(new risoluzione(900, 900));
        size5.addActionListener(new risoluzione(1024, 1024));
        solo.addActionListener(new SinglePlayer());
        easy.addActionListener(new SinglePlayer());
        medium.addActionListener(new Difficulty(600, 6));
        hard.addActionListener(new Difficulty(720, 8));
        on.addActionListener(new suono(false));
        off.addActionListener(new suono(true));
        computer.addActionListener(new computer());
        coop.addActionListener(new TwoPlayer());
        classifica.addActionListener(new Classifica_Panel());
        about.addActionListener(new info());
        rules.addActionListener(new regole());
    }   
}