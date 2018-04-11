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
import com.gdx.common.SprMap;

import java.io.FileNotFoundException;


public class scrMap extends Game implements Screen, InputProcessor {
//    int nX1, nY1, nX2, nY2, nWidth, nHeight;
    SpriteBatch batch;
    OrthographicCamera ocCam;
    SprMap sprMap;
    GamContainer gamHamsters;

    public scrMap(GamContainer _gamhamsters) {
        batch = new SpriteBatch();
        sprMap = new SprMap();
        ocCam = new OrthographicCamera();
        gamHamsters = _gamhamsters;
    }

    @Override
    public void show() {
        ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.update();
        Gdx.input.setInputProcessor(this);
        try {
            sprMap.mapMaker();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setProjectionMatrix(ocCam.combined);
        sprMap.draw(batch);
//        for (int nI = 0; nI < sprMap.alSprMainWalls.size(); nI++){
//            sprMap.alSprMainWalls.get(nI).draw(batch);
//        }
        for (int nI = 0; nI < sprMap.alSprPellets.size(); nI++){
            sprMap.alSprPellets.get(nI).draw(batch);
        }
//        for (int nI = 0; nI < sprMap.alSprGhostHouse.size(); nI++){
//            sprMap.alSprGhostHouse.get(nI).draw(batch);
//        }
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
//            nY1 = Gdx.graphics.getHeight() - nScreenY;
//        }
//        System.out.println("nX: " + nX1 + " nY: " + nY1);
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
//            System.out.println("alSprWalls.add(new SprMainWall(" + nXPos + ", " + nYPos + ", " + nWidth + ", " + nHeight + "));");
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
