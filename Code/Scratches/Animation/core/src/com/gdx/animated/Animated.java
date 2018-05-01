package com.gdx.animated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.sprites.SprMartian;

import java.util.ArrayList;

public class Animated extends Game {
    SpriteBatch batch;
    int nFrame, nPos;
    boolean bMovement, bOut;
    OrthographicCamera ocCam;
    SprMartian sprMartian;
    ArrayList<SprMartian> arSprMartians = new ArrayList<SprMartian>();

    @Override
    public void create() {
        ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocCam.update();
        batch = new SpriteBatch();
        nPos = 0;
//        sprMartian = new SprMartian(375, 300, 30, 30);
        arSprMartians.add(new SprMartian(100, 50, 15, 15));
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
        nFrame++;
        //nFrame needs to be n*k where k is the amount of frames per row.
        if (nFrame > 120) {
            nFrame = 0;
        }
        for (SprMartian sp : arSprMartians) {
            sp.move();
            sp.animation(nFrame);
            bOut = isOutOfBounds(sp);
            if(bOut) {
                sp.OOB();
            }
            batch.draw(sp.sprTemp, sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight());
        }
//        batch.begin();
//        trTemp = sprMartian.animation(nFrame);
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

