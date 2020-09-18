package model;

import java.awt.Graphics2D;
import java.awt.Color;

public class Coin extends Environment {
    boolean collected;

    public Coin(int x, int y) {
        pos = new startPos(x, y); // init pos
        color = new Color(255, 223, 0);
        collected = false;
    }

    @Override
    public void render(Graphics2D g2D, int width, int height) {
        this.width = width;
        this.height = height;
        g2D.setColor(color);
        g2D.fillOval(pos.x, pos.y, width, height);
    }

    public boolean getCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

}
