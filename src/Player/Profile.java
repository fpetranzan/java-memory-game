/*
 * Profile.java
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

import java.io.Serializable;

/**
 *
 * @author FRANCESCO
 */
public class Profile  implements Serializable{
    private String name;
    private String mode;
    private int point;

//profilo delle partite
    public Profile(String name, String mode, int poit) {
        this.name = name;
        this.mode = mode;
        this.point = poit;
    }

//metodi setter
    public void setName(String name) {
        this.name = name;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setPoint(int point) {
        this.point = point;
    }

//metodi getter   
    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public int getPoint() {
        return point;
    }
}
