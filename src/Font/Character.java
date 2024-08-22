/*
 * Character.java
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

package Font;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.Serializable;

/**
 *
 * @author FRANCESCO
 */
public class Character implements Serializable{
    
    public static Font getFont(String name, int size){ //nome carattere e grandezza
    //crea un font personalizzato da File .ttf
        try {
            Font font;
            font = Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("src/Font/" + name + ".ttf")); //richiamo il carattere selezionato
            font = font.deriveFont(java.awt.Font.BOLD,size); //imposto il grassatte
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font; //restituisco il carattere scelto
        } catch (FontFormatException ex) {} catch (IOException ex) {}
    return null;
    }
}