package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Player;
import view.GameCanvas;
import view.GameView;

public class GameScreenListener implements KeyListener, MouseListener {
    private GameView gameView;
    private GameCanvas gameCanvas;

    public GameScreenListener(GameView gameView) {
        this.gameView = gameView;
        gameCanvas = gameView.getCanvas();
    }

    @Override
    public void mousePressed(MouseEvent e) {    
        gameView.jump();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
