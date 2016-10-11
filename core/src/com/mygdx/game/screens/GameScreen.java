package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.WorldController;
import com.mygdx.game.model.World;
import com.mygdx.game.model.GameMap;
import com.mygdx.game.view.WorldRenderer;

public class GameScreen implements Screen, InputProcessor {
    final MyGdxGame game;
    private World world;
    private WorldRenderer renderer;
    private WorldController controller;
    private OrthographicCamera camera;
    private TextButton hatChangeButton;
    private TextButton clothChangeButton;
    private Skin skin;

    // переменные для скроллирования карты
    private boolean isScreenDragged;
    private int pointerX;
    private int pointerY;
    private float zoom;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        hatChangeButton = new TextButton("Change hat", skin);
        clothChangeButton = new TextButton("Change cloth", skin);
    }

    private void setCamera(float x, float y) {
        camera.position.set(x, y, 0);
        camera.update();
        buttonsUpdate();
    }

    private void buttonsUpdate() {
        float positionX = camera.position.x - camera.viewportWidth * zoom / 2;
        float positionY = camera.position.y - hatChangeButton.getHeight() + camera.viewportHeight * zoom / 2;

        hatChangeButton.setPosition(positionX, positionY);
        clothChangeButton.setPosition((positionX), positionY - hatChangeButton.getHeight() * 2);
    }

    @Override
    public void show() {
        world = new World(new Vector2(1000, 1000));
        renderer = new WorldRenderer(world);
        controller = new WorldController(world);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setCamera(world.getWoodCutter().getPosition().x, world.getWoodCutter().getPosition().y);
        Gdx.input.setInputProcessor(this);
        isScreenDragged = false;
        zoom = camera.zoom;
        buttonsUpdate();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        buttonsUpdate();
        controller.update(delta);
        renderer.update(delta);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        renderer.render(game.batch);
        hatChangeButton.draw(game.batch, 1);
        clothChangeButton.draw(game.batch, 1);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        if (camera.position.x < width * camera.zoom / 2) setCamera(width * camera.zoom / 2, camera.position.y);
        if (camera.position.x + width * camera.zoom / 2 > GameMap.GAME_MAP_WIDTH)
            setCamera(GameMap.GAME_MAP_WIDTH - width * camera.zoom / 2, camera.position.y);
        if (camera.position.y < height * camera.zoom / 2) setCamera(camera.position.x, height * camera.zoom / 2);
        if (camera.position.y + height * camera.zoom / 2 > GameMap.GAME_MAP_HEIGHT)
            setCamera(camera.position.x, GameMap.GAME_MAP_HEIGHT - height * camera.zoom / 2);


        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        skin.dispose();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!isScreenDragged) {
            int mapX = (int) (screenX * zoom + camera.position.x - camera.viewportWidth * zoom / 2);
            int mapY = (int) (camera.position.y + camera.viewportHeight * zoom / 2 - screenY * zoom);
            Rectangle hatRectangle = new Rectangle(hatChangeButton.getX(), hatChangeButton.getY(), hatChangeButton.getWidth(), hatChangeButton.getHeight());
            Rectangle clothRectangle = new Rectangle(clothChangeButton.getX(), clothChangeButton.getY(), clothChangeButton.getWidth(), clothChangeButton.getHeight());

            if (hatRectangle.contains(mapX, mapY))
                world.changeHat();
            else if (clothRectangle.contains(mapX, mapY))
                world.changeClothes();
            else controller.setClickCoordinates(mapX, mapY);
        } else {
            isScreenDragged = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isScreenDragged) {
            int directionX = (int) ((pointerX - screenX) * camera.zoom);
            int directionY = (int) ((pointerY + screenY - Gdx.graphics.getHeight()) * camera.zoom);

            //чтобы за пределы игрового мира нельзя было перетащить экран (влево)
            if (camera.position.x - camera.viewportWidth * zoom / 2 + directionX < 0) {
                directionX = (int) (camera.viewportWidth * zoom / 2 - camera.position.x);
            }

            //чтобы за пределы игрового мира нельзя было перетащить экран (вправо)
            if (camera.position.x + camera.viewportWidth * zoom / 2 + directionX > GameMap.GAME_MAP_WIDTH) {
                directionX = GameMap.GAME_MAP_WIDTH - (int) (camera.viewportWidth * zoom / 2 + camera.position.x);
            }

            //чтобы за пределы игрового мира нельзя было перетащить экран (вниз)
            if (camera.position.y - camera.viewportHeight * zoom / 2 + directionY < 0) {
                directionY = (int) (camera.viewportHeight * zoom / 2 - camera.position.y);
            }

            //чтобы за пределы игрового мира нельзя было перетащить экран (вверх)
            if (camera.position.y + camera.viewportHeight * zoom / 2 + directionY > GameMap.GAME_MAP_HEIGHT) {
                directionY = GameMap.GAME_MAP_HEIGHT - (int) (camera.viewportHeight * zoom / 2 + camera.position.y);
            }

            camera.translate(directionX, directionY);
        }
        pointerX = screenX;
        pointerY = Gdx.graphics.getHeight() - screenY;
        isScreenDragged = true;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (amount > 0 && camera.zoom <= 1) {
            camera.zoom = zoom = camera.zoom * 2;
        } else if (amount < 0 && camera.zoom >= 0.5f) {
            camera.zoom = zoom = camera.zoom / 2;
        }

        camera.update();
        return false;
    }
}
