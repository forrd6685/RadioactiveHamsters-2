package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;

public class SprMartian extends Sprite {

    int nDx, nDy, nCurrentDir, nPos, nOrigX, nOrigY;
    boolean bFirst = true;
    public Animation arAnimation[];
    Texture txSheet;
    boolean bFlip;
    SpriteSheetAnimator spriteSheetAnimator;
    public Sprite sprTemp;


    public SprMartian(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("MartianAni.png")));
        txSheet = new Texture("MartianAni.png");
        spriteSheetAnimator = new SpriteSheetAnimator(txSheet, 3, 8, 1);
        arAnimation = spriteSheetAnimator.animate();
        setSize(nW, nH);
        setPosition(nX, nY);
        nOrigX = nX;
        nOrigY = nY;
        nDx = 0;
        nDy = 0;
        nCurrentDir = -1;
    }

    public void animation(int nFrame) {
        sprTemp = (Sprite) arAnimation[nPos].getKeyFrame(nFrame, true);
//        sprTemp = new Sprite(sprTemp);
        sprTemp.setFlip(false, true);
        if (bFlip) {
            sprTemp.setFlip(true, true);
        }
    }

    public void reset() {
        setPosition(nOrigX, nOrigY);
        nCurrentDir = -1;
        bFirst = false;
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

    public void move() {
        if (nCurrentDir == -1) { // up
            nDx = 0;
            nDy = -2;
            nPos = 2;
            bFlip = false;
        } else if (nCurrentDir == 1) { // down
            nDx = 0;
            nDy = 2;
            nPos = 0;
            bFlip = false;
        } else if (nCurrentDir == 2) { // right
            nDx = 2;
            nDy = 0;
            nPos = 1;
            bFlip = false;
        } else if (nCurrentDir == -2) { // left
            nDx = -2;
            nDy = 0;
            nPos = 1;
            bFlip = true;
        }
        setX(getX() + nDx);
        setY(getY() + nDy);
    }

    public void outOfBounds() {
        setX(getX() - nDx);
        setY(getY() - nDy);
        pickNewDirection();
    }
}