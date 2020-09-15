package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import model.Player;

import java.awt.Graphics2D;

import view.GameCanvas;
import view.GameView;
import view.MainMenu;

public class GameScreenListener implements KeyListener {
    private GameView gameView;
    private GameCanvas gameCanvas;
    private Graphics2D g2D;
    private Player player;
    final private int xSpeed = 10;
    private boolean left = false;

    public GameScreenListener(GameView gameView) {
        this.gameView = gameView;
        gameCanvas = gameView.getCanvas();
        g2D = gameCanvas.getG2D();
        player = gameCanvas.getPlayer();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // Remove the start game banner
        if (gameCanvas.getString() != "") {
            if (e.getKeyCode() == 0x20) {
                gameCanvas.setString("");
                gameCanvas.repaint(0, 0, gameView.getWIDTH(), gameView.getHeight());
                gameView.start();
            }
            
        } else {
            // Main keyswitch
            switch (e.getKeyCode()) {
                case 0x20:
                    // jump
                    if (!gameView.getFalling() && !gameView.getJumping()) {
                        gameView.setJumpHeight(player.getY() - 210);
                        gameView.setJumpStart(player.getY());
                        gameView.setXSpeed(xSpeed);
                        gameView.setJumping(true);
                        gameView.setAccel(1);
                        gameView.setDecel(20);
                    }
                    break;
                case 0x25:
                    // go left
                    player.updatePos(player.getX() - xSpeed, player.getY());
                    left = true;
                    break;
                case 0x27:
                    // go right
                    player.updatePos(player.getX() + xSpeed, player.getY());
                    left = false;
                    break;
                case 0x50:
                    // pause
                    if (gameView.getRunning()) {
                        gameCanvas.setString("Paused. Press SPACE");
                        gameCanvas.repaint(0, 0, gameView.getWIDTH(), gameView.getHeight());
                        gameView.stop();
                    } else {
                        gameCanvas.setString("");
                        gameCanvas.repaint(0, 0, gameView.getWIDTH(), gameView.getHeight());
                        gameView.start();
                    }
                    break;
                case 0x51:
                    // quit
                    System.exit(0);
                default:
                    break;
            }
            gameView.setLeft(left);
        }
        System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
