package com.gdx.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

public class SprHamster extends Sprite {

    float fDx, fDy;
    public float fSpeed;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("hamster.png")));
        setPosition(nX, nY);
        setSize(nW, nH);
        fSpeed = 2;
    }

    public void Movement(int nHamDir) {
        if (nHamDir == 1) {
            fDy = fSpeed;
            fDx = 0;
        } else if (nHamDir == 2) {
            fDx = fSpeed;
            fDy = 0;
        } else if (nHamDir == 3) {
            fDy = -fSpeed;
            fDx = 0;
        } else if (nHamDir == 4) {
            fDx = -fSpeed;
            fDy = 0;
        } else {
            fDx = 0;
            fDy = 0;
        }

        setX(getX() + fDx);
        setY(getY() + fDy);
    }

    public void OOB() {
        setX(getX() - fDx);
        setY(getY() - fDy);
    }
}
