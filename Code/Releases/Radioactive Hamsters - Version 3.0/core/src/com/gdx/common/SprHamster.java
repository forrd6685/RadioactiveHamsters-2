package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprHamster extends Sprite {

    int nCurrentDir, nPos, nGlow, nGlowTime, nOrigX, nOrigY, nStatus;
    public Animation arNormalAni[], arGlowingAni[], arDarkAni[];
    Animation[][] aAllAnimations;
    public SprQuad HQuad;
    Texture txNormHam, txGlowHam, txDarkHam;
    SpriteSheetAnimator spriteSheetAnimator;
    boolean bRadioactive, bGlow;
    int[] DX = new int[]{0, 0, 2, 0, -2};
    int[] DY = new int[]{0, -2, 0, 2, 0};
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
        nPos = 0;
        nGlow = 0;
        nGlowTime = 0;
        nStatus = 0;
        bRadioactive = false;
        setPosition(nOrigX, nOrigY);
    }

    public void movement(int move, SprMap sprMap, int nFrame) {


        move(move, sprMap);
        sprMap.hHitWall(this);
        sprMap.warpingEdge(this);
        animation(nFrame);
    }

    public void statusUpdate(int _nStatus) {
        nStatus = _nStatus;
        if (nStatus == 2) {
            bGlow = true;
            nGlowTime = (int) (Math.random() * 40 + 10);
        } else if (nStatus == 1) {
            bRadioactive = true;
        }
    }

    public void animation(int nFrame) {
        if (bRadioactive) {
            if (bGlow) {
                if ((int) (Math.random() * 30 + 1) == 1) {
                    statusUpdate(2);
                }
            }
        }
        if (nGlow > nGlowTime) {
            nGlow = 0;
            bGlow = false;
            nStatus = 1;
        } else if (nGlow == nGlowTime) {
            //keeps him from glowing at the start
        } else {
            nGlow++;
        }
        if (nCurrentDir == 1) {
            nPos = 3;
        } else if (nCurrentDir == 2) {
            nPos = 2;
        } else if (nCurrentDir == 3) {
            nPos = 0;
        } else if (nCurrentDir == 4) {
            nPos = 1;
        }
        sprTemp = (Sprite) aAllAnimations[nStatus][nPos].getKeyFrame(nFrame, true);
        sprTemp.setPosition(getX(), getY());
        sprTemp.setSize(getWidth(), getHeight());
        sprTemp.setFlip(false, true);
    }

    public void tryMove(int nNewDir, SprMap map) {
        float fX = getX() + DX[nNewDir];
        float fY = getY() + DY[nNewDir];

        if (!map.bPlayCheckWall(this, fX, fY, true)) {
            nCurrentDir = nNewDir;
        }

    }

    public void move(int nNewDir, SprMap map) {
        HQuad = new SprQuad(getX(), getY());
        System.out.println("Hamster: " + HQuad.nQuad);
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