package view;

import javax.swing.JPanel;

import model.Player;
import model.Wall;
import model.Block;
import model.Floor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameCanvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private GameView gameView;
    private Player player;
    private Graphics2D g2D;
    private String startMessage = "Press SPACE To Start!";
    private ArrayList<Block> blocks;
    private ArrayList<Wall> walls;
    private ArrayList<Floor> floors;

    final private int blockWidth = 60;
    final private int blockHeight = 60;
    final private int wallWidth = 120;
    final private int wallHeight = 240;

    public GameCanvas(GameView gameView) {
        this.gameView = gameView;
        setPreferredSize(new Dimension(gameView.getWIDTH(), gameView.getHeight()));
        setBackground(new Color(10, 20, 20));
        player = new Player();
        blocks = new ArrayList<>();
        walls = new ArrayList<>();
        floors = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2D = (Graphics2D) g;

        for (var each: blocks) {
            each.render(g2D, blockWidth, blockHeight);
        }

        for (var each: walls) {
            each.render(g2D, wallWidth, wallHeight);
        }

        for (var each: floors) {
            each.render(g2D, wallHeight, wallWidth);
        }

        player.render(g2D, 20, 20);

        g2D.setFont(new Font("Courier", Font.PLAIN, 40));
        g2D.drawString(startMessage, gameView.getWIDTH() / 5, gameView.getHeight() / 2);
        g2D.setFont(new Font("Courier", Font.PLAIN, 12));
        g2D.drawString("SPACE: Jump   ARROW_KEYS: Move   P: Pause   R: Reset   Q: Quit", 10, 10);
    }

    public Player getPlayer() {
        return player;
    }
    
    public Graphics2D getG2D() {
        return g2D;
    }

    public void setString(String s) {
        startMessage = s;
    }

    public String getString() {
        return startMessage;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }
}
