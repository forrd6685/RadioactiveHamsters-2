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
    int[] DX = new int[]{0, 0, 2, 0, -2};
    int[] DY = new int[]{0, -2, 0, 2, 0};

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

    public void movement(int move, SprMap sprMap){
        move(move, sprMap);
        sprMap.pHitWall(this);
        sprMap.warpingEdge(this);
    }
    public void reset() {
        nCurrentDir = 0;
        nPos = 2;
        setPosition(nOrigX, nOrigY);
    }

    public void animation(int nFrame) {
        if (nCurrentDir == 1) {
            nPos = 2;
            bFlip = false;
        } else if (nCurrentDir == 2) {
            nPos = 1;
            bFlip = false;
        } else if (nCurrentDir == 3) {
            nPos = 0;
            bFlip = false;
        } else if (nCurrentDir == 4) {
            nPos = 1;
            bFlip = true;
        }
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
        if (!map.bPlayCheckWall(this, fX, fY, false)) {
            nCurrentDir = nNewDir;
        }
    }

    public void move(int nNewDir, SprMap map) {
        if (nNewDir != nCurrentDir) {
            tryMove(nNewDir, map);
        }
        setX(getX() + (float)DX[nCurrentDir]);
        setY(getY() + (float)DY[nCurrentDir]);
    }

    public void outOfBounds() {
        setX(getX() - (float)DX[nCurrentDir]);
        setY(getY() - (float)DY[nCurrentDir]);
    }
}