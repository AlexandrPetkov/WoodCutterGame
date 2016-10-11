package com.mygdx.game.view.ClothesAnimations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.view.Animation;
import com.mygdx.game.view.ClothesAnimations.WoodCutterClothesRenderer;

public class ClothRenderer extends WoodCutterClothesRenderer {

    public ClothRenderer() {
        standImg = new Texture("anim_woodcutter_cloth_stand/anim_woodcutter_cloth_stand.png");
        walkUpImg = new Texture("anim_woodcutter_cloth_walk_up/anim_woodcutter_cloth_walk_up.png");
        walkDownImg = new Texture("anim_woodcutter_cloth_walk_down/anim_woodcutter_cloth_walk_down.png");
        walkUpAnim = new Animation(new TextureRegion(walkUpImg), 1, 10);
        walkDownAnim = new Animation(new TextureRegion(walkDownImg), 1, 10);
    }
}
