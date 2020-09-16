package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import view.GameView;

public abstract class Environment {

    protected int width;
    protected int height;

    Color color;
    class startPos {
        public int x = 0;
        public int y = 0;

        public startPos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    startPos pos;
    public int getX() {
        return pos.x;
    }

    public int getY() {
        return pos.y;
    }

    public void updatePos(int x, int y) {
        pos.x = x;
        pos.y = y;
    }

    public void updateColor(Color color) {
        this.color = color;
    }

    public void render(Graphics2D g2D, int width, int height) {
        this.width = width;
        this.height = height;
        g2D.setColor(color);
        g2D.fillRect(pos.x, pos.y, width, height);
    }

    public Rectangle getBounary() {
        return new Rectangle(pos.x, pos.y, width, height);
    }
}
