package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class SprMartian extends Sprite {

    int nX, nY;
    int nCurrentDir;
    boolean bFirst = true;
    boolean isOut = false;
    int[] DX = {2, 0, -2, 0};
    int[] DY = {0, -2, 0, 2};
    // 0 Right, 1 Down, 2 Left, 3 Up

    public SprMartian(int _nX, int _nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("martian.png")));
        setSize(nW, nH);
        setPosition(_nX, _nY);
         nCurrentDir = 3;
         nX = _nX;
         nY = _nY;
        setFlip(false, true);

    }

    public void pickNewDirection() {
        isOut = false;
        if (bFirst) {
            if (Math.random() < 0.5) {
                nCurrentDir = 2;
            } else {
                nCurrentDir = 0;
            }
            bFirst = false;
        }

        if (nCurrentDir == 1 || nCurrentDir == 3) {
            if (Math.random() < 0.5) {
                nCurrentDir = 2;
            } else {
                nCurrentDir = 0;
            }
        } else if (nCurrentDir == 0 || nCurrentDir == 2) {
            if (Math.random() < 0.5) {
                nCurrentDir = 1;
            } else {
                nCurrentDir = 3;
            }
        }
    }


    public void Movement() {
        if ((int) (Math.random() * 50 + 1) == 2) {
            nCurrentDir = randDir();
        }
        nX += (DX[nCurrentDir]);
        nY += (DY[nCurrentDir]);

        if (isOut) {
            //get inbound
            nX += (DX[nCurrentDir] * -2);
            nY += (DY[nCurrentDir] * -2);
            setX(nX);
            setY(nY);
           // pickNewDirection();
        } else {
            setX(nX);
            setY(nY);
        }
    }


    public int randDir() {
        int nDir = (int) (Math.random() * 3 + 1);
        return nDir;
    }
}



