package com.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.common.*;
import com.gdx.hamsters.GamHamsters;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ScrPlay implements Screen, InputProcessor {
    SpriteBatch batch;
    int nGhostdX, nGhostdY, nWantMoveHam, nWantMoveMar, nMarFrame, nHamFrame, nY, nArrowMove;
    boolean bMovement, bIsHit, bExists;
    OrthographicCamera ocCam;
    ArrayList<SprMartian> arSprMartians = new ArrayList<SprMartian>();
    SprHamster sprHamster;
    GamHamsters gamHamsters;
    SprMap sprMap;
    SprNuke sprNuke;
    Texture tx;
    SprPlayableMartian sprPMart;
    Sprite sprBackground;

    public ScrPlay(GamHamsters _gamhamsters) {
        batch = new SpriteBatch();
        nGhostdX = 0;
        nGhostdY = 0;
        bMovement = false;
        nGhostdX = 0;
        nGhostdY = 0;
        sprMap = new SprMap();
        nY = 229;
        sprNuke = new SprNuke(212, 232, 20, 20);
        sprHamster = new SprHamster(215, 369, 15, 15);
        arSprMartians.add(new SprMartian(215, 232, 15, 15));
        arSprMartians.add(new SprMartian(215, 231, 15, 15));
        arSprMartians.add(new SprMartian(215, 230, 15, 15));
        ocCam = new OrthographicCamera();
        tx = new Texture("background1.jpg");
        sprBackground = new Sprite(tx);
        gamHamsters = _gamhamsters;
    }

    @Override
    public void show() {
        reset();
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
        frames();
        allMovement();
        hitTests();
        batch.begin();
        drawAll();
//        batch.draw(sprBackground, 0, 0);
//        sprHamster.sprTemp.draw(batch);
//        if (gamHamsters.bMultiplayer) {
//            sprPMart.animation(nMarFrame);
//            batch.draw(sprPMart.sprTemp, sprPMart.getX(), sprPMart.getY(), sprPMart.getWidth(), sprPMart.getHeight());
//        }
//        for (SprMartian sp : arSprMartians) {
//            batch.draw(sp.sprTemp, sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight());
//        }
//        if (sprNuke.bShow) {
//            sprNuke.draw(batch);
//        }
//        sprMap.draw(batch);
//        if (sprMap.alSprPellets.isEmpty()) {
//            sprHamster.statusUpdate(1);
//        }
//        for (SprPellet sp : sprMap.alSprPellets) {
//            sp.draw(batch);
//        }
////        for (int nI = 0; nI < sprMap.alSprMainWalls.size(); nI++) {
////            sprMap.alSprMainWalls.get(nI).draw(batch);
////        }
////        for (int nI = 0; nI < sprMap.alSprGhostHouse.size(); nI++) {
////            sprMap.alSprGhostHouse.get(nI).draw(batch);
////        }
        batch.end();
    }

    public boolean isHit(Sprite spr1, Sprite spr2) {
        return spr1.getBoundingRectangle().overlaps(spr2.getBoundingRectangle());
    }

    public void frames() {
        nMarFrame++;
        nHamFrame++;
        if (nMarFrame > 8) {
            nMarFrame = 0;
        }
        if (nHamFrame > 12) {
            nHamFrame = 0;
        }
    }

    public void allMovement() {
        for (SprMartian sp : arSprMartians) {
            sp.movement(sprMap, nMarFrame);
        }
        if (gamHamsters.bMultiplayer) {
            sprPMart.movement(nWantMoveMar, sprMap);
        }
        sprHamster.movement(nWantMoveHam, sprMap, nHamFrame);
    }

    public void hitTests() {
        for (SprMartian sp : arSprMartians) {
            bIsHit = isHit(sprHamster, sp);
            if (bIsHit) gamHamsters.updateState(2);
        }
        if (gamHamsters.bMultiplayer) {
            bIsHit = isHit(sprHamster, sprPMart);
            if (bIsHit) gamHamsters.updateState(2);
        }
        bIsHit = isHit(sprHamster, sprNuke);
        if (bIsHit) gamHamsters.updateState(3);
        for (SprPellet sp : sprMap.alSprPellets) sp.updateStatus(sprHamster);
        if (sprMap.alSprPellets.isEmpty()) {
            for (int nI = 0; nI < sprMap.alSprGhostHouse.size(); nI++) {
                sprMap.alSprGhostHouse.remove(nI);
            }
            sprNuke.bShow = true;
        }
    }

    public void drawAll(){
        batch.draw(sprBackground, 0, 0);
        sprHamster.sprTemp.draw(batch);
        if (gamHamsters.bMultiplayer) {
            sprPMart.animation(nMarFrame);
            batch.draw(sprPMart.sprTemp, sprPMart.getX(), sprPMart.getY(), sprPMart.getWidth(), sprPMart.getHeight());
        }
        for (SprMartian sp : arSprMartians) {
            batch.draw(sp.sprTemp, sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight());
        }
        if (sprNuke.bShow) {
            sprNuke.draw(batch);
        }
        sprMap.draw(batch);
        if (sprMap.alSprPellets.isEmpty()) {
            sprHamster.statusUpdate(1);
        }
        for (int nI=0; nI<sprMap.alSprPellets.size(); nI++) {
            SprPellet sp = sprMap.alSprPellets.get(nI);
            if (!sp.isEaten) sp.draw(batch);
            else sprMap.alSprPellets.remove(sp);
        }
//        for (int nI = 0; nI < sprMap.alSprMainWalls.size(); nI++) {
//            sprMap.alSprMainWalls.get(nI).draw(batch);
//        }
//        for (int nI = 0; nI < sprMap.alSprGhostHouse.size(); nI++) {
//            sprMap.alSprGhostHouse.get(nI).draw(batch);
//        }
    }

    public void reset() {
        if (arSprMartians.size() > 3) {
            arSprMartians.remove(arSprMartians.size() - 1);
        }
        if (gamHamsters.bMultiplayer) {
            sprPMart = new SprPlayableMartian(215, 229, 15, 15);
            sprPMart.reset();
        } else {
            arSprMartians.add(new SprMartian(215, 229, 15, 15));
        }
        for (SprMartian sp : arSprMartians) {
            sp.reset();
        }
        sprHamster.reset();
        nWantMoveHam = 0;
        sprNuke.bShow = false;
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
            nWantMoveHam = 1;
        } else if (keycode == Input.Keys.D) {
            nWantMoveHam = 2;
        } else if (keycode == Input.Keys.S) {
            nWantMoveHam = 3;
        } else if (keycode == Input.Keys.A) {
            nWantMoveHam = 4;
        }
        if (keycode == Input.Keys.UP) {
            nArrowMove = 1;
        } else if (keycode == Input.Keys.RIGHT) {
            nArrowMove = 2;
        } else if (keycode == Input.Keys.DOWN) {
            nArrowMove = 3;
        } else if (keycode == Input.Keys.LEFT) {
            nArrowMove = 4;
        } else {
            nArrowMove = 0;
        }
        if (nArrowMove != 0) {
            if (gamHamsters.bMultiplayer) {
                nWantMoveMar = nArrowMove;
            } else {
                nWantMoveHam = nArrowMove;
            }
        }
        if (keycode == Input.Keys.ALT_RIGHT) {
            for (int nI = 0; nI < sprMap.alSprPellets.size(); nI++) {
                sprMap.alSprPellets.get(nI).isEaten = true;
            }
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
//        if (button == Input.Buttons.LEFT) {
//            nX1 = nScreenX;
//            nY1 = nScreenY;
//            System.out.println("nX: " + nX1 + " nY: " + nY1);
//        }
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
