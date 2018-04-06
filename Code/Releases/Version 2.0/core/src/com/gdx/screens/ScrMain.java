package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.common.SprGhost;
import com.gdx.common.SprHamster;
import com.gdx.common.SprPellet;
import com.gdx.hamsters.GamHamsters;

import java.util.ArrayList;

public class ScrMain implements Screen, InputProcessor {

    SpriteBatch batch;
    int nGhostdX, nGhostdY, nHamDir, nI, nX, nY;
    boolean bMovement, bHamsterOOB;
    OrthographicCamera ocCam;
    SprGhost arGhost[] = new SprGhost[4];
    SprHamster sprHamster;
    GamHamsters gamHamsters;
    ArrayList<SprPellet> alPellets = new ArrayList<SprPellet>();

    public ScrMain(GamHamsters _gamhamsters) {
        batch = new SpriteBatch();
        sprHamster = new SprHamster(100, 100, 30, 30);
        nGhostdX = 0;
        nGhostdY = 0;
        bMovement = false;
        ocCam = new OrthographicCamera();
        gamHamsters = _gamhamsters;
    }

    @Override
    public void show() {
        ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.update();
        Gdx.input.setInputProcessor(this);
        arGhost[0] = new SprGhost(375, 300, 30, 30);
        arGhost[1] = new SprGhost(100, 250, 30, 30);
        arGhost[2] = new SprGhost(50, 250, 30, 30);
        arGhost[3] = new SprGhost(200, 50, 30, 30);
        nX = 0;
        nY = 400;
        for (nI = 0; nI < 50; nI++) {
            alPellets.add(new SprPellet(nX, nY));
            // https://gamedev.stackexchange.com/questions/89985/how-to-remove-game-objects-after-on-overlap-in-libgdx-game
            // https://beginnersbook.com/2013/12/java-arraylist/
            nX = nX + 50;
            if (nX >= Gdx.graphics.getWidth()) {
                nX = 100;
                nY = nY - 100;
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprHamster.Movement(nHamDir);
        for (int nNum = 0; nNum <= 3; nNum++) {
            arGhost[nNum].Movement();
            arGhost[nNum].isOut = isOutOfBounds(arGhost[nNum]);
        }
        batch.begin();
        for (int nNum2 = 0; nNum2 <= 3; nNum2++) {
            arGhost[nNum2].draw(batch);
        }
        batch.end();
        // nNum and nNum2 are placeholders for the loop
        bHamsterOOB = isOutOfBounds(sprHamster);
        if (bHamsterOOB == true) {
            sprHamster.OOB();
        }
        batch.begin();

        sprHamster.draw(batch);
        for (int nJ = 0; nJ < alPellets.size(); nJ++) {
            if (isHit(sprHamster, alPellets.get(nJ))) {
                SprPellet.isDead = true;
            }
            if (SprPellet.isDead == true) {
                alPellets.remove(nJ);
                System.out.println(alPellets.size());
            } else {
                alPellets.get(nJ).draw(batch);
            }

            if (alPellets.isEmpty()) {
                gamHamsters.updateState(3);
            }
            SprPellet.isDead = false;
        }

        batch.end();
    }



    public boolean isHit(Sprite sprGhost, Sprite spr2) {
        return sprGhost.getBoundingRectangle().overlaps(spr2.getBoundingRectangle());
    }

    public static boolean isOutOfBounds(Sprite spr) {
        if (0 < spr.getX() && spr.getX() + spr.getWidth() < Gdx.graphics.getWidth() && 0 < spr.getY() && spr.getY() + spr.getHeight() < Gdx.graphics.getHeight()) {
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
        bMovement = true;
        if (keycode == Input.Keys.W) {
            nHamDir = 1;
        } else if (keycode == Input.Keys.D) {
            nHamDir = 2;
        } else if (keycode == Input.Keys.S) {
            nHamDir = 3;
        } else if (keycode == Input.Keys.A) {
            nHamDir = 4;
        }
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
