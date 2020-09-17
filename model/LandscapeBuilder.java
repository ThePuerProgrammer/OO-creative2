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

    public LandscapeBuilder(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        blocks = this.gameCanvas.getBlocks();
        walls = this.gameCanvas.getWalls();
        floors = this.gameCanvas.getFloors();
        random = new Random();
    }

    public void buildLandscape() {
        for (int i = 0; i < 240 * 10; i += 240) {
            floors.add(new Floor(i, GameView.HEIGHT - 100));
        }

        for (int i = 0, k = GameView.HEIGHT - 200; i < 240 * 10; i += 240, k -= 150) {
            blocks.add(new Block(i, k));
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
