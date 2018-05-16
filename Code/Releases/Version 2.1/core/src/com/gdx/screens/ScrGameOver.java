package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.hamsters.GamHamsters;
import com.gdx.common.SprButton;

public class ScrGameOver implements Screen, InputProcessor {

    SpriteBatch batch;
    Texture txGameOver;
    GamHamsters gamHamsters;
    Texture txExp;
    SprButton sprButtonBack;

    public ScrGameOver(GamHamsters _gamHamsters) {
        batch = new SpriteBatch();
        txGameOver = new Texture("Game Over Screen.jpg");
        sprButtonBack = new SprButton (0,0,50,100,"Back.png");
        gamHamsters = _gamHamsters;

    }

    @Override
    public void show() {Gdx.input.setInputProcessor(this);}

    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(txGameOver, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprButtonBack.draw(batch);
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
            if (sprButtonBack.isClicked(screenX, screenY)) {
                gamHamsters.updateState(0);
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
