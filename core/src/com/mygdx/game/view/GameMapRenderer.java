package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Tile;

import java.util.ArrayList;

public class GameMapRenderer {
    private ArrayList<Tile> tiles;

    private Texture atlasImg;

    public GameMapRenderer(ArrayList<Tile> tiles) {
        this.tiles = tiles;
        atlasImg = new Texture("main_island/img/main_island_atlas.png");

    }


    public void drawGameMap(SpriteBatch batch){
        for (int i = 0; i < tiles.size(); i++) {
            batch.draw(atlasImg,
                    tiles.get(i).getX(),
                    tiles.get(i).getY(),
                    tiles.get(i).getWidth(),
                    tiles.get(i).getHeight(),
                    tiles.get(i).getPosX(),
                    tiles.get(i).getPosY(),
                    tiles.get(i).getWidth(),
                    tiles.get(i).getHeight(),
                    tiles.get(i).isFlipHorizontal(),
                    tiles.get(i).isFlipVertical());
        }
    }

    public void dispose(){
        atlasImg.dispose();
    }
}
