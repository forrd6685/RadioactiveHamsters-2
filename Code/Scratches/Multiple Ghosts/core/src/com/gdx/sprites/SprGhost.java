package com.gdx.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprGhost extends Sprite {

    int nDx, nDy;
    int nCurrentDir = 1;
    public boolean isOut = false;

    public SprGhost(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("ghost.png")));
        setSize(nW, nH);
        setPosition(nX, nY);
    }

    public void Movement() {
        if ((int) (Math.random() * 50 + 1) == 2) {
            setDir(randDir());
        }
        nDx = 0;
        nDy = 0;
        if (nCurrentDir == 1) {
            nDy = 2;
        } else if (nCurrentDir == 2) {
            nDx = 2;
        } else if (nCurrentDir == 3) {
            nDy = -2;
        } else if (nCurrentDir == 4) {
            nDx = -2;
        }

        if (isOut) {
            // get inbound
            setX(getX() - nDx);
            setY(getY() - nDy);
            // pick a random direction
            setDir(randDir());
        } else {
            setX(getX() + nDx);
            setY(getY() + nDy);
        }
    }

    public int randDir() {
        int nNewDir = (int) (Math.random() * 4 + 1);
        while(nNewDir == nCurrentDir) {
            nNewDir = (int) (Math.random() * 4 + 1);
        }
        System.out.println(nNewDir);
        return nNewDir;
    }

    public void setDir(int nDirNew) {
        nCurrentDir = nDirNew;
    }
}
