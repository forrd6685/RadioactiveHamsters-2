package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SprMartian extends Sprite {

    int nX, nY, nW, nH;
    int nCurrentDir;
    boolean isInHouse = true;
    boolean isOut = false;
    int[] DX = {2, 0, -2, 0};
    int[] DY = {0, -2, 0, 2};
    public SprSquare squareL, squareR, squareT, squareB;
    // 0 Right, 1 Down, 2 Left, 3 Up

    public SprMartian(int _nX, int _nY, int _nW, int _nH) {
        super(new Texture(Gdx.files.internal("martian.png")));
        setSize(_nW, _nH);
        setPosition(_nX, _nY);
        nCurrentDir = 3;
        nX = _nX;
        nY = _nY;
        setFlip(false, true);


    }



    public void pickNewDirection() {

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
        if (isOut = true) {
            isOut = false;
        }
    }


    public void Movement() {



        if (isInHouse) {
            nCurrentDir = 1;
        }

        nX += (DX[nCurrentDir]);
        nY += (DY[nCurrentDir]);
        squareL = new SprSquare(nX - 50, nY, nW, nH);
        squareR = new SprSquare(nX + 50, nY, nW, nH);
        squareT = new SprSquare(nX, nY - 50, nW, nH);
        squareB = new SprSquare(nX, nY + 50, nW, nH);
        if (isOut) {
            nX += (DX[nCurrentDir] * -2);
            nY += (DY[nCurrentDir] * -2);
            setX(nX);
            setY(nY);
            pickNewDirection();

        }
        setX(nX);
        setY(nY);


    }

}



