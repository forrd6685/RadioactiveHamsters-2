package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class SprGhost extends Sprite {

    int nDx, nDy;
    int nCurrentDir;
    boolean bFirst = true;

    public SprGhost(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("ghost.png")));
        setSize(nW, nH);
        setPosition(nX, nY);
        nDx = 0;
        nDy = 0;
        nCurrentDir = -1;
        setFlip(false, true);
    }
    public void move() {
        if (nCurrentDir == -1) { // up
            nDx = 0;
            nDy = -2;
        } else if (nCurrentDir == 1) { // down
            nDx = 0;
            nDy = 2;
        } else if (nCurrentDir == 2) { // left
            nDx = 2;
            nDy = 0;
        } else if (nCurrentDir == -2) { // right
            nDx = -2;
            nDy = 0;
        }
        setX(getX() + nDx);
        setY(getY() + nDy);
    }
    public void OOB() {
        setX(getX() - nDx);
        setY(getY() - nDy);
    }
    public void pickNewDirection() {
        if (bFirst) {
            if (Math.random() < 0.5) {
                nCurrentDir = 2;
            } else {
                nCurrentDir = -2;
            }
            bFirst = false;
        } else {
//        int nNewDir = nCurrentDir;
//        while (nNewDir == Math.abs(nCurrentDir)) {
//            nNewDir = (int) (Math.random() * 4 + 1);
//        }
            if (Math.abs(nCurrentDir) == 1) {
                if (Math.random() < 0.5) {
                    nCurrentDir = 2;
                } else {
                    nCurrentDir = -2;
                }
            } else if (Math.abs(nCurrentDir) == 2) {
                if (Math.random() < 0.5) {
                    nCurrentDir = 1;
                } else {
                    nCurrentDir = -1;
                }
            }
        }
//        if (nCurrentDir == 1) System.out.println("up");
//        if (nCurrentDir == -1) System.out.println("down");
//        if (nCurrentDir == 2) System.out.println("left");
//        if (nCurrentDir == -2) System.out.println("right");
    }
}

