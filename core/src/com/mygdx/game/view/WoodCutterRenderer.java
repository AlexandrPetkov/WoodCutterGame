package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.WoodCutter;

import java.util.ArrayList;

class WoodCutterRenderer {

    private static final String ANIM_WOODCUTTER = "anim_woodcutter_";
    private static final String DOT_PNG = ".png";

    private Texture zombieStandImg;
    private Texture zombieWalkUpImg;
    private Texture zombieWalkDownImg;
    private Animation zombieWalkUpAnim;
    private Animation zombieWalkDownAnim;
    private TextureRegion currentWoodCutterImg;

    private ArrayList<WoodCutterClothesRenderer> clothRenderer;
    private ArrayList<WoodCutterClothesRenderer> hatRenderer;


    WoodCutterRenderer() {
        clothRenderer = new ArrayList<WoodCutterClothesRenderer>();
        hatRenderer = new ArrayList<WoodCutterClothesRenderer>();
        clothRenderer.add(new WoodCutterClothesRenderer("cloth"));
        clothRenderer.add(new WoodCutterClothesRenderer("double_cloth"));

    //    standImg = new Texture("anim_woodcutter_double_cloth_stand/anim_woodcutter_double_cloth_stand.png");
  //      walkUpImg = new Texture("anim_woodcutter_double_cloth_walk_up/anim_woodcutter_double_cloth_walk_up.png");
//        walkDownImg = new Texture("anim_woodcutter_double_cloth_walk_down/anim_woodcutter_double_cloth_walk_down.png");


        hatRenderer.add(new WoodCutterClothesRenderer("hat"));

        hatRenderer.add(new WoodCutterClothesRenderer("double_hat"));

        zombieStandImg = new Texture(Gdx.files.internal(ANIM_WOODCUTTER+"stand/"+ANIM_WOODCUTTER+"stand"+DOT_PNG));
        zombieWalkUpImg = new Texture(Gdx.files.internal(ANIM_WOODCUTTER+"walk_up/"+ANIM_WOODCUTTER+"walk_up"+DOT_PNG));
        zombieWalkDownImg = new Texture(Gdx.files.internal(ANIM_WOODCUTTER+"walk_down/"+ANIM_WOODCUTTER+"walk_down"+DOT_PNG));
        zombieWalkUpAnim = new Animation(new TextureRegion(zombieWalkUpImg), 1, 10);
        zombieWalkDownAnim = new Animation(new TextureRegion(zombieWalkDownImg), 1, 10);
    }
    void drawWoodCutter(SpriteBatch batch, WoodCutter woodCutter){
        switch (woodCutter.getMove()){
            case WALK_DOWN: currentWoodCutterImg = zombieWalkDownAnim.getFrame(woodCutter.isLookingRight());
                break;
            case WALK_UP: currentWoodCutterImg = zombieWalkUpAnim.getFrame(woodCutter.isLookingRight());
                break;
            default: currentWoodCutterImg = new TextureRegion(zombieStandImg);
        }
        batch.draw(currentWoodCutterImg, woodCutter.getPosition().x - 133, woodCutter.getPosition().y - 80);
        batch.draw(hatRenderer.get(woodCutter.getHatNumberDressed()).drawWoodCutterClothes(woodCutter.getMove(), woodCutter.isLookingRight()), woodCutter.getPosition().x - 133, woodCutter.getPosition().y - 80);
        batch.draw(clothRenderer.get(woodCutter.getClothNumberDressed()).drawWoodCutterClothes(woodCutter.getMove(), woodCutter.isLookingRight()), woodCutter.getPosition().x - 133, woodCutter.getPosition().y - 80);
    }
    void update(float delta){
        zombieWalkUpAnim.update(delta);
        zombieWalkDownAnim.update(delta);
        for (int i = 0; i < clothRenderer.size(); i++) {
            clothRenderer.get(i).update(delta);
            hatRenderer.get(i).update(delta);
        }
    }

    void dispose(){
        zombieStandImg.dispose();
        zombieWalkDownImg.dispose();
        zombieWalkUpImg.dispose();

        for (WoodCutterClothesRenderer aClothRenderer : clothRenderer) {
            aClothRenderer.dispose();
        }

        for (WoodCutterClothesRenderer aHatRenderer : hatRenderer) {
            aHatRenderer.dispose();
        }
    }
}
