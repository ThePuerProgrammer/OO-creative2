// certain game loop timing elements for setting FPS 
// adapted from Patrick Feltes Java 2D platformer tutorial
// https://youtu.be/OCcZUO4Zf6o
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameScreenListener;
import model.Player;
import model.Block;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

public class GameView implements Runnable {

    final static public int HEIGHT = 400;
    final static public int WIDTH = 800;

    private JFrame window;
    private GameCanvas canvas;

    private boolean running = false;
    final private int fps = 60;
    final private int time = 1000 / fps;
    private Thread thread;

    private Player player;
    private Block floor;

    private int jumpHeight = 0;
    private boolean jumping = false;
    private boolean falling = true;
    private boolean left = false;
    private boolean right = false;
    private int xSpeed = 0;
    private int decel = 0;
    private int accel = 0;
    
    private ArrayList<Block> blocks;

    public GameView(JFrame window) {
        this.window = window;
        window.setTitle("The Game With No Name");
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
        floor = canvas.getBlock();
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

        // vertical movement
        if (jumping) {
            jumping = jump();
        } else {
            fall();
        }

        // horizontal movement
        if (left) {
            goLeft();
        } else if (right) {
            goRight();
        }
    }


    private boolean jump() {
        player.updatePos(player.getX(), player.getY() - decel);
        decel -= 1;
        boolean breakout = false;
        for (var each: blocks) {
            while (player.getBounary().intersects(each.getBounary())) {
                breakout = true;
                player.updatePos(player.getX(), player.getY() + 1);
            }
            if (breakout) return false;
        }
        if (player.getY() <= jumpHeight) {
            return false;
        } else {
            return true;
        }
    }

    private void fall() {
        player.updatePos(player.getX(), player.getY() + accel);
        accel += 1;
        falling = true;
        if (accel > 20) accel = 20;
        boolean breakout = false;
        for (var each: blocks) {
            while (player.getBounary().intersects(each.getBounary()) || player.getBounary().intersects(floor.getBounary())) {
                player.updatePos(player.getX(), player.getY() - 1);
                breakout = true;
                accel = 1;
                falling = false;
            }
            if (breakout) break;
        }
    }

    private void goLeft() {
        boolean playerMovable = player.getX() > WIDTH / 3;
        if (playerMovable) {
            player.updatePos(player.getX() - xSpeed, player.getY());
        } else {
            for (var each: blocks) {
                each.updatePos(each.getX() + xSpeed, each.getY());
            }
        }
        boolean breakout = false;
        for (var each: blocks) {
            while (player.getBounary().intersects(each.getBounary())) {
                breakout = true;
                if (playerMovable) {
                    player.updatePos(player.getX() + 1, player.getY());
                } else {
                    for (var every: blocks) {
                        every.updatePos(every.getX() - 1, every.getY());
                    }
                }
            }
            if (breakout) break;
        }
    }

    private void goRight() {
        boolean playerMovable = player.getX() < (WIDTH / 3) * 2;
        if (playerMovable) {
            player.updatePos(player.getX() + xSpeed, player.getY());
        } else {
            for (var each: blocks) {
                each.updatePos(each.getX() - xSpeed, each.getY());
            }
        }
        boolean breakout = false;
        for (var each: blocks) {
            while (player.getBounary().intersects(each.getBounary())) {
                breakout = true;
                if (playerMovable) {
                    player.updatePos(player.getX() - 1, player.getY());
                } else {
                    for (var every: blocks) {
                        every.updatePos(every.getX() +1, every.getY());
                    }
                }
            }
            if (breakout) break;
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

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
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
