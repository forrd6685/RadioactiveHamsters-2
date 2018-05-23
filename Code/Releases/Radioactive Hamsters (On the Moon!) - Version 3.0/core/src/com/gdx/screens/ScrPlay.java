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
import com.gdx.common.SprMartian;
import com.gdx.common.SprHamster;
import com.gdx.common.SprMap;
import com.gdx.common.SprNuke;
import com.gdx.hamsters.GamHamsters;
import com.gdx.common.SprPlayableMartian;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ScrPlay implements Screen, InputProcessor {
    SpriteBatch batch;
    int nGhostdX, nGhostdY, nWantMoveHam, nWantMoveMar, nMarFrame, nHamFrame, nY;
    boolean bMovement, bIsHit, bRadioactive;
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
        for (SprMartian sp : arSprMartians) {
            sp.reset();
        }
        bRadioactive = false;
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
        if(bRadioactive) {
            if ((int) (Math.random() * 30 + 1) == 1) {
                sprHamster.statusUpdate(2);
            }
        }
        nMarFrame++;
        nHamFrame++;
        if (nMarFrame > 8) {
            nMarFrame = 0;
        }
        if (nHamFrame > 12) {
            nHamFrame = 0;
        }
        for (SprMartian sp : arSprMartians) {
            sp.move();
            sprMap.gHitWall(sp);
            sprMap.warpingEdge(sp);
        }
        if (gamHamsters.bMultiplayer) {
            sprPMart.move(nWantMoveMar, sprMap);
            sprMap.pHitWall(sprPMart);
            sprMap.warpingEdge(sprPMart);
        }
        sprHamster.move(nWantMoveHam, sprMap);
        sprMap.hHitWall(sprHamster);
        sprMap.warpingEdge(sprHamster);
        for (SprMartian sp : arSprMartians) {
            bIsHit = isHit(sprHamster, sp);
            if (bIsHit) {
                gamHamsters.updateState(2);
            }
        }
        if (gamHamsters.bMultiplayer) {
            bIsHit = isHit(sprHamster, sprPMart);
            if (bIsHit) {
                gamHamsters.updateState(2);
            }
        }
        bIsHit = isHit(sprHamster, sprNuke);
        if (bIsHit) {
            gamHamsters.updateState(3);
        }
        batch.begin();
        batch.draw(sprBackground, 0, 0);
        sprHamster.animation(nHamFrame);
        sprHamster.sprTemp.draw(batch);
        if (gamHamsters.bMultiplayer) {
            sprPMart.animation(nMarFrame);
            batch.draw(sprPMart.sprTemp, sprPMart.getX(), sprPMart.getY(), sprPMart.getWidth(), sprPMart.getHeight());
        }
        for (SprMartian sp : arSprMartians) {
            sp.animation(nMarFrame);
            batch.draw(sp.sprTemp, sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight());
        }
        if (sprNuke.bShow) {
            sprNuke.draw(batch);
        }
        if (sprMap.alSprPellets.isEmpty()) {
            for (int nI = 0; nI < sprMap.alSprGhostHouse.size(); nI++) {
                sprMap.alSprGhostHouse.remove(nI);
                bRadioactive = true;
            }
            sprNuke.bShow = true;
        }

        sprMap.draw(batch);
        for (int nI = 0; nI < sprMap.alSprPellets.size(); nI++) {
            if (isHit(sprHamster, sprMap.alSprPellets.get(nI))) {
                sprMap.alSprPellets.get(nI).isEaten = true;
            }
            if (sprMap.alSprPellets.get(nI).isEaten == true) {
                sprMap.alSprPellets.remove(nI);
            } else {
                sprMap.alSprPellets.get(nI).draw(batch);
                sprMap.alSprPellets.get(nI).isEaten = false;
            }
            if (sprMap.alSprPellets.isEmpty()) {
                sprHamster.statusUpdate(1);

            }
        }
//        for (int nI = 0; nI < sprMap.alSprMainWalls.size(); nI++) {
//            sprMap.alSprMainWalls.get(nI).draw(batch);
//        }
//        for (int nI = 0; nI < sprMap.alSprGhostHouse.size(); nI++) {
//            sprMap.alSprGhostHouse.get(nI).draw(batch);
//        }
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
        if (keycode == Input.Keys.W) {
            nWantMoveHam = 1;
        } else if (keycode == Input.Keys.D) {
            nWantMoveHam = 2;
        } else if (keycode == Input.Keys.S) {
            nWantMoveHam = 3;
        } else if (keycode == Input.Keys.A) {
            nWantMoveHam = 4;
        }

        if (gamHamsters.bMultiplayer) {
            if (keycode == Input.Keys.UP) {
                nWantMoveMar = 1;
            } else if (keycode == Input.Keys.RIGHT) {
                nWantMoveMar = 2;
            } else if (keycode == Input.Keys.DOWN) {
                nWantMoveMar = 3;
            } else if (keycode == Input.Keys.LEFT) {
                nWantMoveMar = 4;
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
