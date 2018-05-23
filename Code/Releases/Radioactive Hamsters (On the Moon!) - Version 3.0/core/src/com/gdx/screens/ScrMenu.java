package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.hamsters.GamHamsters;
import com.gdx.common.SprButton;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public class ScrMenu implements Screen, InputProcessor {

    SpriteBatch batch;
    GamHamsters gamHamsters;
    int nScreen;
    Texture tx;
    SprButton sprPlayButton, sprOptionsButton;


    public ScrMenu(GamHamsters _gamHamsters) {
        gamHamsters = _gamHamsters;
        batch = new SpriteBatch();
        tx = new Texture("Game Title Screen.jpg");
        sprPlayButton = new SprButton(50, 75, 146,53, "PlayButton.jpg");
        sprOptionsButton = new SprButton(Gdx.graphics.getWidth() - 196, 75 , 146, 53, "OptionsButton.jpg");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(tx, 0, 0);
        sprPlayButton.draw(batch);
        sprOptionsButton.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {
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
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            screenY = (Gdx.graphics.getHeight() - screenY);
            if (sprPlayButton.isClicked(screenX, screenY)) {
                gamHamsters.updateState(5);
            }
            if (sprOptionsButton.isClicked(screenX, screenY)) {
                gamHamsters.updateState(4);
            }

        }
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
