package com.mygdx.game.controller;

import com.mygdx.game.model.GameMap;
import com.mygdx.game.model.WoodCutter;

public class WoodCutterController {
    private static final int SPEED = 1;
    private GameMap gameMap;
    private WoodCutter woodCutter;
    private int whiteWavePosX;
    private int whiteWavePosY;


    public WoodCutterController(WoodCutter woodCutter, GameMap gameMap) {
        this.gameMap = gameMap;
        this.woodCutter = woodCutter;
        whiteWavePosX = (int) woodCutter.getPosition().x;
        whiteWavePosY = (int) woodCutter.getPosition().y;
    }

    public void setClickCoordinates(float x, float y){
        for (int i = 0; i < gameMap.getTilesNotStand().size(); i++) {
            if (gameMap.getTilesNotStand().get(i).contains(x, y)) {
                return;
            }
        }
        woodCutter.setWhiteWavePosition(x, y);
        whiteWavePosX = (int) x;
        whiteWavePosY = (int) y;
        update();
    }

    public void update() {
        float distanceX = whiteWavePosX - woodCutter.getPosition().x;
        float distanceY = whiteWavePosY - woodCutter.getPosition().y;

        if (Math.abs(distanceX) < SPEED && Math.abs(distanceY) < SPEED) {
            woodCutterStand();
        } else {
            if (distanceX < 0) {
                woodCutterMoveLeft();
            } else if (distanceX > 0) {
                woodCutterMoveRight();
            }
            if (distanceY < 0) {
                woodCutterMoveDown();
            } else if (distanceY > 0) {
                woodCutterMoveUp();
            }
        }
    }

    private void woodCutterMoveUp() {
        woodCutter.add(0, SPEED);
        woodCutter.setMove(WoodCutter.Move.WALK_UP);
    }

    private void woodCutterMoveDown() {
        woodCutter.add(0, -SPEED);
        woodCutter.setMove(WoodCutter.Move.WALK_DOWN);

    }

    private void woodCutterMoveRight() {
        woodCutter.add(SPEED, 0);
        woodCutter.setLookingRight(true);
        if (woodCutter.getMove().equals(WoodCutter.Move.STAND))
            woodCutter.setMove(WoodCutter.Move.WALK_DOWN);
    }

    private void woodCutterMoveLeft() {
        woodCutter.add(-SPEED, 0);
        woodCutter.setLookingRight(false);
        if (woodCutter.getMove().equals(WoodCutter.Move.STAND))
            woodCutter.setMove(WoodCutter.Move.WALK_UP);
    }

    private void woodCutterStand() {
        woodCutter.setMove(WoodCutter.Move.STAND);
    }
}
