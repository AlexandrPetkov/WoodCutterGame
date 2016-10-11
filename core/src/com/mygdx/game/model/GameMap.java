package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import java.io.IOException;
import java.util.ArrayList;

public class GameMap {
    public static int GAME_MAP_WIDTH;
    public static int GAME_MAP_HEIGHT;

    private ArrayList<Tile> tiles;
    private ArrayList<Rectangle> tilesNotStand;
    private ArrayList<Integer> tilesToStandIndexes;

    public GameMap() {
        tiles = new ArrayList<Tile>();
        tilesToStandIndexes = new ArrayList<Integer>();
        tilesToStandIndexes.add(6);
        tilesToStandIndexes.add(9);
        tilesToStandIndexes.add(10);
        tilesToStandIndexes.add(12);
        tilesToStandIndexes.add(13);
        tilesNotStand = new ArrayList<Rectangle>();
        mapConstructor();
        defineTilesToStand();
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Rectangle> getTilesNotStand() {
        return tilesNotStand;
    }

    private void mapConstructor(){
        try {
            XmlReader.Element root = new XmlReader().parse(Gdx.files.internal("main_island/main_island_map_config.xml"));
            GAME_MAP_WIDTH = root.getInt("tileMapWidth");
            GAME_MAP_HEIGHT = root.getInt("tileMapHeight");
            Array<XmlReader.Element> tilesList;
            tilesList = root.getChildrenByNameRecursively("Tile");

            for (int i = 0; i < tilesList.size; i++) {
                tiles.add(new Tile(tilesList.get(i).getInt("x") - 480,
                        864 - tilesList.get(i).getInt("y"),
                        tilesList.get(i).getInt("height"),
                        tilesList.get(i).getInt("width"),
                        tilesList.get(i).getInt("index"),
                        tilesList.get(i).getBoolean("flipHorizontal"),
                        tilesList.get(i).getBoolean("flipVertical")));
            }
        }catch (IOException e){
        }
    }

    private void defineTilesToStand(){
        for (int i = 0; i < tiles.size(); i++) {
            if (!tilesToStandIndexes.contains(tiles.get(i).getIndex())){
                tilesNotStand.add(new Rectangle(tiles.get(i).getX(), tiles.get(i).getY(), tiles.get(i).getWidth(), tiles.get(i).getHeight()));
            }
        }
    }
}
