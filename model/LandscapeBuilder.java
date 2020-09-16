package model;

import java.util.ArrayList;

import model.Block;
import view.GameCanvas;

public class LandscapeBuilder {
    private ArrayList<Block> blocks;
    GameCanvas gameCanvas;

    public LandscapeBuilder(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        blocks = this.gameCanvas.getBlocks();
    }

    public void buildLandscape() {
        
    }
}
