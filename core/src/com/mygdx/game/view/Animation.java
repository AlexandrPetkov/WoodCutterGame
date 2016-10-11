package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {

    protected Array<TextureRegion> frames;
    protected float maxFrameTime;
    protected float currentFrameTime;
    protected int frameCount;
    protected int frame;

    public Animation(TextureRegion region, float cycleTime, int frameCount) {
        frames = new Array<TextureRegion>();
        this.frameCount = frameCount;
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
            maxFrameTime = cycleTime / frameCount;
            frame = 0;
        }
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }
    }

    public TextureRegion getFrame(boolean flipX) {
        if (frames.get(frame).isFlipX() != flipX) {
            frames.get(frame).flip(true, false);
        }
        return frames.get(frame);
    }
}
