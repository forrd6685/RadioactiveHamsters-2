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
import com.gdx.hamsters.Container;
import com.gdx.common.SprHamster;
import com.gdx.common.SprGhost;
import com.gdx.common.SprWall;
import com.gdx.common.SprMap;

public class Map extends Game implements Screen, InputProcessor {

    SpriteBatch batch;
    int nGhostX, nGhostY, nGhostDirOld, nGhostDirNew, nGhostdX, nGhostdY, nHamDir, nHamVorH, nHamdX, nHamdY, nRanGhostMove, nI, nWallX1, nWallY1, nWallX2, nWallY2;
    boolean bMovement, bGhostOOB, bHamsterOOB, bGhostRanMove, bHit;
    OrthographicCamera ocCam;
    SprGhost sprGhost;
    SprHamster sprHamster;
    SprMap sprMap;
    Container gamHamsters;
    SprWall sprWally[];
    int nX1, nY1, nX2, nY2, nWidth, nHeight;

    public Map(Container _gamhamsters) {
        batch = new SpriteBatch();
        sprGhost = new SprGhost(275, 200, 25, 25);
        sprHamster = new SprHamster(308, 196, 25, 25);
        sprMap = new SprMap();
        nGhostdX = 0;
        nGhostdY = 0;
        bMovement = false;
        bHit = false;
        ocCam = new OrthographicCamera();
        gamHamsters = _gamhamsters;
        sprWally = new SprWall[14];
    }

    @Override
    public void show() {
        ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.update();
        sprMap.Walls(sprWally);
        Gdx.input.setInputProcessor(this);
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
        if (bMovement = true) {
            sprHamster.Movement(nHamDir);
        }
        bGhostOOB = isOutOfBounds(sprGhost);
        if (bGhostOOB == true) {
            sprGhost.OOB(nGhostDirOld, nGhostDirNew);
            nGhostDirOld = nGhostDirNew;
            nGhostDirNew = sprGhost.GhostDirection(nGhostDirOld, nGhostDirNew);
            sprGhost.Movement(nGhostDirNew);
        }
        bHamsterOOB = isOutOfBounds(sprHamster);
        if (bHamsterOOB == true) {
            sprHamster.OOB();
        }
        for (nI = 0; nI < sprWally.length; nI++) {
            gHitWall(sprGhost, sprWally[nI]);
        }
        for (nI = 0; nI < sprWally.length; nI++) {
            bHit = hHitWall(sprHamster, sprWally[nI]);
            if (bHit == true) {
                System.out.println("Hit Wall: " + nI);
            }
        }
        batch.begin();
        sprGhost.draw(batch);
        sprHamster.draw(batch);
        sprMap.draw(batch);       
        for (nI = 0; nI < sprWally.length; nI++) {
            sprWally[nI].draw(batch);
        }
        batch.end();
    }

    public boolean hHitWall(Sprite sprHam, Sprite sprWall) {
        if (sprHam.getBoundingRectangle().overlaps(sprWall.getBoundingRectangle())) {
            sprHamster.OOB();
            return true;
        }
        return false;
    }

    public void gHitWall(Sprite sprG, Sprite sprWall) {
        if (sprG.getBoundingRectangle().overlaps(sprWall.getBoundingRectangle())) {
            bGhostOOB = true;
            if (bGhostOOB == true) {
                sprGhost.OOB(nGhostDirOld, nGhostDirNew);
                nGhostDirOld = nGhostDirNew;
                nGhostDirNew = sprGhost.GhostDirection(nGhostDirOld, nGhostDirNew);
                sprGhost.Movement(nGhostDirNew);
            };
        }
    }

    public static boolean isOutOfBounds(Sprite sprGhost) {
        if (0 < sprGhost.getX() && sprGhost.getX() + sprGhost.getWidth() < Gdx.graphics.getWidth() && 0 < sprGhost.getY() && sprGhost.getY() + sprGhost.getHeight() < Gdx.graphics.getHeight()) {
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
        if (button == Input.Buttons.LEFT) {
            nX1 = nScreenX;
            nY1 = Gdx.graphics.getHeight() - nScreenY;
        }
        return false;
    }

    @Override
    public boolean touchUp(int nScreenX, int nScreenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            int nXPos, nYPos;
            nX2 = nScreenX;
            nY2 = Gdx.graphics.getHeight() - nScreenY;
            nWidth = Math.abs(nX1 - nX2);
            nHeight = Math.abs(nY1 - nY2);
            if(nX1 < nX2) {
               nXPos = nX1; 
            } else {
               nXPos = nX2; 
            }
            if(nY1 < nY2) {
               nYPos = nY1; 
            } else {
               nYPos = nY2; 
            }
            System.out.println("sprWall[] = new SprWall("+nXPos+", "+nYPos+", "+nWidth+", "+nHeight+");");
        }
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
