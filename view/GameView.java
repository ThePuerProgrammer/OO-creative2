package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameScreenListener;
import model.Player;

import java.awt.Container;
import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

public class GameView {
    private JFrame window;
    private GameCanvas canvas;

    public GameView(JFrame window) {
        this.window = window;
        window.setTitle("Climber");
    }

    public void init() {
        Container contentPane = window.getContentPane();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 800));
        contentPane.add(panel);

        canvas = new GameCanvas(this);
        contentPane.add(canvas);

        canvas.setFocusable(true);
        GameScreenListener gSL = new GameScreenListener(this);
        canvas.addMouseListener(gSL);
        // game over
    }

    public void jump() {
        double nano = System.nanoTime();
        Player player = canvas.getPlayer();
        int playerY = player.getY();
        int sleepTime = 10;
        int x = 5;
        int y = 20;
        while (true) {
            player.updatePos(player.getX() + x, player.getY() - y);
            canvas.paintImmediately(player.getX(), player.getY(), 20, 20);
            canvas.repaint();
            if (player.getY() == playerY - 210) {
                break;
            }
            y -= 1;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("caught");
            }
        }
        playerY = player.getY();
        y = 1;
        while (true) {
            player.updatePos(player.getX() + x, player.getY() + y);
            canvas.paintImmediately(player.getX(), player.getY(), 20, 20);
            if (player.getY() == playerY + 210) {
                break;
            }
            y += 1;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("caught");
            }
        }
        if (player.getX() > 800 || player.getX() < 0 || player.getY() > 800 || player.getX() < 0) {
            player.updatePos(10, 770);
            canvas.repaint();
            canvas.paintImmediately(player.getX(), player.getY(), 20, 20);
        }
    }

    public GameCanvas getCanvas() {
        return canvas;
    }
}
