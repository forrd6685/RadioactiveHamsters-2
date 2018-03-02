/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.hamsters.GamHamsters;

/**
 *
 * @author forrd6685
 */
public class ScrGameOver implements Screen {

    SpriteBatch batch;
    Texture tExp;

    public ScrGameOver(GamHamsters aThis) {
        batch = new SpriteBatch();
        tExp = new Texture("explosion.jpg");

    }

    public void render(float delta) {
        batch.begin();
        batch.draw(tExp, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
