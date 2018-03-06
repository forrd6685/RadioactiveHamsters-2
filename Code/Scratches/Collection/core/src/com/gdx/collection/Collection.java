package com.gdx.collection;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.sprites.SprHamster;
import com.gdx.sprites.SprPellet;
import java.util.ArrayList;

public class Collection extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    int nHamDir, nHamVorH, nHamdX, nHamdY, nI, nX, nY, nPelletAmt;
    boolean bHamsterOutOfBounds, bCollected, bHit;
    OrthographicCamera ocCam;
    SprHamster sprHamster;
    ArrayList<SprPellet> Pellets = new ArrayList<SprPellet>();

    public void create() {
        batch = new SpriteBatch();
        sprHamster = new SprHamster(100, 100, 30, 30);
        bCollected = false;
        Gdx.input.setInputProcessor(this);
        ocCam = new OrthographicCamera();
        ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.update();
        nX = 100;
        nY = 400;
        for (nI = 0; nI < 10; nI++) {
            Pellets.add(new SprPellet(nX, nY));
            // https://gamedev.stackexchange.com/questions/89985/how-to-remove-game-objects-after-on-overlap-in-libgdx-game
            // https://beginnersbook.com/2013/12/java-arraylist/
            nX = nX + 50;
            nPelletAmt++;
        }
    }

    @Override
    public void render() {
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
        sprHamster.Movement(nHamDir);
        bHamsterOutOfBounds = isOutOfBounds(sprHamster);
        if (bHamsterOutOfBounds == true) {
            sprHamster.OOB();
        }


        batch.begin();
        sprHamster.draw(batch);
        for (nI = 0; nI < 10; nI++) {
            for (int nJ = 0; nJ < Pellets.size; nJ++) {
                Pellets.get(nJ).draw(batch);
                if (isHit(sprHamster, Pellets)) {
                System.out.println("Yum");
                nPelletAmt--;
                Pellets.remove(nJ);
            }
            
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
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        nHamDir = 0;
        return true;
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
