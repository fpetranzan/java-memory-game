/*
 * Game.java
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
import Computer.Computer;
import static Computer.Computer.memory;
import Font.Character;
import Player.Player;
import Player.Tempo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import static memory.Main.cl;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import memory.Main;
import static memory.Main.p;

/**
 *
 * @author FRANCESCO
 */
public class Game extends JPanel /*implements Serializable*/{
    public static ArrayList<Card> cards;    //array con tutte le carte
    public static Card card1;   //prima carta selezionata
    public static Card card2;   //seconda carta selezionata
    public static int n_card;  //numero di carte per lato
    public static int size;    //dimensione di un lato del frame
    public static int coppie;   //numero di coppie trovate
    public static boolean suono;    //suoni accesi o spenti
    private Card selected;
    private Timer time;
    private boolean player;

//pannello con le carte e la loro gestione    
    public Game(int size, int n_card, FileOutputStream file){
        this.size = size;
        this.n_card = n_card;
    
    //personalizzazione del pannello
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(size, size));
        this.setLayout(new GridLayout(n_card, n_card));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(41, 54, 83));       

        ArrayList<Card> cardsList = new ArrayList<Card>();
        java.util.List<Integer> cardVals = new ArrayList<Integer>();
    
    //creazione dei numeri per il numero di coppie possibili
        for (int i = 0; i < ((n_card * n_card) / 2); i++){
            cardVals.add(i);
            cardVals.add(i);
        }
    //mescola le carte
        Collections.shuffle(cardVals);
        
        int contatore = 0;
    
    //creazione di un bottone per ogni carta
        for (int val : cardVals){
            Card c = new Card();
        //attributi del bottone
            c.setValore(val);
            c.setBackground(Color.white);
            c.setFont(Character.getFont("Retro", 50));
            c.setBorder(new LineBorder(Color.black, 1));
            c.setText("?");
            c.setForeground(new Color(41, 54, 83));
            c.setPosizione(contatore);
            contatore++;
            c.setEnabled(false);
        
    //evento per girare le carte
        class gira implements ActionListener, Serializable{
            public void actionPerformed (ActionEvent e){
                    selected = c;
                    gira(selected); //richiamo metodo gira
            }
        }
        
        c.addActionListener(new gira());
    
        cardsList.add(c);
        }
        this.cards = cardsList;
        
        class controlla implements ActionListener, Serializable{
            public void actionPerformed(ActionEvent e){
                controlla();                
            }
        }
        
        time = new Timer(750, new controlla());
        time.setRepeats(false);
    
    //aggiunge le carte al pannello
        for (Card c : cards){
            this.add(c);
        }
    }

