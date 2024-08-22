/*
 * Card.java
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

package Card;

import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author FRANCESCO
 */
public class Card extends JButton implements Serializable{
//variabili presenti in ogni bottone
    private int valore;
    private int posizione;
    private boolean accoppiata;

//metodi setter
    public void setValore(int valore) {
        this.valore = valore;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }
    
    public void setAccoppiata(boolean accoppiata){
        this.accoppiata = accoppiata;
    }

//metodi getter    
    public int getValore() {
        return valore;
    }

    public int getPosizione() {
        return posizione;
    }
    
    public boolean getAccoppiata(){
        return accoppiata;
    }
}