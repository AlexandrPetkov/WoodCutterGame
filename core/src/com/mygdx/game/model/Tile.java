package com.mygdx.game.model;

public class Tile {
    private int x, y, height, width, index, posX, posY;
    private boolean flipHorizontal, flipVertical;

    public Tile(int x, int y, int height, int width, int index, boolean flipHorizontal, boolean flipVertical) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.index = index;
        this.posX = ((index- 1) % 7) * 100 + (((index- 1) % 7) *2 +1) * 2;
        this.posY = ((index-1) / 7) * 100 + (((index - 1) / 7) *2 +1) * 2;
        this.flipHorizontal = flipHorizontal;
        this.flipVertical = flipVertical;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getIndex() {
        return index;
    }

    public boolean isFlipHorizontal() {
        return flipHorizontal;
    }

    public boolean isFlipVertical() {
        return flipVertical;
    }
}
