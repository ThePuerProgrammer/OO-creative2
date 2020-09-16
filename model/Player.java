package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends Environment{
    public Player() {
        pos = new startPos(200, 350); // init pos
        color = Color.RED;
    }

    public Rectangle enhancedBoundary() {
        return new Rectangle(pos.x, pos.y, width, height);
    }
}
