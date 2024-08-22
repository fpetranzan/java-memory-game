/*
 * Computer.java
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

package Computer;

import Card.Card;
import Interfaces.primaria.Game;
import computer.Semaforo;
import static memory.Main.g;
import static Interfaces.primaria.Game.card1;
import static Interfaces.primaria.Game.card2;
import static Interfaces.primaria.Game.cards;
import java.util.*;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author FRANCESCO
 */
public class Computer extends Thread implements Serializable{
//algoritmo computer per la scelta delle carte
    public static ArrayList<Card> memory = new ArrayList<Card>(); //memoria del computer
    public static boolean exit;
    public static boolean stop;
    private Random random = new Random();
    private Semaforo pieno;
    private Semaforo vuoto;
    private Card c1;

    public Computer(Semaforo pieno, Semaforo vuoto) {           
        this.pieno = pieno;
        this.vuoto = vuoto;
        this.setName("Computer");
    }
    
    public void run(){
        int scelta;
        while(true){
            pieno.P();
                g.setBackground(new Color(217, 110, 128));
                //verfica se il computer deve terminare o procedere
                if(exit){
                    setExit(false);
                    break;
                }

                do{
                    //verfica se il computer deve terminare o procedere
                    if(stop){
                    setStop(false);
                        vuoto.V();
                    }
                    try {
                        sleep(1500);
                    } catch (InterruptedException ex) {}
                    //scelta della prima carta tramite il metodo generaCarta (casuale)
                        scelta = generaCarta();
                        if(scelta != -1)
                            //se la generazione è andata a buon fine richiama il metodo scegliCarta1
                            scegliCarta1(cards.get(scelta));
                        else 
                            break;
                    try {
                        sleep(1500);
                    } catch (InterruptedException ex) {}
                    //scelta della seconda carta tramite il metodo generaCarta (casuale)
                        scelta = generaCarta();
                        if(scelta != -1)
                            //se la generazione è andata a buon fine richiama il metodo scegliCarta2
                            scegliCarta2(cards.get(scelta));
                        else
                            break;
                        
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {}

                }while(card1.getValore() == card2.getValore());

                try {
                    sleep(500);
                } catch (InterruptedException ex) {}
            vuoto.V();
        }
    }
    
    public static void setExit(boolean exit) {
        Computer.exit = exit;
    }
    
    public static void setStop(boolean stop) {
        Computer.stop = stop;
    }
//generazione carta    
    public int generaCarta(){
        //verifica che il computer possa procedere
        if(!exit){
            return random.nextInt(cards.size());
        }
    return -1;
    }

//controllo e conferma della scelta della prima carta    
    public void scegliCarta1(Card c){
        //esegue dei controlli
        if(c.getAccoppiata() || c.getPosizione() == memory.get(memory.size()-1).getPosizione() || c.getPosizione() == memory.get(memory.size()-2).getPosizione()){
            //se vanno a buon fine deve generare un'altra carta
            //riesegue il procedimento fino a trovare una carta valida
            int scelta = generaCarta();
                if(scelta != -1)
                    scegliCarta1(cards.get(scelta));
        }else{
        //trovata una carta
           c1 = c;
           //clicca la carta scelta
           c.doClick();           
        }
    }

//controllo e conferma della scelta della seconda carta        
    public void scegliCarta2(Card c){
        ArrayList<Card> lastTwo = new ArrayList<Card>();
        //controlla se manca l'ultima coppia
        if(Game.coppie == cards.size()/2 -1){
            for(Card x : cards){
                if(!x.getAccoppiata()){
                    lastTwo.add(x);
                }
            }
            //clicca le ultime due carte
            lastTwo.get(0).doClick();
            lastTwo.get(1).doClick();
        }else   ////esegue dei controlli
        if(c.getAccoppiata() || c.getPosizione() == c1.getPosizione() || c.getPosizione() == memory.get(memory.size()-1).getPosizione() || c.getPosizione() == memory.get(memory.size()-2).getPosizione()){
            //se vanno a buon fine deve generare un'altra carta
            //riesegue il procedimento fino a trovare una carta valida
            int scelta = generaCarta();
                if(scelta != -1)
                    scegliCarta2(cards.get(scelta));
        }else{
        //trovata una carta valida
        //ma prima controlla se scegliCarta1 è uguale a una carta già in memoria
        //se presente viene girata
            if(memory.size() == 2){ //1 turno = 2 carte girata = 2 carte in memoria
                    if(c1.getValore() == memory.get(memory.size()-1).getValore() && c1.getPosizione() != memory.get(memory.size()-1).getPosizione()){
                        memory.get(memory.size()-1).doClick();
                    }else
                    if(c1.getValore() == memory.get(memory.size()-2).getValore() && c1.getPosizione() != memory.get(memory.size()-2).getPosizione()){
                        memory.get(memory.size()-2).doClick();
                    }else{
                        c.doClick();
                    }
            }else
            if(memory.size() >= 4){ //2o+ turni = 2/4 carte girate = 2/4 carte in memoria
                if(c1.getValore() == memory.get(memory.size()-1).getValore() && c1.getPosizione() != memory.get(memory.size()-1).getPosizione()){
                    memory.get(memory.size()-1).doClick();
                }else if(c1.getValore() == memory.get(memory.size()-2).getValore() && c1.getPosizione() != memory.get(memory.size()-2).getPosizione()){
                    memory.get(memory.size()-2).doClick();
                }else if(c1.getValore() == memory.get(memory.size()-3).getValore() && c1.getPosizione() != memory.get(memory.size()-3).getPosizione()){
                    memory.get(memory.size()-3).doClick();
                }else if(c1.getValore() == memory.get(memory.size()-4).getValore() && c1.getPosizione() != memory.get(memory.size()-4).getPosizione()){
                    memory.get(memory.size()-4).doClick();
                }else{
                    c.doClick();
                }
            }else{ //se non presente in memoria viene girata la carta scelta
                c.doClick();
            }
        }
    }
}
