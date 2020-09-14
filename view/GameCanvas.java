package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameCanvas extends JPanel{
    private GameView gameView;

    public GameCanvas(GameView gameView) {
        this.gameView = gameView;
        setPreferredSize(new Dimension(800, 800));
        setBackground(new Color(50, 60, 70));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
    }
}
