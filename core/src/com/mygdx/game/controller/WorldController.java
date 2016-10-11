package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.model.World;

import java.util.ArrayList;

public class WorldController {
    World world;
    private WoodCutterController woodCutterController;
    private ArrayList<WoodCutterController> randomWoodCutterControllers;
    private int randWoodCutRefreshTime = 300;

    public WorldController(World world) {
        this.world = world;
        woodCutterController = new WoodCutterController(world.getWoodCutter(), world.getGameMap());
        randomWoodCutterControllers = new ArrayList<WoodCutterController>();
        for (int i = 0; i < world.getRandomWoodCutters().size(); i++) {
            randomWoodCutterControllers.add(new WoodCutterController(world.getRandomWoodCutters().get(i), world.getGameMap()));
        }
        for (int i = 0; i < randomWoodCutterControllers.size(); i++) {
            randomWoodCutterControllers.get(i).setClickCoordinates(MathUtils.random(200, 3200), MathUtils.random(200,2500));
        }
    }

    public void setClickCoordinates(float x, float y) {
        woodCutterController.setClickCoordinates(x, y);
    }

    public void update(float delta) {
        woodCutterController.update();
        randWoodCutRefreshTime -= delta;
        if (randWoodCutRefreshTime <= 0) {
            randWoodCutRefreshTime = 300;
            for (int i = 0; i < randomWoodCutterControllers.size(); i++) {
                randomWoodCutterControllers.get(i).setClickCoordinates(MathUtils.random(200, 3200), MathUtils.random(300,1900));
            }
        }
        for (int i = 0; i < randomWoodCutterControllers.size(); i++) {
            randomWoodCutterControllers.get(i).update();
        }
        world.sortWoodCutters();
    }
}
