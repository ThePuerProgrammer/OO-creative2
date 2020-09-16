package view;

import javax.swing.JPanel;

import model.Player;
import model.Block;

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
    private Block block;
    private Graphics2D g2D;
    private String startMessage = "Press SPACE To Start!";
    private ArrayList<Block> blocks;

    final private int blockWidth = 40;
    final private int blockHeight = 40;

    public GameCanvas(GameView gameView) {
        this.gameView = gameView;
        setPreferredSize(new Dimension(gameView.getWIDTH(), gameView.getHeight()));
        setBackground(new Color(50, 60, 70));
        player = new Player();
        block = new Block(0, gameView.getHeight() - 30);
        blocks = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2D = (Graphics2D) g;
        g2D.setFont(new Font("Arial", Font.PLAIN, 60));
        g2D.setColor(Color.white);
        g2D.drawString(startMessage, 90, 200);
        g2D.setFont(new Font("Courier", Font.PLAIN, 12));
        g2D.drawString("SPACE: Jump   ARROW_KEYS: Move   P: Pause   Q: Quit", 10, 10);
        g2D.setColor(Color.BLACK);
        block.render(g2D, gameView.getWIDTH(), 40);
        for (var each: blocks) {
            each.render(g2D, blockWidth, blockHeight);
        }
        player.render(g2D, 20, 20);
    }

    @Override
    public void repaint() {

    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
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
}
