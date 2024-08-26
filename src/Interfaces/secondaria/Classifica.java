/*
 * Classifica.java
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
import Player.Profile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.*;
import java.util.*;
import javax.swing.*;
import memory.Main;

/**
 *
 * @author FRANCESCO
 */
public class Classifica implements Serializable{
    private JFrame frame;
    private JPanel panel;
    private JPanel panel2;
    private ArrayList<Profile> players_profile; //informazioni partite salvate
    
    public void createClassifica(){
    //richiamo dei metodi e creazione della classifica
        createInterface();
        
        setProfile();

        setClassifica();
        
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(panel2, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private void createInterface(){
    //crea l'interfaccia della classifica
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                
        frame = new JFrame("Memory - RANKING");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(new Dimension(380, 550));
            frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
            frame.setIconImage(Main.icon.getImage());
            
        panel = new JPanel();
            panel.setLayout(new GridLayout(2, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            panel.setBackground(new Color(110, 217, 181));
            
        JLabel cls = new JLabel();
            cls.setPreferredSize(new Dimension(20, 0));
            cls.setText("CLASSIFICA PUNTEGGI:");
            cls.setFont(Character.getFont("Retro", 20));
            cls.setHorizontalAlignment(JLabel.CENTER);
            cls.setForeground(Color.white);
            panel.add(cls);
                    
        JLabel format = new JLabel();
            format.setText("PLAYER (MODALITÀ): PUNTEGGIO");
            format.setFont(Character.getFont("Retro", 12));
            format.setHorizontalAlignment(JLabel.CENTER);
            format.setForeground(Color.white);
            panel.add(format);
    }
    
    private void setProfile(){
    //legge il file e crea tutti i profili già presenti nell file
        File cl = new File("classifica.txt");     
        int c = 0;
        FileReader fr = null;
        
        try{
            fr = new FileReader(cl);
        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }
        players_profile = new ArrayList<Profile>();
        String name = "";
        String modalita = "";
        String punti = "";
        //salva nelle varibili nome, modalità e punti
        try {
            while((c=fr.read())!= -1){
                name += (char) c;
                while((c=fr.read())!= 9){      
                    name += (char) c;
                }

                while((c=fr.read())!= 58){      
                    modalita += (char) c;
                }
                fr.read();

                while((c=fr.read())!= 10){      
                    punti += (char) c;
                }
            //salva i dati
                Profile player_profile = new Profile(name, modalita, Integer.parseInt(punti));
            name = "";
            modalita = "";
            punti = "";
            //aggiunge i dati a un array
            players_profile.add(player_profile);
            }
        } catch (IOException ex) {}
        
        try { 
            fr.close();
        } catch (IOException ex) {}
        
        int num = players_profile.size();
        //InsertionSort per creare la classifica in ordine decrescente
        for (int i = 1; i < num; ++i) {
            int p = players_profile.get(i).getPoint();
            String n = players_profile.get(i).getName();
            String m = players_profile.get(i).getMode();
            
            int j = i - 1; 

            while (j >= 0 && players_profile.get(j).getPoint() < p) { 
                players_profile.get(j + 1).setPoint(players_profile.get(j).getPoint());
                players_profile.get(j + 1).setName(players_profile.get(j).getName());
                players_profile.get(j + 1).setMode(players_profile.get(j).getMode());
                j = j - 1; 
            } 
            players_profile.get(j + 1).setPoint(p);
            players_profile.get(j + 1).setName(n);
            players_profile.get(j + 1).setMode(m); 
        }
    }
    
    private void setClassifica(){
    //aggiunge all'interfaccia la classifica: nome, modalità e punteggio dei primi dieci
        int j = 0;
        panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
        panel2.setBackground(new Color(41, 54, 83));
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for(int i = 0; i < (players_profile.size()>=10 ? 10 : players_profile.size()); i++){
            JLabel pos = new JLabel((i+1) + ".  ");
                pos.setFont(Character.getFont("Retro", 16));
                pos.setHorizontalAlignment(JLabel.CENTER);
                pos.setForeground(Color.white);
            JLabel testo = new JLabel(players_profile.get(0).getName() + " " + players_profile.get(0).getMode() + ": " + players_profile.get(0).getPoint());
                testo.setFont(Character.getFont("Retro", 16));
                testo.setHorizontalAlignment(JLabel.CENTER);
                testo.setForeground(Color.white);
            
            c.weighty = 0.01;
            panel2.add(pos, c);
            panel2.add(testo, c);
            j++;
            c.gridy = j;
        }
    }
}