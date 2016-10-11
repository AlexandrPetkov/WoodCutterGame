package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.WhiteWave;

/**
 * Created by Саша on 05.09.2016.
 */
public class WhiteWaveRenderer {
    private Texture whiteWaveImg;
    private Animation whiteWaveAnim;

    public WhiteWaveRenderer() {
        whiteWaveImg = new Texture(Gdx.files.internal("white_wave/white_wave.png"));
        whiteWaveAnim = new Animation(new TextureRegion(whiteWaveImg), 0.5f, 4);
    }

    public void drawWhiteWave(SpriteBatch batch, WhiteWave whiteWave) {
        batch.draw(whiteWaveAnim.getFrame(false), whiteWave.getBounds().getX(), whiteWave.getBounds().getY());
    }

    public void dispose(){
        whiteWaveImg.dispose();
    }

    public void update(float delta) {
        whiteWaveAnim.update(delta);
    }
}
