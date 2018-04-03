package com.gdx.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Screen;
import com.gdx.hamsters.GamContainer;
import com.gdx.common.SprHamster;
import com.gdx.common.SprGhost;
import com.gdx.common.SprMap;


public class scrMap extends Game implements Screen, InputProcessor {
    SpriteBatch batch;                                          // Wall Making Variables
    int nHamDir, nRanGhostMove, nFirstMove; //nX1, nY1, nX2, nY2, nWidth, nHeight;
    boolean bMovement, bGhostOOB, bHamsterOOB, bHit, bGhostHitWall;
    public static boolean bMoveOut = false;
    OrthographicCamera ocCam;
    SprGhost sprGhost;
    SprHamster sprHamster;
    SprMap sprMap;
    GamContainer gamHamsters;

    public scrMap(GamContainer _gamhamsters) {
        batch = new SpriteBatch();
        sprGhost = new SprGhost(306, 253, 25, 25);
        sprHamster = new SprHamster(308, 196, 25, 25);
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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nRanGhostMove = (int) (Math.random() * 50 + 1);
        if (bMoveOut = false) {
            if (nRanGhostMove == 1) {
                sprGhost.GhostDirection();
            }
        }
        sprGhost.Movement();
        sprHamster.Movement(nHamDir);
        bHamsterOOB = isOutOfBounds(sprHamster);
        if (bHamsterOOB == true) {
            sprHamster.OOB();
        }
        sprMap.gHitWall(sprGhost);
        while (bGhostOOB == true) {
            sprGhost.OOB();
            sprGhost.GhostDirection();
            sprGhost.Movement();
            bGhostOOB = isOutOfBounds(sprGhost);
        }
        sprMap.hHitWall(sprHamster);
        batch.begin();
        sprGhost.draw(batch);
        sprHamster.draw(batch);
        sprMap.draw(batch);
        batch.end();
    }
//    public void hHitWall(Sprite sprHam, Sprite sprWall) {
//        if (sprHam.getBoundingRectangle().overlaps(sprWall.getBoundingRectangle())) {
//            sprHamster.OOB();
//        }
//    }

//    public void gHitWall(Sprite sprG, Sprite sprWall) {
//        //Using while stopped it glitching into a wall
//        while (sprG.getBoundingRectangle().overlaps(sprWall.getBoundingRectangle())) {
//            sprGhost.OOB();
//            nGhostDirOld = nGhostDirNew;
//            nGhostDirNew = sprGhost.GhostDirection(nGhostDirOld, nGhostDirNew);
//            sprGhost.Movement(nGhostDirNew);
//        }
//    }

    public static boolean isOutOfBounds(Sprite sprGhost) {
        float fGhostWidth = sprGhost.getWidth(), fGhostHeight = sprGhost.getHeight(), fX = sprGhost.getX();
        float fY = sprGhost.getY(), fScrWidth = Gdx.graphics.getWidth(), fScrHeight = Gdx.graphics.getHeight();

        if (0 < fX && fX + fGhostWidth < fScrWidth && 0 < fY && fY + fGhostHeight < fScrHeight) {
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
    public boolean touchDown(int nScreenX, int nScreenY, int pointer, int button) {
//        if (button == Input.Buttons.LEFT) {
//            nX1 = nScreenX;
//            nY1 = Gdx.graphics.getHeight() - nScreenY;
//        }
        return false;
    }

    @Override
    public boolean touchUp(int nScreenX, int nScreenY, int pointer, int button) {
//        if (button == Input.Buttons.LEFT) {
//            int nXPos, nYPos;
//            nX2 = nScreenX;
//            nY2 = Gdx.graphics.getHeight() - nScreenY;
//            nWidth = Math.abs(nX1 - nX2);
//            nHeight = Math.abs(nY1 - nY2);
//            if (nX1 < nX2) {
//                nXPos = nX1;
//            } else {
//                nXPos = nX2;
//            }
//            if (nY1 < nY2) {
//                nYPos = nY1;
//            } else {
//                nYPos = nY2;
//            }
//            System.out.println("Walls.add(new SprWall(" + nXPos + ", " + nYPos + ", " + nWidth + ", " + nHeight + "));");
//        }
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

    @Override
    public void create() {
    }
}
