package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.WoodCutter;
import com.mygdx.game.view.ClothesAnimations.*;

import java.util.ArrayList;

public class WoodCutterRenderer {
    private Texture zombieStandImg;
    private Texture zombieWalkUpImg;
    private Texture zombieWalkDownImg;
    private Animation zombieWalkUpAnim;
    private Animation zombieWalkDownAnim;
    private TextureRegion currentWoodCutterImg;

    private ArrayList<WoodCutterClothesRenderer> clothRenderer;
    private ArrayList<WoodCutterClothesRenderer> hatRenderer;


    public WoodCutterRenderer() {
        clothRenderer = new ArrayList<WoodCutterClothesRenderer>();
        hatRenderer = new ArrayList<WoodCutterClothesRenderer>();
        clothRenderer.add(new ClothRenderer());
        clothRenderer.add(new DoubleClothRenderer());
        hatRenderer.add(new HatRenderer());
        hatRenderer.add(new DoubleHatRenderer());

        zombieStandImg = new Texture(Gdx.files.internal("anim_woodcutter_stand/anim_woodcutter_stand.png"));
        zombieWalkUpImg = new Texture(Gdx.files.internal("anim_woodcutter_walk_up/anim_woodcutter_walk_up.png"));
        zombieWalkDownImg = new Texture(Gdx.files.internal("anim_woodcutter_walk_down/anim_woodcutter_walk_down.png"));
        zombieWalkUpAnim = new Animation(new TextureRegion(zombieWalkUpImg), 1, 10);
        zombieWalkDownAnim = new Animation(new TextureRegion(zombieWalkDownImg), 1, 10);
    }
    public void drawWoodCutter(SpriteBatch batch, WoodCutter woodCutter){
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
    public void update(float delta){
        zombieWalkUpAnim.update(delta);
        zombieWalkDownAnim.update(delta);
        for (int i = 0; i < clothRenderer.size(); i++) {
            clothRenderer.get(i).update(delta);
            hatRenderer.get(i).update(delta);
        }
    }

    public ArrayList<WoodCutterClothesRenderer> getClothRenderer() {
        return clothRenderer;
    }

    public ArrayList<WoodCutterClothesRenderer> getHatRenderer() {
        return hatRenderer;
    }

    public void dispose(){
        zombieStandImg.dispose();
        zombieWalkDownImg.dispose();
        zombieWalkUpImg.dispose();
    }
}
