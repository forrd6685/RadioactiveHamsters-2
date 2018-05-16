package com.gdx.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class SprHamster extends Sprite {

    float fDx, fDy;
    int nPos, nGlow, nGlowTime, nStatus;
    public Animation arNormalAni[], arGlowingAni[], arDarkAni[];
    Texture txNormHam, txGlowHam, txDarkHam;
    boolean bFlip, bRadioactive;
    SpriteSheetAnimator spriteSheetAnimator;
    public Sprite sprTemp;
    Animation[][] alAnimations;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("hamster.png")));
        txNormHam = new Texture("NormHamAni.png");
        txGlowHam = new Texture("LRadHamAni.png");
        txDarkHam = new Texture("DRadHamAni.png");
        spriteSheetAnimator = new SpriteSheetAnimator(txNormHam, 4, 3, 2);
        arNormalAni = spriteSheetAnimator.animate();
        spriteSheetAnimator = new SpriteSheetAnimator(txGlowHam, 4, 3, 2);
        arGlowingAni = spriteSheetAnimator.animate();
        spriteSheetAnimator = new SpriteSheetAnimator(txDarkHam, 4, 3, 2);
        arDarkAni = spriteSheetAnimator.animate();
        alAnimations = new Animation[3][12];
        alAnimations[0] = arNormalAni;
        alAnimations[1] = arDarkAni;
        alAnimations[2] = arGlowingAni;
        setPosition(nX, nY);
        setSize(nW, nH);
        setFlip(false, true);
    }

    public void statusUpdate(int _nStatus) {
        nStatus = _nStatus;
        if (nStatus == 2) {
            bRadioactive = true;
            nGlowTime = (int) (Math.random() * 40 + 10);
        }
    }

    public Sprite animation(int nFrame) {
//        if (bRadioactive) {
//            if (bGlow) {
//                nGlowTime = (int) (Math.random() * 40 + 10);
//                sprTemp = (Sprite) arGlowingAni[nPos].getKeyFrame(nFrame, true);
//                if (nGlow >= nGlowTime) {
//                    nGlow = 0;
//                    bGlow = false;
//                } else {
//                    nGlow++;
//
//                }
//            } else {
//                sprTemp = (Sprite) arDarkAni[nPos].getKeyFrame(nFrame, true);
//
//            }
//        } else {
//            sprTemp = (Sprite) arNormalAni[nPos].getKeyFrame(nFrame, true);
//        }
        if(bRadioactive) {
            if (nGlow >= nGlowTime) {
                nGlow = 0;
                nStatus = 1;
            } else {
                nGlow++;
            }
        }
        sprTemp = (Sprite) alAnimations[nStatus][nPos].getKeyFrame(nFrame, true);
        sprTemp.setPosition(getX(), getY());
        sprTemp.setSize(getWidth(), getHeight());
        sprTemp.setFlip(false, true);
        if (bFlip) {
            sprTemp.setFlip(true, true);
        }
        return sprTemp;
    }

    public void Movement(int nHamDir) {
        if (nHamDir == 1) {
            fDy = -2;
            fDx = 0;
            nPos = 3;
        } else if (nHamDir == 2) {
            fDx = 2;
            fDy = 0;
            nPos = 2;
        } else if (nHamDir == 3) {
            fDy = 2;
            fDx = 0;
            nPos = 0;
        } else if (nHamDir == 4) {
            fDx = -2;
            fDy = 0;
            nPos = 1;
        } else {
            fDx = 0;
            fDy = 0;
        }
        setX(getX() + fDx);
        setY(getY() + fDy);
    }

    public void OOB() {
        setX(getX() - fDx);
        setY(getY() - fDy);
    }
}
