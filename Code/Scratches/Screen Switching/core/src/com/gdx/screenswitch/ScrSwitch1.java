/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.screenswitch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Switch Screens
 * @author forrd6685
 */
public class ScrSwitch1 implements Screen, InputProcessor {
    Texture tx;
    SpriteBatch batch;
    ScreenSwitch ScreenSwitch;
    Sprite spr;
    boolean bKey;

    public ScrSwitch1(ScreenSwitch _screenSwitch) {
        ScreenSwitch = _screenSwitch;
        batch = new SpriteBatch();
        tx = new Texture("One.png");
        spr = new Sprite(tx);
        bKey = false;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
        System.out.println("Press 'A' to switch screens");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            ScreenSwitch.updateState(2);
        }
        batch.begin();
        batch.draw(spr, 0, 0);
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
    public boolean keyDown(int i) {
        System.out.println("KeyDown.");
        
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