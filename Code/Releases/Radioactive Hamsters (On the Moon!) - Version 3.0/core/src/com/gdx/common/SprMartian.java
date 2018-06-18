package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;

public class SprMartian extends Sprite {

    int nDx, nDy, nCurrentDir, nPos, nOrigX, nOrigY, nOldMove;
    boolean bFirst = true;
    public Animation arAnimation[];
    Texture txSheet;
    boolean bFlip, bMove;
    SpriteSheetAnimator spriteSheetAnimator;
    public Sprite sprTemp;
    int[] DX = new int[]{0, 2, 0, -2};
    int[] DY = new int[]{-2, 0, 2, 0};
    int[][] nSquaresCheck = new int[][]{{0, 1, 3}, {1, 0, 2}, {2, 1, 3}, {3, 0, 2}};


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
        nCurrentDir = 0;
    }

    public void movement(SprMap sprMap, int nFrame, float fX, float fY) {
        move(sprMap, fX, fY);
        sprMap.gHitWall(this, bFirst);
        sprMap.warpingEdge(this);
        animation(nFrame);
    }

    public void animation(int nFrame) {
        if (nCurrentDir == 0) {
            nPos = 2;
            bFlip = false;
        } else if (nCurrentDir == 1) {
            nPos = 1;
            bFlip = false;
        } else if (nCurrentDir == 2) {
            nPos = 0;
            bFlip = false;
        } else if (nCurrentDir == 3) {
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

    public void reset() {
        setPosition(nOrigX, nOrigY);
        nCurrentDir = 0;
        bFirst = true;
    }

    public void pickNewDirection() {
        if (bFirst) {
            if (Math.random() < 0.5) {
                nCurrentDir = 1;
            } else {
                nCurrentDir = 3;
            }
            bFirst = false;
        } else {
            nOldMove = nCurrentDir;
            while(nOldMove % 2 == nCurrentDir % 2) {
                nCurrentDir = (int) (Math.random() * 4.0);
            }
        }

    }

    public boolean tryMove(int nNewDir, SprMap map) {
        float fX = getX() + DX[nNewDir];
        float fY = getY() + DY[nNewDir];
        if (!bFirst && !map.bPlayCheckWall(this, fX, fY, true) && (int) (Math.random() * 3.0) == 0) {
            nCurrentDir = nNewDir;
            return true;
        }
        return false;
    }

    public void move(SprMap sprMap, float fX, float fY) {
//        if (getX() <= fX + 100.0F && getX() + getWidth() > fX - 100.0F) {
//            if (getY() <= fY + 100.0F && getY() + getHeight() > fY - 100.0F) {
//                proximityCheck(fX, fY);
//            }
        for (int nI = 0; nI < 3; ++nI) {
            bMove = tryMove(nSquaresCheck[nCurrentDir][nI], sprMap);
            if (bMove) {
                break;
            }
        }
        setX(getX() + DX[nCurrentDir]);
        setY(getY() + DY[nCurrentDir]);
    }

    public void proximityCheck(float fX, float fY) {
        if (Math.random() < 0.5D) {
            if (getX() > fX) {
                nCurrentDir = 3;
            } else if (getX() < fX) {
                nCurrentDir = 1;
            }
        } else if (getY() > fY) {
            nCurrentDir = 0;
        } else if (getY() < fY) {
            nCurrentDir = 2;
        }

    }

    public void outOfBounds() {
        setX(getX() - DX[nCurrentDir]);
        setY(getY() - DY[nCurrentDir]);
        pickNewDirection();
    }
}