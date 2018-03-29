package com.gdx.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class SprGhost extends Sprite {

    int nDx, nDy, nDirNew = 1, nDirOld;

    public SprGhost(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("ghost.png")));
        setSize(nW, nH);
        setPosition(nX, nY);
//        setFlip(true, true);
    }

    public void Movement() {
        nDx = 0;
        nDy = 0;
        if (nDirNew == 1) {
            nDy = 2;
        } else if (nDirNew == 2) {
            nDx = 2;
        } else if (nDirNew == 3) {
            nDy = -2;
        } else if (nDirNew == 4) {
            nDx = -2;
        }
        setX(getX() + nDx);
        setY(getY() + nDy);
    }

    public void OOB() {
//        bMoveOut = true;
        setX(getX() - nDx);
        setY(getY() - nDy);
    }

    public int GhostDirection() {
        nDirOld = nDirNew;
        while (nDirNew == nDirOld) {
            nDirNew = (int) (MathUtils.random() * 4 + 1);
        }
        return nDirNew;

    }
}
