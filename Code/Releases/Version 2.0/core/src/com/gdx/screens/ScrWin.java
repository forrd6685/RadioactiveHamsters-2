package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.hamsters.GamHamsters;

/**
 *
 * @author forrd6685
 */
public class ScrWin implements Screen , InputProcessor {

    SpriteBatch batch;
    GamHamsters gamHamsters;
    Texture tExp, tWin, tEnter;

    public ScrWin(GamHamsters aThis) {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        tExp = new Texture("explosion.jpg");
        tWin = new Texture("win.gif");
        tEnter = new Texture("PressEnter.png");
    }
    @Override
    public void show() {

    }

    public void render(float delta) {

        batch.begin();
        batch.draw(tExp, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(tWin, Gdx.graphics.getWidth()/2 - 140, Gdx.graphics.getHeight()/2 - 200, 300, 300);
        batch.draw(tEnter, Gdx.graphics.getWidth()/2 - 75, 0,100, 100);
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
        if (keycode == Input.Keys.ENTER) {
            gamHamsters.updateState(1);
        }
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
    public boolean touchDown(int i, int i1, int i2, int i3) {
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