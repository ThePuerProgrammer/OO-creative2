// certain game loop timing elements for setting FPS 
// adapted from Patrick Feltes Java 2D platformer tutorial
// https://youtu.be/OCcZUO4Zf6o
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameScreenListener;
import model.Player;

import java.awt.Container;
import java.awt.Dimension;

public class GameView implements Runnable {
    private JFrame window;
    private GameCanvas canvas;
    private boolean running = false;
    final private int HEIGHT = 400;
    final private int WIDTH = 800;
    final private int fps = 60;
    final private int time = 1000 / fps;
    private Thread thread;
    private Player player;
    private int jumpHeight = 0;
    private int jumpStart = 0;
    private boolean jumping = false;
    private boolean falling = false;
    private boolean left = false;
    private int xSpeed = 0;
    private int decel = 0;
    private int accel = 0;

    public GameView(JFrame window) {
        this.window = window;
        window.setTitle("Generic Platformer With Red Square Man");
        window.setResizable(false);
        window.setFocusable(true);
        window.requestFocus();
    }

    public void init() {
        Container contentPane = window.getContentPane();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        contentPane.add(panel);

        canvas = new GameCanvas(this);
        contentPane.add(canvas);

        GameScreenListener gSL = new GameScreenListener(this);
        window.addKeyListener(gSL);
    }
    
    public void start() {
        player = canvas.getPlayer();
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
    }

    public GameCanvas getCanvas() {
        return canvas;
    }

    // game loop
    @Override
    public void run() {
        while (running) {
            
            // To aim for consistent FPS
            long nano = System.nanoTime();
            
            tick();
            canvas.repaint(0, 0, WIDTH, HEIGHT);

            long updated = System.nanoTime() - nano;
            long buffer = time - updated / 1_000_000;

            if (buffer <= 0) {
                buffer = 5;
            }

            try {
                Thread.sleep(buffer);
            } catch (Exception e) {
                System.out.println("Caught Exception");
            }

        }
    }

    private void tick() {
        if (jumping) {
            jumping = jump();
        } else if (falling) {
            falling = fall();
        }
    }


    private boolean jump() {
        if (left) {
            player.updatePos(player.getX() - xSpeed, player.getY() - decel);
            decel -= 1;
            if (player.getY() <= jumpHeight) {
                falling = true;
                return false;
            } else {
                return true;
            }
        } else {
            player.updatePos(player.getX() + xSpeed, player.getY() - decel);
            decel -= 1;
            if (player.getY() <= jumpHeight) {
                falling = true;
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean fall() {
        if (left) {
            player.updatePos(player.getX() - xSpeed, player.getY() + accel);
            accel += 1;
            if (player.getY() >= jumpStart) {
                return false;
            } else {
                return true;
            }
        } else {
            player.updatePos(player.getX() + xSpeed, player.getY() + accel);
            accel += 1;
            if (player.getY() >= jumpStart) {
                return false;
            } else {
                return true;
            }
        }
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public JFrame getWindow() {
        return window;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public void setJumpStart(int jumpStart) {
        this.jumpStart = jumpStart;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setDecel(int decel) {
        this.decel = decel;
    }

    public void setAccel(int accel) {
        this.accel = accel;
    }

    public boolean getFalling() {
        return falling;
    }

    public boolean getJumping() {
        return jumping;
    }

    public boolean getRunning() {
        return running;
    }
}
