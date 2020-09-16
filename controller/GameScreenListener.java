package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Player;

import view.GameCanvas;
import view.GameView;

public class GameScreenListener implements KeyListener {
    private GameView gameView;
    private GameCanvas gameCanvas;
    private Player player;
    final private int xSpeed = 10;
    private boolean left = false;
    private boolean right = false;

    public GameScreenListener(GameView gameView) {
        this.gameView = gameView;
        gameCanvas = gameView.getCanvas();
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
                        gameView.setJumping(true);
                        gameView.setAccel(1);
                        gameView.setDecel(20);
                    }
                    break;
                case 0x25:
                    // go left
                    left = true;
                    right = false;
                    break;
                case 0x27:
                    // go right
                    right = true;
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
            gameView.setXSpeed(xSpeed);
            gameView.setLeft(left);
            gameView.setRight(right);
        }
        // System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 0x20:
                gameView.setJumping(false);
                break;
            case 0x25:
                // stop left
                left = false;
                break;
            case 0x27:
                // stop right
                right = false;
                break;
            default:
                break;
        }
        gameView.setLeft(left);
        gameView.setRight(right);

    }
}
