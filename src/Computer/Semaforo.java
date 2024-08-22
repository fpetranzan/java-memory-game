/*
 * Semaforo.java
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

package computer;

import java.io.Serializable;

/**
 *
 * @author FRANCESCO
 */
public class Semaforo implements Serializable{
    int valore;
    
    public Semaforo(int valore){
        this.valore = valore;
    }
    
    synchronized public void P(){
        while(valore == 0){
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        valore--;
    }
    
    synchronized public void V(){
        valore++;
        notify();
    }
}