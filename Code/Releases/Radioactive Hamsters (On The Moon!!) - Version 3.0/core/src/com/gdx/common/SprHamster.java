package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprHamster extends Sprite {

    int nDx, nDy, nCurrentDir, nPos, nGlow, nGlowTime, nOrigX, nOrigY, nStatus;
    public Animation arNormalAni[], arGlowingAni[], arDarkAni[];
    Animation[][] aAllAnimations;
    Texture txNormHam, txGlowHam, txDarkHam;
    SpriteSheetAnimator spriteSheetAnimator;
    public Sprite sprTemp;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("NormHamAni.png")));
        txNormHam = new Texture("NormHamAni.png");
        txGlowHam = new Texture("LRadHamAni.png");
        txDarkHam = new Texture("DRadHamAni.png");
        spriteSheetAnimator = new SpriteSheetAnimator(txNormHam, 4, 3, 2);
        arNormalAni = spriteSheetAnimator.animate();
        spriteSheetAnimator = new SpriteSheetAnimator(txGlowHam, 4, 3, 2);
        arGlowingAni = spriteSheetAnimator.animate();
        spriteSheetAnimator = new SpriteSheetAnimator(txDarkHam, 4, 3, 2);
        arDarkAni = spriteSheetAnimator.animate();
        aAllAnimations = new Animation[3][12];
        aAllAnimations[0] = arNormalAni;
        aAllAnimations[1] = arDarkAni;
        aAllAnimations[2] = arGlowingAni;
        setPosition(nX, nY);
        setSize(nW, nH);
        nOrigX = nX;
        nOrigY = nY;
        nGlowTime = nGlow;
        setFlip(false, true);
    }

    public void reset() {
        nCurrentDir = 0;
        nDx = 0;
        nDy = 0;
        nPos = 0;
        nGlow = 0;
        nGlowTime = 0;
        nStatus = 0;
        setPosition(nOrigX, nOrigY);
    }

    public void statusUpdate(int _nStatus) {
        nStatus = _nStatus;
        if (nStatus == 2) {
            nGlowTime = (int) (Math.random() * 30 + 10);
        }
    }

    public void animation(int nFrame) {
        if (nGlow > nGlowTime) {
            nGlow = 0;
            nStatus = 1;
        } else if (nGlow == nGlowTime) {
        } else {
            nGlow++;
        }
        sprTemp = (Sprite) aAllAnimations[nStatus][nPos].getKeyFrame(nFrame, true);
        sprTemp.setPosition(getX(), getY());
        sprTemp.setSize(getWidth(), getHeight());
        sprTemp.setFlip(false, true);
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
            nPos = 3;
        } else if (nCurrentDir == 2) {
            nDx = 2;
            nDy = 0;
            nPos = 2;
        } else if (nCurrentDir == 3) {
            nDy = 2;
            nDx = 0;
            nPos = 0;
        } else if (nCurrentDir == 4) {
            nDx = -2;
            nDy = 0;
            nPos = 1;
        }
        setX(getX() + nDx);
        setY(getY() + nDy);
    }

    public void outOfBounds() {
        setX(getX() - nDx);
        setY(getY() - nDy);
    }
}