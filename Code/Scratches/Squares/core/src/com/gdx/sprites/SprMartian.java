package com.gdx.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SprMartian extends Sprite {

    public int nX, nY, nW, nH;
    public int nCurrentDir;
    public SprSquare squareL, squareR;
    public boolean isOut = false;
    int[] DX = {1, 0, -1, 0};
    int[] DY = {0, -1, 0, 1};

    // 0 Right, 1 Down, 2 Left, 3 Up

    public SprMartian(int _nX, int _nY, int _nW, int _nH) {
        super(new Texture(Gdx.files.internal("martian.png")));
        setSize(_nW, _nH);
        setPosition(_nX, _nY);
        nCurrentDir = 3;
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;
        setFlip(false, false);
        squareL = new SprSquare(nX - 25, nY, nH, nW);
        squareR = new SprSquare(nX + 25, nY, nH, nW);
    }

    public void Bounce() {
        if (nCurrentDir == 0) {
            nCurrentDir = 2;
        } else if (nCurrentDir == 1) {
            nCurrentDir = 3;
        } else if (nCurrentDir == 2) {
            nCurrentDir = 0;
        } else if (nCurrentDir == 3) {
            nCurrentDir = 1;
        }
    }

    public void Movement() {


        nX += (DX[nCurrentDir]);
        nY += (DY[nCurrentDir]);

        squareL.update((int) getX() - 25, (int) getY());
        squareR.update((int) getX() + 25, (int) getY());

        if (isOut) {
            nX += (DX[nCurrentDir] * -2);
            nY += (DY[nCurrentDir] * -2);
            setX(nX);
            setY(nY);
            Bounce();


        }
        setX(nX);
        setY(nY);


    }



}



