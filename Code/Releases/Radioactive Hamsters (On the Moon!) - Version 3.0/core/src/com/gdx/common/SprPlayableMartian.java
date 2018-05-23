package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprPlayableMartian extends Sprite {
    int nDx, nDy, nCurrentDir, nPos, nOrigX, nOrigY;
    boolean bFlip;
    public Animation arAnimation[];
    Texture txSheet;
    SpriteSheetAnimator spriteSheetAnimator;
    public Sprite sprTemp;

    public SprPlayableMartian(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("RedMartianAni.png")));
        txSheet = new Texture("RedMartianAni.png");
        spriteSheetAnimator = new SpriteSheetAnimator(txSheet, 3, 8, 1);
        arAnimation = spriteSheetAnimator.animate();
        setSize(nW, nH);
        setPosition(nX, nY);
        nOrigX = nX;
        nOrigY = nY;
    }

    public void reset() {
        nCurrentDir = -1;
        nDx = 0;
        nDy = 0;
        nPos = 2;
        setPosition(nOrigX, nOrigY);
    }

    public void animation(int nFrame) {
        sprTemp = (Sprite) arAnimation[nPos].getKeyFrame(nFrame, true);
//        sprTemp = new Sprite(sprTemp);
        sprTemp.setFlip(false, true);
        if (bFlip) {
            sprTemp.setFlip(true, true);
        }
    }

    public void tryMove(int nNewDir, SprMap map) {
        //Thanks Abdullah
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
        if (!map.bCheckHitWall(this, fX, fY, false)) {
            nCurrentDir = nNewDir;
        }
    }

    public void move(int nNewDir, SprMap map) {
        if (nNewDir != nCurrentDir) {
            tryMove(nNewDir, map);
        }
        bFlip = false;
        if (nCurrentDir == 1) { // up
            nDy = -2;
            nDx = 0;
            nPos = 2;
        } else if (nCurrentDir == 2) { // right
            nDx = 2;
            nDy = 0;
            nPos = 1;
        } else if (nCurrentDir == 3) { // down
            nDy = 2;
            nDx = 0;
            nPos = 0;
        } else if (nCurrentDir == 4) { // left
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
    }
}
