package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.World;

public class WorldRenderer {

    private World world;
    private WoodCutterRenderer woodCutterRenderer;
    private WhiteWaveRenderer whiteWaveRenderer;
    private GameMapRenderer map;


    public WorldRenderer(World world) {
        this.world = world;
        woodCutterRenderer = new WoodCutterRenderer();
        whiteWaveRenderer = new WhiteWaveRenderer();
        map = new GameMapRenderer(world.getGameMap().getTiles());
    }


    public void render(SpriteBatch batch) {
            map.drawGameMap(batch);
        if (!world.getWoodCutter().getBounds().overlaps(world.getWoodCutter().getWhiteWave().getBounds())) {
            whiteWaveRenderer.drawWhiteWave(batch, world.getWhiteWave());
        }
        boolean isWoodCutterDrawn = false;
        for (int i = 0; i < world.getRandomWoodCutters().size(); i++) {
            if (world.getWoodCutter().getBounds().getY() > world.getRandomWoodCutters().get(i).getBounds().getY()){
                woodCutterRenderer.drawWoodCutter(batch, world.getWoodCutter());
                isWoodCutterDrawn = true;
            }
            woodCutterRenderer.drawWoodCutter(batch, world.getRandomWoodCutters().get(i));
        }
        if (!isWoodCutterDrawn) woodCutterRenderer.drawWoodCutter(batch, world.getWoodCutter());
    }

    public void dispose(){
        woodCutterRenderer.dispose();
        whiteWaveRenderer.dispose();
        map.dispose();
    }

    public void update(float delta){
        whiteWaveRenderer.update(delta);
        woodCutterRenderer.update(delta);

    }
}
