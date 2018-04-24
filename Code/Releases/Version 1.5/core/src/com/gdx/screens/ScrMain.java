package com.gdx.screens;

import com.gdx.common.SprHamster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gdx.hamsters.GamHamsters;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gdx.common.SprPellet;
import com.gdx.sprites.SprGhost;
import java.util.ArrayList;

public class ScrMain implements Screen, InputProcessor {

    SpriteBatch batch;
    int nGhostDirOld, nGhostDirNew, nGhostdX, nGhostdY, nHamDir, nRanGhostMove, nI, nX, nY;
    boolean bMovement, bGhostOOB, bGhost2OOB, bHamsterOOB;
    OrthographicCamera ocCam;
    SprGhost sprGhost, sprGhost2;
    SprHamster sprHamster;
    GamHamsters gamHamsters;
    ArrayList<SprPellet> alPellets = new ArrayList<SprPellet>();

    public ScrMain(GamHamsters _gamhamsters) {
        batch = new SpriteBatch();
        sprGhost = new SprGhost(375, 300, 30, 30);
        sprGhost2 = new SprGhost(100, 250, 30, 30);
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
        nRanGhostMove = (int) (Math.random() * 50 + 1);

        if (nRanGhostMove == 1) {
            nGhostDirOld = nGhostDirNew;
            nGhostDirNew = sprGhost.GhostDirection(nGhostDirOld, nGhostDirNew);
        }
        sprGhost.Movement(nGhostDirNew);
        nRanGhostMove = (int) (Math.random() * 50 + 1);
        if (nRanGhostMove == 1) {
            nGhostDirOld = nGhostDirNew;
            nGhostDirNew = sprGhost.GhostDirection(nGhostDirOld, nGhostDirNew);
        }
        sprGhost2.Movement(nGhostDirNew);
        sprHamster.Movement(nHamDir);
        bGhostOOB = isOutOfBounds(sprGhost);
        while (bGhostOOB == true) {
            sprGhost.OOB();
            nGhostDirOld = nGhostDirNew;
            nGhostDirNew = sprGhost.GhostDirection(nGhostDirOld, nGhostDirNew);
            sprGhost.Movement(nGhostDirNew);
            bGhostOOB = isOutOfBounds(sprGhost);
        }
        bGhost2OOB = isOutOfBounds(sprGhost2);
        while (bGhost2OOB == true) {
            sprGhost2.OOB();
            nGhostDirOld = nGhostDirNew;
            nGhostDirNew = sprGhost2.GhostDirection(nGhostDirOld, nGhostDirNew);
            sprGhost2.Movement(nGhostDirNew);
            bGhost2OOB = isOutOfBounds(sprGhost2);
        }
        bHamsterOOB = isOutOfBounds(sprHamster);
        if (bHamsterOOB == true) {
            sprHamster.OOB();
        }
        if (isHit(sprGhost, sprHamster) == true || isHit(sprGhost2, sprHamster) == true) {
            gamHamsters.updateState(2);
        }
        batch.begin();
        sprGhost.draw(batch);
        sprGhost2.draw(batch);
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
