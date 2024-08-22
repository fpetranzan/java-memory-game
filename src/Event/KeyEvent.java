/*
 * KeyEvent.java
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

package Event;

import java.awt.event.KeyListener;
import java.io.Serializable;
import memory.Main;

/**
 *
 * @author FRANCESCO
 */
public class KeyEvent implements KeyListener, Serializable{

    @Override
    public void keyTyped(java.awt.event.KeyEvent ke) {}

    @Override
    public void keyPressed(java.awt.event.KeyEvent ke) {
    //controlla se il tasto premuto è uguale a "Enter"
        if(ke.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            Main.enter(); //se corrisponde richiama il metodo nel Main
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent ke) {}
    
}