package model;

import java.util.ArrayList;
import java.util.Random;

import view.GameCanvas;
import view.GameView;

public class LandscapeBuilder {
    Random random;
    private ArrayList<Block> blocks;
    private ArrayList<Wall> walls;
    private ArrayList<Floor> floors;
    GameCanvas gameCanvas;

    private int wallHeight;
    private int wallWidth;
    private int floorHeight;
    private int floorWidth;
    private int blockWidthHeight;

    public LandscapeBuilder(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        blocks = this.gameCanvas.getBlocks();
        walls = this.gameCanvas.getWalls();
        floors = this.gameCanvas.getFloors();
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

        // pipe left
        for (int i = 520; i > 460 - (4 * 60); i -= 60) {
            blocks.add(new Block(240 * 3, GameView.HEIGHT - i));
        }

        // pipe right
        for (int i = 520; i > 460 - (11 * 60); i -= 60) {
            blocks.add(new Block(240 * 4, GameView.HEIGHT - i));
        }

        // floor right of pipe
        for (int i = 240 * 4; i < 240 * 8; i += 240) {
            floors.add(new Floor(i, GameView.HEIGHT - 400));
        }





        
        // staircase code
        // for (int i = 0, k = GameView.HEIGHT - 200; i < 240 * 10; i += 240, k -= 150) {
        //     blocks.add(new Block(i, k));
        // }
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
