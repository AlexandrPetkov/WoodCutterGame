package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.WoodCutter;
import com.mygdx.game.view.Animation;

class WoodCutterClothesRenderer {
    private Texture standImg;
    private Texture walkUpImg;
    private Texture walkDownImg;
    private Animation walkUpAnim;
    private Animation walkDownAnim;

    WoodCutterClothesRenderer(String clothType) {

        this.standImg = new Texture("anim_woodcutter_"+clothType+"_stand/anim_woodcutter_"+clothType+"_stand.png");
        this.walkUpImg = new Texture("anim_woodcutter_"+clothType+"_walk_up/anim_woodcutter_"+clothType+"_walk_up.png");
        this.walkDownImg = new Texture("anim_woodcutter_"+clothType+"_walk_down/anim_woodcutter_"+clothType+"_walk_down.png");
        walkUpAnim = new Animation(new TextureRegion(this.walkUpImg), 1, 10);
        walkDownAnim = new Animation(new TextureRegion(this.walkDownImg), 1, 10);
    }

    TextureRegion drawWoodCutterClothes (WoodCutter.Move move, boolean isLookingRight) {
        switch (move) {
            case WALK_DOWN:
                return walkDownAnim.getFrame(isLookingRight);
            case WALK_UP:
                return walkUpAnim.getFrame(isLookingRight);
            default:
                return new TextureRegion(standImg);
        }
    }

    void update(float delta){
        walkUpAnim.update(delta);
        walkDownAnim.update(delta);
    }

    void dispose(){
        standImg.dispose();
        walkDownImg.dispose();
        walkUpImg.dispose();
    }
}
