package com.mygdx.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WoodCutter {

    public enum Move {
        STAND, WALK_UP, WALK_DOWN
    }
    private static final int BOUNDS_SIZE = 40;

    private WhiteWave whiteWave;
    private Move move;
    private Vector2 position;
    private Rectangle bounds;
    private boolean isLookingRight;

    private int hatNumberDressed;
    private int clothNumberDressed;

    public WoodCutter(Vector2 position) {
        this.position = position;
        whiteWave = new WhiteWave(position);
        bounds = new Rectangle(position.x - BOUNDS_SIZE / 2, position.y - BOUNDS_SIZE / 2, BOUNDS_SIZE, BOUNDS_SIZE);
        isLookingRight = false;
        hatNumberDressed = 0;
        clothNumberDressed = 0;
        move = Move.STAND;
    }

    public void setWhiteWavePosition(float x, float y){
        whiteWave.setPosition(new Vector2(x, y));
    }

    public int getHatNumberDressed() {
        return hatNumberDressed;
    }

    public int getClothNumberDressed() {
        return clothNumberDressed;
    }

    public void changeHat() {
        if (hatNumberDressed == 1){
            hatNumberDressed=0;
        } else hatNumberDressed++;
    }

    public void changeClothes() {
        if (clothNumberDressed == 1){
            clothNumberDressed=0;
        } else clothNumberDressed++;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        bounds.setCenter(position);
    }

    public void add (float x, float y){
        this.position.add(x, y);
        bounds.setCenter(position);
    }

    public WhiteWave getWhiteWave() {
        return whiteWave;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public boolean isLookingRight() {
        return isLookingRight;
    }

    public void setLookingRight(boolean lookingRight) {
        isLookingRight = lookingRight;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setRandomClothes(){
        hatNumberDressed = MathUtils.random(0,1);
        clothNumberDressed = MathUtils.random(0,1);
    }
}
