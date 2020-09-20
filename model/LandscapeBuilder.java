package model;

import java.util.ArrayList;
import java.util.Random;

import view.GameCanvas;
import view.GameView;

public class LandscapeBuilder {
    Random random;
    private ArrayList<Block> blocks;
    private ArrayList<Coin> coins;
    private ArrayList<Floor> floors;
    private ArrayList<PowerUp> powerUps;
    GameCanvas gameCanvas;

    private int wallHeight;
    private int wallWidth;
    private int floorHeight;
    private int floorWidth;
    private int blockWidthHeight;

    public LandscapeBuilder(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        blocks = this.gameCanvas.getBlocks();
        coins = this.gameCanvas.getCoins();
        floors = this.gameCanvas.getFloors();
        powerUps = this.gameCanvas.getPowerUps();
        random = new Random();
        wallHeight = gameCanvas.getWallHeight();
        wallWidth = gameCanvas.getWallWidth();
        floorHeight = gameCanvas.getWallWidth();
        floorWidth = gameCanvas.getWallHeight();
        blockWidthHeight = gameCanvas.getBlockHeight();
    }

    public void buildLandscape() {

        // first wall
        for (int i = 0, k = 1; i < 7; i++, k += 2) {
            for (int j = -(60 * 5); j < (60 * 4); j += 60)
                blocks.add(new Block(j, blockWidthHeight/2 * k));
        }

        // first floor
        for (int i = -240; i < 240 * 3; i += 240) {
            floors.add(new Floor(i, GameView.HEIGHT - 400));
        }

        powerUps.add(new PowerUp(240 * 2, GameView.HEIGHT - 500));
        
        // pipe left
        for (int i = 520; i > 460 - (4 * 60); i -= 60) {
            blocks.add(new Block(240 * 3, GameView.HEIGHT - i));
        }

        // pipe right
        for (int i = 520; i > 460 - (11 * 60); i -= 60) {
            blocks.add(new Block(240 * 4, GameView.HEIGHT - i));
        }

        // floor right of pipe
        for (int i = 240 * 4; i < 240 * 7; i += 240) {
            floors.add(new Floor(i, GameView.HEIGHT - 400));
        }

        // second wall
        for (int i = 0, k = 1; i < 7; i++, k += 2) {
            for (int j = 240 * 6; j < 240 * 8; j += 60)
                blocks.add(new Block(j, blockWidthHeight / 2 * k));
        }

        // second platform
        for (int i = 240 * 4; i > 300; i -= 60) {
            blocks.add(new Block(i, GameView.HEIGHT));
        }

        // under wall
        for (int i = GameView.HEIGHT / 2; i < GameView.HEIGHT * 2; i += 60) {
            blocks.add(new Block(240, i));
        }

        for (int i = 240; i < 240 * 6; i += 60) {
            floors.add(new Floor(i, GameView.HEIGHT * 2));
        }
        
        // staircase code
        for (int i = 240, k = GameView.HEIGHT * 2; i < 240 * 11; i += 240, k -= 150) {
            blocks.add(new Block(i, k));
        }

        // coins
        for (int i = 250, k = GameView.HEIGHT * 2 - 60; i < 250 * 11; i += 240, k -= 150) {
            if (i > 250) coins.add(new Coin(i, k));
        }

    }

    public void randomBlock() {
        int k = random.nextInt(300) + 50;
        int dice = random.nextInt(10);
        int drawDistance = 900;
        for (int i = 0; i < dice; i++) {
            blocks.add(new Block(drawDistance, k));
            drawDistance += 60;
        }
    }
}
