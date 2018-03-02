package com.gdx.collection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gdx.hamsters.GamHamsters;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import static com.gdx.scratches.SprGhost.horizontal;
import static com.gdx.scratches.ScrMain.isOutOfBounds;
import static com.gdx.scratches.SprGhost.vertical;

public class ScrCollection implements Screen, InputProcessor {

    SpriteBatch batch;
    int nHamDir, nHamVorH, nHamdX, nHamdY, nI, nX, nY;
    boolean bHamsterOutOfBounds, bCollected, bHit;
    OrthographicCamera ocCam;
    SprHamster sprHamster;
    SprPellet arPellets[] = new SprPellet[10];
    GamHamsters game;

    public ScrCollection(GamHamsters aThis) {
        batch = new SpriteBatch();
        sprHamster = new SprHamster(100, 100, 30, 30);
        bCollected = false;
        Gdx.input.setInputProcessor(this);
        ocCam = new OrthographicCamera();
        game = aThis;

    }

    @Override
    public void show() {
        ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.update();
        nX = 100;
        nY = 400;
        for (nI = 0; nI < 10; nI++) {
            arPellets[nI] = new SprPellet(nX, nY);
            nX = nX + 50;
            
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nHamVorH = nHamDir % 2;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            nHamDir = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            nHamDir = 2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            nHamDir = 3;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            nHamDir = 4;
        }

        if (nHamVorH == 0) {
            nHamdX = horizontal(nHamDir, nHamdY);
            nHamdY = 0;
        } else {
            nHamdY = vertical(nHamDir, nHamdX);
            nHamdX = 0;
        }
        sprHamster.setX(sprHamster.getX() + nHamdX);
        sprHamster.setY(sprHamster.getY() + nHamdY);
        bHamsterOutOfBounds = isOutOfBounds(sprHamster);
        if (bHamsterOutOfBounds == true) {
            sprHamster.setX(sprHamster.getX() - nHamdX);
            sprHamster.setY(sprHamster.getY() - nHamdY);
        }
        

        batch.begin();
        sprHamster.draw(batch);
        for (nI = 0; nI < 10; nI++) {
            arPellets[nI].draw(batch);
            if(isHit(sprHamster, arPellets[nI])) {
                GamHamsters.updateState(3);
                System.out.println("Yum");
            }
        }
        batch.end();
    }

    public boolean isHit(Sprite spr1, Sprite spr2) {
        return spr1.getBoundingRectangle().overlaps(spr2.getBoundingRectangle());
    }

    public static boolean isOutOfBounds(Sprite spr1) {
        if (0 < spr1.getX() && spr1.getX() + spr1.getWidth() < Gdx.graphics.getWidth() && 0 < spr1.getY() && spr1.getY() + spr1.getHeight() < Gdx.graphics.getHeight()) {
            return false;
        } else {
            return true;
        }
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
        nHamDir = 0;
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