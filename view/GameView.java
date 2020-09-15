package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameScreenListener;
import model.Player;

import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

public class GameView implements Runnable {
    private JFrame window;
    private GameCanvas canvas;
    private boolean running = false;

    public GameView(JFrame window) {
        this.window = window;
        window.setTitle("Climber");
        window.setResizable(false);
    }

    public void init() {
        Container contentPane = window.getContentPane();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 800));
        contentPane.add(panel);

        canvas = new GameCanvas(this);
        contentPane.add(canvas);

        GameScreenListener gSL = new GameScreenListener(this);
        canvas.addMouseListener(gSL);

        running = true;
        run();
        // game over
    }

    public void jump() {
        // Color color = canvas.getBackground();
        // Player player = canvas.getPlayer();
        // int playerY = player.getY();
        // int sleepTime = 10;
        // int x = 5;
        // int y = 20;
        // while (true) {
        //     player.updateColor(color);
        //     canvas.paintImmediately(player.getX(), player.getY(), 20, 20);
        //     player.updateColor(Color.RED);
        //     player.updatePos(player.getX() + x, player.getY() - y);
        //     canvas.paintImmediately(player.getX(), player.getY(), 20, 20);
        //     if (player.getY() == playerY - 210) {
        //         break;
        //     }
        //     y -= 1;
        //     try {
        //         Thread.sleep(sleepTime);
        //     } catch (InterruptedException e) {
        //         System.out.println("caught");
        //     }
        // }
        // canvas.repaint();
        // playerY = player.getY();
        // y = 1;
        // while (true) {
        //     player.updateColor(color);
        //     canvas.paintImmediately(player.getX(), player.getY(), 20, 20);
        //     player.updateColor(Color.RED);
        //     player.updatePos(player.getX() + x, player.getY() + y);
        //     canvas.paintImmediately(player.getX(), player.getY(), 20, 20);
        //     if (player.getY() == playerY + 210) {
        //         break;
        //     }
        //     y += 1;
        //     try {
        //         Thread.sleep(sleepTime);
        //     } catch (InterruptedException e) {
        //         System.out.println("caught");
        //     }
        // }
        // if (player.getX() > 800 || player.getX() < 0 || player.getY() > 800 || player.getX() < 0) {
        //     player.updatePos(10, 770);
        //     canvas.repaint();
        //     canvas.paintImmediately(player.getX(), player.getY(), 20, 20);
        // }
    }

    public GameCanvas getCanvas() {
        return canvas;
    }

    @Override
    public void run() {
        while (running) {
            

            tick();
            canvas.repaint();
        }
    }

    private void tick() {

    }
}
