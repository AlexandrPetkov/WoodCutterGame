package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class World {
    private WoodCutter woodCutter;
    private GameMap gameMap;
    private ArrayList<WoodCutter> randomWoodCutters;

    public World(Vector2 position) {
        gameMap = new GameMap();
        woodCutter = new WoodCutter(position);
        randomWoodCutters = new ArrayList<WoodCutter>();
        for (int i = 0; i < 20; i++) {
            randomWoodCutters.add(new WoodCutter(new Vector2(1200 + i*40, 1000)));
        }
        for (int i = 0; i < randomWoodCutters.size(); i++) {
            randomWoodCutters.get(i).setRandomClothes();
        }
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public ArrayList<WoodCutter> getRandomWoodCutters() {
        return randomWoodCutters;
    }

    public void sortWoodCutters(){
        Collections.sort(randomWoodCutters, new Comparator<WoodCutter>() {
            @Override
            public int compare(WoodCutter o1, WoodCutter o2) {
                return o1.getBounds().getY() > o2.getBounds().getY() ? -1: 1;
            }
        });
    }

    public WoodCutter getWoodCutter() {
        return woodCutter;
    }

    public void setWhiteWavePosition(float x, float y) {
        woodCutter.getWhiteWave().setPosition(new Vector2(x, y));
    }

    public WhiteWave getWhiteWave() {
        return woodCutter.getWhiteWave();
    }

    public void changeHat() {
        woodCutter.changeHat();
    }

    public void changeClothes() {
        woodCutter.changeClothes();
    }
}
