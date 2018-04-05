package com.gdx.screens;

import com.gdx.common.SprHamster;
import com.gdx.common.SprGhost;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gdx.common.SprMap;
import com.gdx.hamsters.GamHamsters;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gdx.common.SprPellet;

import java.util.ArrayList;

public class ScrPlay implements Screen, InputProcessor {

    SpriteBatch batch;
    int nGhostdX, nGhostdY, nHamDir, nRanGhostMove, nI, nX, nY;
    boolean bMovement, bIsHit;
    OrthographicCamera ocCam;
    SprGhost sprGhost;
    SprHamster sprHamster;
    GamHamsters gamHamsters;
    SprMap sprMap;
    ArrayList<SprPellet> alPellets = new ArrayList<SprPellet>();

    public ScrPlay(GamHamsters _gamhamsters) {
        batch = new SpriteBatch();
        sprGhost = new SprGhost(306, 253, 25, 25);
        sprHamster = new SprHamster(308, 196, 25, 25);
        nGhostdX = 0;
        nGhostdY = 0;
        sprMap = new SprMap();
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
        sprMap.wallMaker();
//        nX = 0;
//        nY = 400;
//        for (nI = 0; nI < 50; nI++) {
//            alPellets.add(new SprPellet(nX, nY));
//            // https://gamedev.stackexchange.com/questions/89985/how-to-remove-game-objects-after-on-overlap-in-libgdx-game
//            // https://beginnersbook.com/2013/12/java-arraylist/
//            nX = nX + 50;
//            if (nX >= Gdx.graphics.getWidth()) {
//                nX = 100;
//                nY = nY - 100;
//            }
//        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nRanGhostMove = (int) (Math.random() * 50 + 1);
        if (nRanGhostMove == 1) {
            sprGhost.GhostDirection();
        }
        sprGhost.Movement();
        sprHamster.Movement(nHamDir);
//        bHamsterOOB = isOutOfBounds(sprHamster);
//        if (bHamsterOOB == true) {
//            sprHamster.OOB();
//        }
//        while (bGhostOOB == true) {
//            sprGhost.OOB();
//            sprGhost.GhostDirection();
//            sprGhost.Movement();
//            bGhostOOB = isOutOfBounds(sprGhost);
        sprMap.hHitWall(sprHamster);
        sprMap.gHitWall(sprGhost);
        warpingEdge(sprHamster);
        warpingEdge(sprGhost);
        bIsHit = isHit(sprHamster, sprGhost);
        if (bIsHit) {
            gamHamsters.updateState(2);
        }
        batch.begin();
        sprGhost.draw(batch);
        sprHamster.draw(batch);
        sprMap.draw(batch);
        batch.end();
    }

    public boolean isHit(Sprite spr1, Sprite spr2) {
        return spr1.getBoundingRectangle().overlaps(spr2.getBoundingRectangle());
    }

    public static void warpingEdge(Sprite spr1) {
        float fHalfWidth = spr1.getWidth() / 2, fX = spr1.getX(), fScreenWidth = Gdx.graphics.getWidth();
        if (fX + fHalfWidth < 0) {
            spr1.setX(fScreenWidth - fHalfWidth - 1);
        } else if (fX + fHalfWidth > fScreenWidth) {
            spr1.setX(0 - fHalfWidth + 1);
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
