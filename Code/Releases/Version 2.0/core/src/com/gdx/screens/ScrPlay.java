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
import com.gdx.common.SprMap;
import com.gdx.hamsters.GamHamsters;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ScrPlay implements Screen, InputProcessor {
    SpriteBatch batch;
    int nGhostdX, nGhostdY, nWantMove, nX1, nY1;
    boolean bMovement, bIsHit;
    OrthographicCamera ocCam;
    ArrayList<SprGhost> arSprGhosts = new ArrayList<SprGhost>();
    SprHamster sprHamster;
    GamHamsters gamHamsters;
    SprMap sprMap;

    public ScrPlay(GamHamsters _gamhamsters) {
        batch = new SpriteBatch();
        nGhostdX = 0;
        nGhostdY = 0;
        bMovement = false;
        nGhostdX = 0;
        nGhostdY = 0;
        ocCam = new OrthographicCamera();
        sprMap = new SprMap();
        sprHamster = new SprHamster(215, 369, 15, 15);
        arSprGhosts.add(new SprGhost(215, 232, 15, 15));
        arSprGhosts.add(new SprGhost(215, 231, 15, 15));
        arSprGhosts.add(new SprGhost(215, 230, 15, 15));
        arSprGhosts.add(new SprGhost(215, 229, 15, 15));
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
        for (SprGhost sp : arSprGhosts) {
            sp.move();
            sprMap.gHitWall(sp);
            sprMap.warpingEdge(sp);
        }
        sprHamster.move(nWantMove, sprMap);
        sprMap.hHitWall(sprHamster);
        sprMap.warpingEdge(sprHamster);
//        for (SprGhost sp : arSprGhosts) {
//            bIsHit = isHit(sprHamster, sp);
//            if (bIsHit) {
//                gamHamsters.updateState(2);
//            }
//        }
        batch.begin();
        sprHamster.draw(batch);
        for (SprGhost sp : arSprGhosts) {
            sp.draw(batch);
        }
        sprMap.draw(batch);
        for (int nI = 0; nI < sprMap.alSprPellets.size(); nI++) {
            if (isHit(sprHamster, sprMap.alSprPellets.get(nI))) {
                sprMap.alSprPellets.get(nI).isEaten = true;
            }
            if (sprMap.alSprPellets.get(nI).isEaten == true) {
                sprMap.alSprPellets.remove(nI);
                System.out.println(sprMap.alSprPellets.size());
            } else {
                sprMap.alSprPellets.get(nI).draw(batch);
                sprMap.alSprPellets.get(nI).isEaten = false;
            }
            if (sprMap.alSprPellets.isEmpty()) {
                gamHamsters.updateState(3);
            }

        }
//        for (int nI = 0; nI < sprMap.alSprMainWalls.size(); nI++) {
//            sprMap.alSprMainWalls.get(nI).draw(batch);
//        }
//        for (int nI = 0; nI < sprMap.alSprGhostHouse.size(); nI++) {
//            sprMap.alSprGhostHouse.get(nI).draw(batch);
//        }}
        batch.end();
    }

    public boolean isHit(Sprite spr1, Sprite spr2) {
        return spr1.getBoundingRectangle().overlaps(spr2.getBoundingRectangle());
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
            nWantMove = 1;
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            nWantMove = 2;
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            nWantMove = 3;
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            nWantMove = 4;
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
        if (button == Input.Buttons.LEFT) {
            nX1 = nScreenX;
            nY1 = nScreenY;
            System.out.println("nX: " + nX1 + " nY: " + nY1);
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
