package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.buttons.Buttons;
import com.gdx.sprites.SprButton;
import com.badlogic.gdx.graphics.Texture;

public class ScrMain implements Screen, InputProcessor {

    SpriteBatch batch;
    Buttons buttons;
//    SprButton sprButtonPlay = new SprButton(100, 100, 100, 100, "button1.jpg");
    Texture tx = new Texture("Game Title Screen.jpg");
    SprButton sprPlayButton, sprOptionsButton;


    public ScrMain(Buttons _buttons) {
        batch = new SpriteBatch();
        sprPlayButton = new SprButton(50, 75,146,53, "PlayButton.jpg");
        sprOptionsButton = new SprButton(Gdx.graphics.getWidth() - 196, 75 , 146, 53, "OptionsButton.jpg");
                //        sprButtonPaul = new SprButton(100, 100, 200, 100, "button1.png");
//        sprButtonPat = new SprButton(100, 100, 300, 300, "button2.jpg");
//        sprButtonExp = new SprButton(100, 100, 100, 10, "button3.png");
        buttons = _buttons;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(tx, 0, 0);
        sprPlayButton.draw(batch);
        sprOptionsButton.draw(batch);
        batch.end();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

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
        if (button == Input.Buttons.LEFT) {
            screenY = (Gdx.graphics.getHeight() - screenY);
            if (sprPlayButton.isClicked(screenX, screenY)) {
                buttons.updateState(1);
            }
            if (sprOptionsButton.isClicked(screenX, screenY)) {
                buttons.updateState(2);
            }

        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
