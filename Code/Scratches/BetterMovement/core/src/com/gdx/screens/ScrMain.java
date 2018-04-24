package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.common.SprHamster;
import com.gdx.common.SprMap;
import com.gdx.hamsters.GamHamsters;

import java.io.FileNotFoundException;


public class ScrMain implements Screen, InputProcessor {

    SpriteBatch batch;
    int nGhostdX, nGhostdY, nHamDir, nX1, nY1;
    boolean bMovement, bHitWall;
    OrthographicCamera ocCam;
    SprHamster sprHamster;
    GamHamsters gamHamsters;
    SprMap sprMap;
    int wantMove;

    public ScrMain(GamHamsters _gamhamsters) {
        batch = new SpriteBatch();
        nGhostdX = 0;
        nGhostdY = 0;
        bMovement = false;
        bHitWall = false;
        nGhostdX = 0;
        nGhostdY = 0;
        ocCam = new OrthographicCamera();
        sprMap = new SprMap();
        sprHamster = new SprHamster(215, 272, 15, 15);
        gamHamsters = _gamhamsters;
    }

    @Override
    public void show() {
        try {
            sprMap.mapMaker();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.update();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(ocCam.combined);
//        for (int nNum = 0; nNum < arSprGhosts.size(); nNum++) {
//            arSprGhosts[nNum].move();
//            sprMap.gHitWall(arSprGhosts[nNum]);
//            warpingEdge(arSprGhosts[nNum]);
//        }
//        sprHamster.tryMove(nHamDir);
        sprHamster.move(wantMove, sprMap);
        sprMap.hHitWall(sprHamster);
        sprMap.warpingEdge(sprHamster);


//        sprHamster.bWallHit = sprMap.hHitWall(sprHamster);

        batch.begin();
        sprHamster.draw(batch);
        sprMap.draw(batch);
        for (int nI = 0; nI < sprMap.alSprMainWalls.size(); nI++) {
            sprMap.alSprMainWalls.get(nI).draw(batch);
        }
        for (int nI = 0; nI < sprMap.alSprGhostHouse.size(); nI++) {
            sprMap.alSprGhostHouse.get(nI).draw(batch);
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
        bMovement = true;
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            wantMove = 1;
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            wantMove = 2;
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            wantMove = 3;
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            wantMove = 4;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;

    }

    @Override
    public boolean touchDown(int nScreenX, int nScreenY, int pointer, int button) {
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
