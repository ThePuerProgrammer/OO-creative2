package view;

import javax.swing.JPanel;

import model.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameCanvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private GameView gameView;
    public Player player;

    public GameCanvas(GameView gameView) {
        this.gameView = gameView;
        setPreferredSize(new Dimension(800, 800));
        setBackground(new Color(50, 60, 70));
        player = new Player();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        player.render(g2D);
    }

    public Player getPlayer() {
        return player;
    }
}
