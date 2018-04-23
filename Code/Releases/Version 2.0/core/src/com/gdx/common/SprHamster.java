package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprHamster extends Sprite {

    int nDx, nDy, nCurrentDir;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("hamster.png")));
        setPosition(nX, nY);
        setSize(nW, nH);
        setFlip(false, true);
    }

    //Thanks Abdullah
    public void tryMove(int nNewDir, SprMap map) {
        if (nNewDir == 1) {
            nDy = -2;
            nDx = 0;
        } else if (nNewDir == 2) {
            nDx = 2;
            nDy = 0;
        } else if (nNewDir == 3) {
            nDy = 2;
            nDx = 0;
        } else if (nNewDir == 4) {
            nDx = -2;
            nDy = 0;
        }
        float fX = getX() + nDx;
        float fY = getY() + nDy;
        if (!map.hHitWall(this, fX, fY)) {
            nCurrentDir = nNewDir;
        }
    }

    public void move(int nNewDir, SprMap map) {

        if (nNewDir != nCurrentDir) {
            tryMove(nNewDir, map);
        }

        if (nCurrentDir == 1) {
            nDy = -2;
            nDx = 0;
        } else if (nCurrentDir == 2) {
            nDx = 2;
            nDy = 0;
        } else if (nCurrentDir == 3) {
            nDy = 2;
            nDx = 0;
        } else if (nCurrentDir == 4) {
            nDx = -2;
            nDy = 0;
        }
        setX(getX() + nDx);
        setY(getY() + nDy);
    }

    public void outOfBounds() {
        setX(getX() - nDx);
        setY(getY() - nDy);
    }
}