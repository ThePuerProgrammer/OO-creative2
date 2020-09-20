package model;

import java.awt.Color;
import java.awt.Rectangle;
import view.GameCanvas;

public class Player extends Environment{

    final private int startX = 240;
    final private int startY = 20;

    public Player() {
        pos = new startPos(startX, startY); // init pos
        color = Color.RED;
    }

    public Rectangle enhancedBoundary() {
        return new Rectangle(pos.x, pos.y, width, height);
    }

    public void resetStart() {
        updatePos(startX, startY);
    }

    public void grow(int x) {
        GameCanvas.updatePlayerSize(x);
    }
}
