/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.hamsters.GamHamsters;

/**
 *
 * @author forrd6685
 */
public class ScrSwitch2 implements Screen, InputProcessor {

    Texture tx;
    SpriteBatch batch;
    Sprite spr;
    GamHamsters gamHamsters;

    public ScrSwitch2(GamHamsters _gamhamsters) {
        batch = new SpriteBatch();
        tx = new Texture("two.png");
        spr = new Sprite(tx);
        gamHamsters = _gamhamsters;
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(spr, 400, 400);
        batch.end();

    }

    @Override
    public void show() {
        System.out.println("You switched to Screen 2!");
        System.out.println("Press Backspace to go back to the Menu.");
        Gdx.input.setInputProcessor(this);
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
    public boolean keyDown(int i) {
        if (i == Input.Keys.BACKSPACE) {
            gamHamsters.updateState(5);
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