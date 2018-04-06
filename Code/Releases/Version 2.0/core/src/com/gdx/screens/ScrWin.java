package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.hamsters.GamHamsters;

/**
 *
 * @author forrd6685
 */
public class ScrWin implements Screen {

    SpriteBatch batch;
    Texture tExp, tWin;

    public ScrWin(GamHamsters aThis) {
        batch = new SpriteBatch();
        tExp = new Texture("explosion.jpg");
        tWin = new Texture("win.gif");

    }

    public void render(float delta) {
        batch.begin();
        batch.draw(tExp, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(tWin, Gdx.graphics.getWidth()/2 - 140, Gdx.graphics.getHeight()/2 - 200, 300, 300);
        batch.end();
    }

    @Override
    public void show() {
  
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
}