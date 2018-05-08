package com.gdx.animated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.gdx.sprites.SprHamster;
import com.gdx.sprites.SprMartian;

import java.util.ArrayList;

public class Animated extends Game {
    SpriteBatch batch;
    int nMarFrame, nHamFrame, nPos, nHamDir;
    boolean bMovement, bOut, bHamsterOutOfBounds;
    OrthographicCamera ocCam;
    SprMartian sprMartian;
    SprHamster sprHamster;
    ArrayList<SprMartian> arSprMartians = new ArrayList<SprMartian>();

    @Override
    public void create() {
        ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.update();
        batch = new SpriteBatch();
        nPos = 0;
//        sprMartian = new SprMartian(375, 300, 30, 30);
        sprHamster = new SprHamster(100, 100, 30, 30);
        arSprMartians.add(new SprMartian(100, 50, 100, 100));
        arSprMartians.add(new SprMartian(100, 150, 15, 15));
        arSprMartians.add(new SprMartian(100, 250, 15, 15));
        arSprMartians.add(new SprMartian(100,350,15,15));
        bMovement = false;
//        ocCam = new OrthographicCamera();

    }

    public void render() {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setProjectionMatrix(ocCam.combined);
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
            sp.animation(nMarFrame);
            bOut = isOutOfBounds(sp);
            if(bOut) {
                sp.OOB();
            }
            batch.draw(sp.sprTemp, sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            nHamDir = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            nHamDir = 2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            nHamDir = 3;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            nHamDir = 4;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            sprHamster.bGlow = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            sprHamster.bRadioactive = true;
        }
        sprHamster.Movement(nHamDir);
        bHamsterOutOfBounds = isOutOfBounds(sprHamster);
        if (bHamsterOutOfBounds == true) {
            sprHamster.OOB();
        }
        sprHamster.animation(nHamFrame);
        sprHamster.sprTemp.draw(batch);
//        batch.begin();
//        trTemp = sprMartian.animation(nMarFrame);
//        batch.draw(trTemp, nX, 100);
        batch.end();
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
    public void dispose() {
        batch.dispose();
    }
}

