package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprHamster extends Sprite {

    int nDx, nDy, nCurrentDir, nPos, nGlow, nGlowTime;
    public boolean isGlowing, isRadioactive;
    public Animation arAnimation1[], arAnimation2[], arAnimation3[];
    Texture txSheet1, txSheet2, txSheet3;
    boolean bFlip;
    SpriteSheetAnimator spriteSheetAnimator;
    public Sprite sprTemp;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("hamster.png")));
        txSheet1 = new Texture("NormHamAni.png");
        txSheet2 = new Texture("LRadHamAni.png");
        txSheet3 = new Texture("DRadHamAni.png");
        spriteSheetAnimator = new SpriteSheetAnimator(txSheet1, 4, 3, 2);
        arAnimation1 = spriteSheetAnimator.animate();
        spriteSheetAnimator = new SpriteSheetAnimator(txSheet2, 4, 3, 2);
        arAnimation2 = spriteSheetAnimator.animate();
        spriteSheetAnimator = new SpriteSheetAnimator(txSheet3, 4, 3, 2);
        arAnimation3 = spriteSheetAnimator.animate();
        setPosition(nX, nY);
        setSize(nW, nH);
        setFlip(false, true);
    }

    public Sprite animation(int nFrame) {
        if(isRadioactive) {
            if (isGlowing) {
                nGlowTime =  (int) (Math.random() * 40 + 1);
                sprTemp = (Sprite) arAnimation2[nPos].getKeyFrame(nFrame, true);
                if (nGlow >= nGlowTime) {
                    nGlow = 0;
                    isGlowing = false;
                } else {
                    nGlow++;

                }
            } else {
                sprTemp = (Sprite) arAnimation3[nPos].getKeyFrame(nFrame, true);
            }
        } else {
            sprTemp = (Sprite) arAnimation1[nPos].getKeyFrame(nFrame, true);
        }
        sprTemp.setPosition(getX(), getY());
        sprTemp.setSize(getWidth(), getHeight());
        sprTemp.setFlip(false, true);
        if (bFlip) {
            sprTemp.setFlip(true, true);
        }
        return sprTemp;
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