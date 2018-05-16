package com.gdx.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sun.org.apache.xpath.internal.SourceTree;

public class SprGhost extends Sprite {
    public boolean isOut = false;
    int nX, nY;
    int nCurrentDir = 0;
    int[] DX = {1, 0, -1, 0};
    int[] DY = {0, -1, 0, 1};
    // 0 Right, 1 Down, 2 Left, 3 Up

    public SprGhost(int _nX, int _nY, int _nW, int _nH) {
        super(new Texture(Gdx.files.internal("ghost.png")));
        nX = _nX;
        nY = _nY;
        setSize(_nW, _nH);
        setPosition(_nX, _nY);

    }

    public void pickNewDirection() {
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
       if ((int) (Math.random() * 50 + 1) == 2) {
            nCurrentDir = randDir();
        }
        nX += (DX[nCurrentDir]);
        nY += (DY[nCurrentDir]);

        if (isOut) {
            // get inbound
            nX += (DX[nCurrentDir] * -2);
            nY += (DY[nCurrentDir] * -2);
            setX(nX);
            setY(nY);
            pickNewDirection();
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
