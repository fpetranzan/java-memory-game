/*
 * Salva.java
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

import Card.Card;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import Computer.Computer;
import Font.Character;
import static Interfaces.primary_interfaces.Game.cards;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.border.LineBorder;
import memory.Main;

/**
 *
 * @author FRANCESCO
 */
public class Salva {
    private static JButton load;
    private static JButton login;

//metodo per creare un salvataggio    
    public static void createSalva(){
    //creazione e modifica dell'interfaccia
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame gui_save = new JFrame("Memory Card - SAVE");
            gui_save.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            gui_save.getContentPane().setBackground(new Color(41, 54, 83));
            gui_save.setSize(new Dimension(450, 140));
            gui_save.setLocation(dim.width/2-gui_save.getSize().width/2, dim.height/2-gui_save.getSize().height/2);
            gui_save.setLayout(null);
            gui_save.setResizable(false);
            gui_save.setIconImage(Main.icon.getImage());
            
        JLabel name = new JLabel();
            name.setText("Nome Salvataggio:");
            name.setFont(Character.getFont("Retro", 20));
            name.setLocation(20, 15);
            name.setForeground(Color.white);
            name.setSize(new Dimension(245, 30));

        JTextField insert_nameSave = new JTextField();
            insert_nameSave.setLocation(275, 10);
            insert_nameSave.setBorder(new LineBorder(new Color(41, 54, 83), 1));
            insert_nameSave.setSize(new Dimension(150, 40));
            insert_nameSave.setFont(Character.getFont("Classic", 18));

        login = new JButton();   
            login.setText("SALVA");
            login.setFont(Character.getFont("Retro", 20));
            login.setLocation(305, 65);
            login.setBackground(new Color(110, 217, 181));
            login.setForeground(Color.white);
            login.setSize(new Dimension(120, 35));

    //evento per salvare la partita
        class login implements ActionListener{
            public void actionPerformed (ActionEvent e){
            File save = new File("src\\saves\\" + insert_nameSave.getText());
                if(save.exists()){
                    gui_save.setVisible(false);
                    int scelta = JOptionPane.showConfirmDialog(gui_save,"Salvataggio già esistente... Sovrascriverlo?","ATTENZIONE!",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                    switch(scelta){
                        case 0:
                            try {
                                ObjectOutputStream sw = new ObjectOutputStream(new FileOutputStream(save));
                                sw.writeObject(Main.frame);
                                sw.close();
                            } catch (IOException ex) {}
                            gui_save.setVisible(false);
                        break;
                        case 1:
                            gui_save.setVisible(true);
                        break;
                    }
                }else{
                    try {
                        ObjectOutputStream sw = new ObjectOutputStream(new FileOutputStream(save));
                        sw.writeObject(Main.frame);
                        sw.close();
                    } catch (IOException ex) {}
                    gui_save.setVisible(false);
                }
            }
        }

        login.addActionListener(new login());
        
        JButton exit = new JButton();   
            exit.setText("ANNULLA");
            exit.setFont(Character.getFont("Retro", 20));
            exit.setLocation(20, 65);
            exit.setBackground(new Color(241, 65, 65));
            exit.setForeground(Color.white);
            exit.setSize(new Dimension(150, 35));
        
        class esci implements ActionListener{
            public void actionPerformed (ActionEvent e){
                gui_save.setVisible(false);
            }
        }

        exit.addActionListener(new esci());    
            
        gui_save.add(exit);    
        gui_save.add(login);
        gui_save.add(insert_nameSave);
        gui_save.add(name);
        gui_save.setVisible(true);
    }
 
//caricare un salvataggio
    public static void caricaSalva(){
        Computer.setExit(true);
    //creazione e modifica dell'interfaccia    
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame gui_load = new JFrame("Memory Card - LOAD");
            gui_load.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            gui_load.setSize(new Dimension(430, 290));
            gui_load.setLocation(dim.width/2-gui_load.getSize().width/2, dim.height/2-gui_load.getSize().height/2);
            gui_load.setResizable(false);
            gui_load.setIconImage(Main.icon.getImage());
            
        JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.setLayout(new FlowLayout());
            panel.setBackground(new Color(41, 54, 83));
            
        JLabel name = new JLabel();
            name.setText("Nome Salvataggio:");
            name.setFont(Character.getFont("Retro", 20));
            name.setForeground(Color.white);

        JTextField insert_nameLoad = new JTextField();
            insert_nameLoad.setPreferredSize(new Dimension(150, 40));
            insert_nameLoad.setBorder(new LineBorder(new Color(41, 54, 83), 1));
            insert_nameLoad.setFont(Character.getFont("Classic", 18));
        
        int i = 0;  
        JPanel panel2 = new JPanel();
            panel2.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
        
        //legge tutti i salvataggi presenti nella cartella
            String[] files_name;
            File file = new File("src\\saves\\");
                files_name = file.list();
                for (String file_name : files_name) {
                    JButton b1 = new JButton(file_name);
                    JButton b2 = new JButton("X");
                    b1.setFont(Character.getFont("Retro", 20));
                    b2.setFont(Character.getFont("Retro", 20));
                    b1.setPreferredSize(new Dimension(190, 40));
                    b2.setPreferredSize(new Dimension(50, 40));
                    b1.setBackground(new Color(110, 217, 181));
                    b1.setForeground(Color.white);
                    b2.setBackground(new Color(34, 141, 105));
                    b2.setForeground(Color.white);
                    b1.setBorder(new LineBorder(Color.BLACK));
                    b2.setBorder(new LineBorder(Color.BLACK));
                        class JTextAdd implements ActionListener, Serializable{
                            public void actionPerformed (ActionEvent e){
                                    insert_nameLoad.setText("");
                                    insert_nameLoad.setText(b1.getText());
                            }
                        }
                    
                        class ButtonRemove implements ActionListener, Serializable{
                            public void actionPerformed (ActionEvent e){
                                int scelta = JOptionPane.showConfirmDialog(null,"Sei sicuro di rimuovere il salvataggio?","ATTENZIONE!", JOptionPane.YES_NO_OPTION);
                                if(scelta == 0){
                                    File file = new File("src\\saves\\" + b1.getText());
                                        file.delete();
                                    b1.setVisible(false);
                                    b2.setVisible(false);
                                }  
                            }
                        }
                    b1.addActionListener(new JTextAdd());
                    b2.addActionListener(new ButtonRemove());

                    panel2.add(b2, c);
                    panel2.add(b1, c);
                    i++;
                    c.gridy = i;
                }
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        container.add(panel2);
        container.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension(260, 124));
            scrollPane.setBorder(new LineBorder(new Color(110, 217, 181), 2));
        JPanel p = new JPanel();
            p.add(scrollPane);
            p.setBackground(new Color(41, 54, 83));
                    
        load = new JButton();   
            load.setText("CARICA");
            load.setFont(Character.getFont("Retro", 20));
            load.setBackground(new Color(110, 217, 181));
            load.setForeground(Color.white);
    
    //evento per caricare il salvataggio
        class load implements ActionListener{
            public void actionPerformed (ActionEvent e){
                try {
                    ObjectInputStream sr = new ObjectInputStream(new FileInputStream("src/saves/" + insert_nameLoad.getText()));
                    Main.frame.setVisible(false);
                    JFrame new_frame = (JFrame) sr.readObject();
                    Main.frame = new_frame;
                    Main.frame.setVisible(true);
                    sr.close();
                    gui_load.setVisible(false);
                } catch (IOException ex) {} catch (ClassNotFoundException ex) {}
            }
        }
        load.addActionListener(new load());
        
        JButton exit = new JButton();   
            exit.setText("ANNULLA");
            exit.setFont(Character.getFont("Retro", 20));
            exit.setBackground(new Color(241, 65, 65));
            exit.setForeground(Color.white);
            exit.setSize(new Dimension(140, 35));
    
        class esci implements ActionListener{
            public void actionPerformed (ActionEvent e){
                gui_load.setVisible(false);
            }
        }
        exit.addActionListener(new esci());  
        
        panel.add(name);
        panel.add(insert_nameLoad);
        panel.add(p);
        panel.add(exit);
        panel.add(Box.createRigidArea(new Dimension(30, 0)));
        panel.add(load);
        gui_load.add(panel);
        gui_load.setVisible(true);
    }
}
