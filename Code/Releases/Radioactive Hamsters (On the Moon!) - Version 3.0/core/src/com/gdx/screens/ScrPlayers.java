package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.common.SprButton;
import com.gdx.hamsters.GamHamsters;
import com.gdx.screens.ScrPlay;

public class ScrPlayers implements Screen, InputProcessor {

    SpriteBatch batch;
    Texture txPlayer;
    GamHamsters gamHamsters;
    SprButton sprButtonOne, sprButtonTwo;

    public ScrPlayers(GamHamsters _gamHamsters) {
        batch = new SpriteBatch();
        txPlayer = new Texture("PlayerSelect.jpg");
        sprButtonOne = new SprButton(63, 209, 146, 53, "One.jpg");
        sprButtonTwo = new SprButton(239 , 209, 146, 53, "Two.jpg");
        gamHamsters = _gamHamsters;

    }

    @Override
    public void show() {Gdx.input.setInputProcessor(this);}

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(txPlayer, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprButtonOne.draw(batch);
        sprButtonTwo.draw(batch);
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
            if (sprButtonOne.isClicked(screenX, screenY)) {
                gamHamsters.bMultiplayer = false;
                gamHamsters.updateState(1);
            }
            if (sprButtonTwo.isClicked(screenX, screenY)) {
                gamHamsters.bMultiplayer = true;
                gamHamsters.updateState(1);
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
