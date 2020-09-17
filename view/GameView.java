// certain game loop timing elements adapted from Patrick Feltes
// https://youtu.be/OCcZUO4Zf6o
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameScreenListener;
import model.Player;
import model.Wall;
import model.Block;
import model.Environment;
import model.Floor;
import model.LandscapeBuilder;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

public class GameView implements Runnable {

    final static public int HEIGHT = 800;
    final static public int WIDTH = 800;

    private JFrame window;
    private GameCanvas gameCanvas;

    private boolean running = false;
    final private int fps = 60;
    final private int time = 1000 / fps;
    private Thread thread;

    private Player player;

    private int jumpHeight = 0;
    private boolean jumping = false;
    private boolean falling = true;
    private boolean left = false;
    private boolean right = false;
    private int xSpeed = 10;
    private int decel = 0;
    private int accel = 0;
    private boolean up = true;

    private LandscapeBuilder landscape;
    
    private ArrayList<Block> blocks;
    private ArrayList<Wall> walls;
    private ArrayList<Floor> floors;

    public GameView(JFrame window) {
        this.window = window;
        window.setTitle("Generic Platform Physics Demo");
        window.setResizable(false);
        window.setFocusable(true);
        window.requestFocus();
    }

    public void init() {
        Container contentPane = window.getContentPane();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        contentPane.add(panel);
        gameCanvas = new GameCanvas(this);
        contentPane.add(gameCanvas);
        GameScreenListener gSL = new GameScreenListener(this);
        window.addKeyListener(gSL);
        gameCanvas.addMouseListener(gSL);
        landscape = new LandscapeBuilder(gameCanvas);
        blocks = gameCanvas.getBlocks();
        walls = gameCanvas.getWalls();
        floors = gameCanvas.getFloors();
        landscape.buildLandscape();
        player = gameCanvas.getPlayer();
    }
    
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
    }

    public GameCanvas getCanvas() {
        return gameCanvas;
    }

    // game loop
    @Override
    public void run() {
        while (running) {
            // To aim for consistent FPS
            long nano = System.nanoTime();
            tick();
            gameCanvas.repaint(0, 0, WIDTH, HEIGHT);
            long updated = System.nanoTime() - nano;
            long buffer = time - updated / 1_000_000;
            if (buffer <= 0) {
                buffer = 5;
            }
            try {
                Thread.sleep(buffer);
            } catch (Exception e) {
                System.out.println("Caught Exception - Thread.sleep(buffer)");
                System.exit(1);
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
        boolean playerMovable = player.getY() > HEIGHT / 4;
        if (playerMovable) {
            player.updatePos(player.getX(), player.getY() - decel);
        } else {
            for (var each: blocks) {
                each.updatePos(each.getX(), each.getY() + decel);
            }
            for (var each: floors) {
                each.updatePos(each.getX(), each.getY() + decel);
            }
            for (var each: walls) {
                each.updatePos(each.getX(), each.getY() + decel);
            }
        }
        decel -= 1;
        boolean breakout = false;
        for (var each: blocks) {
            while (player.getBounary().intersects(each.getBounary())) {
                breakout = true;
                if (playerMovable) {
                    player.updatePos(player.getX(), player.getY() + 1);
                } else {
                    for (var every: blocks) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                    for (var every: floors) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                    for (var every: walls) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                }
            }
            if (breakout) return false;
        }
        breakout = false;
        for (var each: floors) {
            while (player.getBounary().intersects(each.getBounary())) {
                breakout = true;
                if (playerMovable) {
                    player.updatePos(player.getX(), player.getY() + 1);
                } else {
                    for (var every: blocks) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                    for (var every: floors) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                    for (var every: walls) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                }
            }
            if (breakout) return false;
        }
        breakout = false;
        for (var each: walls) {
            while (player.getBounary().intersects(each.getBounary())) {
                breakout = true;
                if (playerMovable) {
                    player.updatePos(player.getX(), player.getY() + 1);
                } else {
                    for (var every: blocks) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                    for (var every: floors) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                    for (var every: walls) {
                        every.updatePos(every.getX(), every.getY() - 1);
                    }
                }
            }
            if (breakout) return false;
        }
        if (decel == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void fall() {
        boolean playerMovable = player.getY() < (HEIGHT / 4) * 3;
        if (playerMovable) {
            player.updatePos(player.getX(), player.getY() + accel);
        } else {
            for (var each: blocks) {
                each.updatePos(each.getX(), each.getY() - accel);
            }
            for (var each: floors) {
                each.updatePos(each.getX(), each.getY() - accel);
            }
            for (var each: walls) {
                each.updatePos(each.getX(), each.getY() - accel);
            }
        }
        accel += 1;
        falling = true;
        if (accel > 20) accel = 20;
        boolean breakout = false;
        for (var each: blocks) {
            while (player.getBounary().intersects(each.getBounary())) {
                if (playerMovable) {
                    player.updatePos(player.getX(), player.getY() - 1);
                } else {
                    for (var every: blocks) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                    for (var every: walls) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                    for (var every: floors) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                }
                breakout = true;
                accel = 1;
                falling = false;
            }
            if (breakout) break;
        }
        breakout = false;
        for (var each: walls) {
            while (player.getBounary().intersects(each.getBounary())) {
                if (playerMovable) {
                    player.updatePos(player.getX(), player.getY() - 1);
                } else {
                    for (var every: blocks) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                    for (var every: walls) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                    for (var every: floors) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                }
                breakout = true;
                accel = 1;
                falling = false;
            }
            if (breakout) break;
        }
        breakout = false;
        for (var each: floors) {
            while (player.getBounary().intersects(each.getBounary())) {
                if (playerMovable) {
                    player.updatePos(player.getX(), player.getY() - 1);
                } else {
                    for (var every: blocks) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                    for (var every: walls) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                    for (var every: floors) {
                        every.updatePos(every.getX(), every.getY() + 1);
                    }
                }
                breakout = true;
                accel = 1;
                falling = false;
            }
            if (breakout) break;
        }
        if (player.getY() > HEIGHT) {
            player.resetStart();
            blocks.clear();
            landscape.buildLandscape();
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
            for (var each: walls) {
                each.updatePos(each.getX() + xSpeed, each.getY());
            }
            for (var each: floors) {
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
                    for (var every: walls) {
                        every.updatePos(every.getX() - 1, every.getY());
                    }
                    for (var every: floors) {
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
            for (var each: walls) {
                each.updatePos(each.getX() - xSpeed, each.getY());
            }
            for (var each: floors) {
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
                    for (var every: walls) {
                        every.updatePos(every.getX() +1, every.getY());
                    }
                    for (var every: floors) {
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

    public void addBlock(int x, int y) {
        blocks.add(new Block(x,y));
    }

    public void reset() {
        blocks.clear();
        walls.clear();
        floors.clear();
        player.resetStart();
        landscape.buildLandscape();
    }
}
