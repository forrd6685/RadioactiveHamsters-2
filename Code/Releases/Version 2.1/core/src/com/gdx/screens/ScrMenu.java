package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.hamsters.GamHamsters;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public class ScrMenu implements Screen, InputProcessor {

    String sMenu;
    GamHamsters gamHamster;
    int nScreen;
    Sprite sp;
    Texture tx;
    SpriteBatch batch;

    public ScrMenu(GamHamsters _gamHamster) {
        gamHamster = _gamHamster;
        batch = new SpriteBatch();
        tx = new Texture("PressEnter.png");
        sp = new Sprite(tx);
        sp.setX(Gdx.graphics.getWidth() / 2 - sp.getWidth() / 2);
        sp.setY(Gdx.graphics.getHeight() / 2 - sp.getHeight() / 2);
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
        sp.draw(batch);
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
            gamHamster.updateState(1);
//        
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
