package model;

import java.awt.Color;

public class Block extends Environment {

    public Block(int x, int y) {
        pos = new startPos(x, y); // init pos
        color = new Color(150, 160, 170);
    }
}