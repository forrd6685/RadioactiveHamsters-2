package com.gdx.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprHamster extends Sprite {

    public int nDx, nDy, nCurrentDir;
    public SprQuad Quad;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("hamster.png")));
        setPosition(nX, nY);
        setSize(nW, nH);

    }

    //Thanks Abdullah
    public void tryMove(int nNewDir) {
        if (nNewDir == 1) {
            nDy = 2;
            nDx = 0;
        } else if (nNewDir == 2) {
            nDx = 2;
            nDy = 0;
        } else if (nNewDir == 3) {
            nDy = -2;
            nDx = 0;
        } else if (nNewDir == 4) {
            nDx = -2;
            nDy = 0;
        }
        float fX = getX() + nDx;
        float fY = getY() + nDy;

    }

    public void move(int nNewDir) {
        Quad = new SprQuad(getX(), getY());

        if (nNewDir != nCurrentDir) {
            tryMove(nNewDir);
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

       System.out.println("Hamster: " + Quad.nQuad);
    }

    public void outOfBounds() {
        setX(getX() - nDx);
        setY(getY() - nDy);
    }
}