package com.gdx.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprHamster extends Sprite {

    float fDx, fDy;
    int nPos, nGlow, nGlowTime;
    public boolean bGlow, bRadioactive;
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
        if(bRadioactive) {
            if (bGlow) {
                nGlowTime =  (int) (Math.random() * 40 + 10);
                sprTemp = (Sprite) arAnimation2[nPos].getKeyFrame(nFrame, true);
                if (nGlow >= nGlowTime) {
                    nGlow = 0;
                    bGlow = false;
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

        public void Movement ( int nHamDir){
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

        public void OOB () {
            setX(getX() - fDx);
            setY(getY() - fDy);
        }
    }
