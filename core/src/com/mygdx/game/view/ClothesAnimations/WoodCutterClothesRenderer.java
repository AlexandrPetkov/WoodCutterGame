package com.mygdx.game.view.ClothesAnimations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.WoodCutter;
import com.mygdx.game.view.Animation;

/**
 * Created by Саша on 05.09.2016.
 */
public abstract class WoodCutterClothesRenderer {
    protected Texture standImg;
    protected Texture walkUpImg;
    protected Texture walkDownImg;
    protected Animation walkUpAnim;
    protected Animation walkDownAnim;

    public TextureRegion drawWoodCutterClothes (WoodCutter.Move move, boolean isLookingRight) {
        switch (move) {
            case WALK_DOWN:
                return walkDownAnim.getFrame(isLookingRight);
            case WALK_UP:
                return walkUpAnim.getFrame(isLookingRight);
            default:
                return new TextureRegion(standImg);
        }
    }

    public void update(float delta){
        walkUpAnim.update(delta);
        walkDownAnim.update(delta);
    }

    public void dispose(){
        standImg.dispose();
        walkDownImg.dispose();
        walkUpImg.dispose();
    }
}
