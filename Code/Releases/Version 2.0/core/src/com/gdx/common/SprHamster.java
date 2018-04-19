package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gdx.hamsters.GamHamsters;

public class SprHamster extends Sprite {

    int nDx, nDy, nWidth = GamHamsters.SCREENWIDTH / 27, nHeight = GamHamsters.SCREENHEIGHT / 30;
    public boolean isOut = false;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("hamster.png")));
        setPosition(nX, nY);
        setSize(nW, nH);
        setFlip(false, true);
    }

    public void Movement(int nHamDir) {
        if (nHamDir == 1) {
            nDy = -2;
            nDx = 0;
        } else if (nHamDir == 2) {
            nDx = 2;
            nDy = 0;
        } else if (nHamDir == 3) {
            nDy = 2;
            nDx = 0;
        } else if (nHamDir == 4) {
            nDx = -2;
            nDy = 0;
        } else {
            nDx = 0;
            nDy = 0;
        }
        setX(getX() + nDx);
        setY(getY() + nDy);
    }

    public void OOB() {
        setX(getX() - nDx);
        setY(getY() - nDy);
    }
}