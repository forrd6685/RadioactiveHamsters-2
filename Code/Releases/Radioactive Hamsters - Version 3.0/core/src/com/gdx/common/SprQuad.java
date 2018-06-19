package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class SprQuad implements Screen {
    public int nQuad;

    public SprQuad(float fX, float fY) {
        nQuad = determineQuad(fX, fY);
        if (nQuad == -1) {
            System.out.println("Error");
        }
    }

    public int determineQuad(float fX, float fY) {
        if (fX >= Gdx.graphics.getWidth() / 2) {
            if (fY >= Gdx.graphics.getHeight() / 2) {
                return 4;
            } else if (fY < Gdx.graphics.getHeight() / 2) {
                return 1;
            }
            return -1;
        } else if (fX < Gdx.graphics.getWidth() / 2) {
            if (fY >= Gdx.graphics.getHeight() / 2) {
                return 3;
            } else if (fY < Gdx.graphics.getHeight() / 2) {
                return 2;
            }
            return -1;
        }
        return -1;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
}
