/*
 * Tempo.java
 *
 * Created on 9-giu-2020, 17.56.41
 *
 * Copyright(c) {2020} XYZ Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of XYZ Company.
 *
 */

/*
 *  Project: 
 *  Created by FRANCESCO
 */

package Player;

import Card.Card;
import Computer.Computer;
import Interfaces.primaria.Game;
import static Interfaces.primaria.Game.card1;
import static Interfaces.primaria.Game.card2;
import static Interfaces.primaria.Game.suono;
import Interfaces.primaria.Menu;
import Interfaces.primaria.Point;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;

/**
 *
 * @author FRANCESCO
 */
public class Tempo extends Thread implements Serializable{
    public static int time = 9;
    private static boolean finish;
    private static boolean stop;
    
    public Tempo(){
        setName("Timer");
    }
    
    public void run(){
        do{
            stop = false;
            for(int i = time; i > 0; i--){
                /*try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException ex) {}*/
                try {
                    BufferedImage timage = ImageIO.read(new File("src\\images_sounds\\" + i + ".png"));
                        Point.timer.setIcon(new ImageIcon(timage));
                } catch (IOException ex) {}
                if(i <= 3){
                    sound("timer", 1.0);
                }
                if(Menu.mode == 0 && i == 1){
                    Computer.setStop(true);
                    Player.setExit(true);
                    break;
                }
                if(Menu.mode == 2 && i == 1){
                    Player.setExit(true);
                    break;
                }
                if(i == 1 && card1 != null && card2 == null){
                    try {
                        Thread.currentThread().sleep(700);
                    } catch (InterruptedException ex) {}
                    card1.setText("?");
                        card1.setForeground(new Color(41, 54, 83));
                    card1 = null;
                }
                if(finish){
                finish = false;
                    break;
                }
                
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException ex) {}
            }
            if(stop){
            setStop(false);
                try {
                    BufferedImage timage = ImageIO.read(new File("src\\images_sounds\\9.png"));
                        Point.timer.setIcon(new ImageIcon(timage));
                } catch (IOException ex) {}
            break;
            }
            
            try {
                Thread.currentThread().sleep(600);
            } catch (InterruptedException ex) {}
        }while(!isGameWon());
    }

    //active sound
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
    
//controlla se il gioco è finito    
    public boolean isGameWon(){
    //controlla che siano state trovate tutte le coppie
        for(Card c: Game.cards){
            if (c.getAccoppiata() == false){
                return false;
            }
        }
        return true;
    }
    
    public static void setExit(boolean finish) {
        Tempo.finish = finish;
    }

    public static void setStop(boolean stop) {
        Tempo.stop = stop;
    }
}
