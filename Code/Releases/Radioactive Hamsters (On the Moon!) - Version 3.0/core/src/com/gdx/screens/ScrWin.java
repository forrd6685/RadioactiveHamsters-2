package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.hamsters.GamHamsters;
import com.gdx.common.SprButton;
import com.gdx.common.SpriteSheetAnimator;
import com.gdx.common.AniMoonExplode;

public class ScrWin implements Screen, InputProcessor {

    SpriteBatch batch;
    GamHamsters gamHamsters;
    Texture txWin;
    Sprite sprTemp;
    SprButton sprButtonBack;
    int nI;
    AniMoonExplode aniMoonExplode;


    public ScrWin(GamHamsters _gamHamsters) {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        aniMoonExplode = new AniMoonExplode(0, 0);
        txWin = new Texture("win.gif");
        sprButtonBack = new SprButton(151, 71, 146,53, "Back.jpg");
        gamHamsters = _gamHamsters;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    public void render(float delta) {
        batch.begin();
        sprTemp = aniMoonExplode.animate();
        sprTemp.draw(batch);
        if(nI < 60) {
            nI++;
        } else {
            batch.draw(txWin, Gdx.graphics.getWidth() / 2 - 70, Gdx.graphics.getHeight() / 2 - 50, 150, 150);
            sprButtonBack.draw(batch);
        }
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
            if (sprButtonBack.isClicked(screenX, screenY)) {
                aniMoonExplode.reset();
                nI=0;
                gamHamsters.updateState(0);
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