//metodo per girare le carte
    public void gira(Card selezione){
    //nel caso di una sola carta scelta
        if (card1 == null && card2 == null){
        //mostra il numero
            card1 = selezione;
            card1.setText(String.valueOf(card1.getValore()));
            card1.setForeground(new Color(110, 217, 181));
        }
    
    //nel caso di due carte scelte
        if (card1 != null && card1 != selezione && card2 == null){
        //mostra il numero
            card2 = selezione;
            card2.setText(String.valueOf(card2.getValore()));
            card2.setForeground(new Color(110, 217, 181));
        
        //controlla la modalità e a chi aspetta il turno
            //mode=0 : computer
            //mode=1 : singolo
            //mode=2 : due giocatori
            if(Menu.mode == 0){
                if(Thread.currentThread().getName().equals("Computer")){
                    player = false;
                }else{
                    player = true;
                }
            }
            if(Menu.mode == 1){
                player = true;
            }
            if(Menu.mode == 2){
                if(Player.name.equals("Player1")){
                    player = true;
                }else if(Player.name.equals("Player2")){
                    player = false;
                }
            }
            
            time.start();

        //rimuove un punto in caso di coppia sbagliata e punteggio maggiore di 0
            if(Menu.mode == 1){
                if(card1.getValore() != card2.getValore() && Integer.parseInt(p.getPPlayer1().getText()) > 0){
                    Main.punti = Integer.toString(Integer.parseInt(Main.punti) - 1);
                    p.getPPlayer1().setText(Main.punti);
                        //p.getPPlayer1().setText(Integer.toString(Integer.parseInt(p.getPPlayer1().getText()) - 1));
                }
            }else
            if(Menu.mode == 0 || Menu.mode == 2){
                if(player){
                    if(card1.getValore() != card2.getValore() && Integer.parseInt(p.getPPlayer1().getText()) > 0){
                        p.getPPlayer1().setText(Integer.toString(Integer.parseInt(p.getPPlayer1().getText()) - 1));
                    }
                }else
                if(card1.getValore() != card2.getValore() && Integer.parseInt(p.getPPlayer2().getText()) > 0){
                    p.getPPlayer2().setText(Integer.toString(Integer.parseInt(p.getPPlayer2().getText()) - 1));
                }
            }
        }
    }
    
    public void controlla() {
    //controllo se le carte selezionate sono uguali
        if(card1.getValore() == card2.getValore()){
        //riparte il timer    
            Tempo.setExit(true);
        //suondo coppia trovata    
        //incremento numero di coppie trovate    
            coppie++;
        //modifica carte trovate
            card1.setBackground(new Color(41, 54, 83));
            card1.setBorder(new LineBorder(Color.white, 1));
            card2.setBackground(new Color(41, 54, 83));
            card2.setBorder(new LineBorder(Color.white, 1));
            card1.setEnabled(false);
            card2.setEnabled(false);
            card1.setAccoppiata(true);
            card2.setAccoppiata(true);

            if(coppie != n_card * n_card /2){
                sound("matched", 0.4); //emette il suono
            }
        //aggiunge cinque punti
            if(Menu.mode == 1){
                Main.punti = Integer.toString(Integer.parseInt(Main.punti) + 5);
                p.getPPlayer1().setText(Main.punti);
                    //p.getPPlayer1().setText(Integer.toString(Integer.parseInt(p.getPPlayer1().getText()) + 5));
            }else if(Menu.mode == 0 || Menu.mode == 2){
                if(player){
                    p.getPPlayer1().setText(Integer.toString(Integer.parseInt(p.getPPlayer1().getText()) + 5));
                }else{
                    p.getPPlayer2().setText(Integer.toString(Integer.parseInt(p.getPPlayer2().getText()) + 5));
                }
            }
        
        //controlla se la partita è finita
            if (this.isGameWon()){
                Computer.setExit(true);
                
            //aggiunge la partita alla classifica
                String str = p.getTPlayer1().getText().substring(0, p.getTPlayer1().getText().length()-1);
                    if(n_card == 4){
                        str = str + "\t(facile):\t" + p.getPPlayer1().getText() + "\n";
                    }
                    if(n_card == 6){
                        str = str + "\t(medio):\t" + p.getPPlayer1().getText() + "\n";
                    }
                    if(n_card == 8){
                        str = str + "\t(difficile):\t" + p.getPPlayer1().getText() + "\n";
                    }

                    byte[] b_str = str.getBytes();
                    try {
                        cl.write(b_str);
                    } catch (IOException ex) {}
            
            //mostra un messaggio in caso di vittoria / sconfitta
                if(Menu.mode == 1){
                //suono vittoria
                    sound("win-lose", 2.0);
                    Tempo.setExit(true);
                    Tempo.setStop(true);
                    JOptionPane.showMessageDialog(this, "Hai vinto!", "WIN / LOSE", JOptionPane.DEFAULT_OPTION);
                }else{
                    if(Integer.parseInt(p.getPPlayer1().getText()) > Integer.parseInt(p.getPPlayer2().getText())){
                        if(Menu.mode == 2){
                            sound("win-lose", 1.0);
                            Tempo.setExit(true);
                            Tempo.setStop(true);
                            JOptionPane.showMessageDialog(this, p.getTPlayer1().getText() + " Hai vinto!", "WIN / LOSE", JOptionPane.DEFAULT_OPTION);
                        }else{
                            sound("win-lose", 1.0);
                            Tempo.setExit(true);
                            Tempo.setStop(true);
                            JOptionPane.showMessageDialog(this, "Hai vinto!", "WIN / LOSE", JOptionPane.DEFAULT_OPTION);
                        }
                    }else{
                        if(Menu.mode == 2){
                            sound("win-lose", 1.0);
                            Tempo.setExit(true);
                            Tempo.setStop(true);
                            JOptionPane.showMessageDialog(this, p.getTPlayer2().getText() + " Hai vinto!", "WIN / LOSE", JOptionPane.DEFAULT_OPTION);
                        }else{
                            sound("win-lose", 1.0);
                            Tempo.setExit(true);
                            Tempo.setStop(true);
                            JOptionPane.showMessageDialog(this,"Hai perso!", "WIN / LOSE", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }
            }
        }else{ //coppia sbagliata
            sound("error", 0.5); //suono errore
            Tempo.setExit(true);
        //in caso di modalità "conputer" aggiunge le carte alla memoria
            if(Menu.mode == 0 && player){
                memory.add(card1);
                memory.add(card2);
            }
        //rigira le carte nel caso in cui sono diverse
            card1.setText("?");
            card1.setForeground(new Color(41, 54, 83));
            card2.setText("?");
            card2.setForeground(new Color(41, 54, 83));
        }
    card1 = null;
    card2 = null;
    }

//controlla se il gioco è finito    
    public boolean isGameWon(){
    //controlla che siano state trovate tutte le coppie
        for(Card c: this.cards){
            if (c.getAccoppiata() == false){
                return false;
            }
        }
        return true;
    }
    
//metodo per eseguire i suoni
    private void sound(String name, double n){
        if(!suono){
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\images_sounds\\" + name + ".wav").getAbsoluteFile( ));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                Float dB = (float) (Math.log(n) / Math.log(10) * 20);
                volume.setValue(dB);
                    clip.start();
            }catch(Exception ex){}
        }
    }
}
