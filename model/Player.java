package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
    public class startPos {
        public int x = 0;
        public int y = 0;
        public startPos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    startPos pos;

    public Player() {
        pos = new startPos(10, 770);
    }

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

    public void render(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.fillRect(pos.x, pos.y, 20, 20);
    }
}
