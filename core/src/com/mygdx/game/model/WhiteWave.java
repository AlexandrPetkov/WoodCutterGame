package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WhiteWave {
    private static final int WIDTH = 66;
    private static final int HEIGHT = 35;

    private Vector2 position;
    private Rectangle bounds;

    public WhiteWave(Vector2 position) {
        this.position = position;
        bounds = new Rectangle(position.x - WIDTH / 2, position.y - HEIGHT / 2, WIDTH, HEIGHT);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        bounds.setCenter(position);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
