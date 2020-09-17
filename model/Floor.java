package model;

import java.awt.Color;

public class Floor extends Environment {
    
    public Floor(int x, int y) {
        pos = new startPos(x, y); // init pos
        color = new Color(100, 110, 120);
    }
}
