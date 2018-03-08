package com.gdx.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

public class SprHamster extends Sprite {

    int nDx, nDy;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("hamster.png")));
        setPosition(nX, nY);
        setSize(nW, nH);
    }

    public void Movement(int nHamDir) {
        if (nHamDir == 1) {
            nDy = 2;
            nDx = 0;
        } else if (nHamDir == 2) {
            nDx = 2;
            nDy = 0;
        } else if (nHamDir == 3) {
            nDy = -2;
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
//        setX(50 - nDx); //Looking to try and help with teleporting the character across the screen
//        setY(getY()- nDy); //y doesn't need to change, does cause general problems with hitting other walls
        setX(getX() - nDx);
        setY(getY() - nDy);
    }
}