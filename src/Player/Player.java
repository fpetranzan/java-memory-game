/*
 * Player.java
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

import computer.Semaforo;
import static Interfaces.primaria.Game.card1;
import static Interfaces.primaria.Game.card2;
import java.awt.Color;
import static memory.Main.g;
import java.io.*;

/**
 *
 * @author FRANCESCO
 */
public class Player extends Thread implements Serializable{
    public Semaforo pieno;
    public Semaforo vuoto;
    public static String name;
    public static boolean exit;

    public Player(Semaforo pieno, Semaforo vuoto, String nome) {
        this.pieno = pieno;
        this.vuoto = vuoto;
        setName(nome);
    }
    
//thread dei giocatori    
    public void run(){
        
        while(true){
            switch(this.getName()){
                case "Player1":
                    vuoto.P();
                        g.setBackground(new Color(146, 110, 217));
                        name = "Player1";
                        try {
                            sleep(500);
                        } catch (InterruptedException ex) {}
                        
                        
                        do{
                            if(exit){
                                setExit(false);
                                break;
                            }
                            try {
                                sleep(500);
                            } catch (InterruptedException ex) {}
                        }while(card1 == null || card2 == null || card1.getValore() == card2.getValore());

                        try {
                            sleep(1000);
                        } catch (InterruptedException ex) {}
                        
                    pieno.V();
                break;
                case "Player2":
                    pieno.P();
                        g.setBackground(new Color(217, 110, 128));
                        name = "Player2";
                        try {
                            sleep(500);
                        } catch (InterruptedException ex) {}

                        do{
                            if(exit){
                                setExit(false);
                                break;
                            }
                            try {
                                sleep(500);
                            } catch (InterruptedException ex) {}
                        }while(card1 == null || card2 == null || card1.getValore() == card2.getValore());

                        try {
                            sleep(1000);
                        } catch (InterruptedException ex) {}
                    vuoto.V();
                break;
            }
        }
    }
            
    public static void setExit(boolean exit) {
        Player.exit = exit;
    }
}
