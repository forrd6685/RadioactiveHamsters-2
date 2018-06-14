package com.gdx.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprMartian extends Sprite {


    public int nX, nY;
    public int nCurrentDir;
    public boolean isOut = false;
    public SprQuad Quad;
    int[] DX = {1, 0, -1, 0, 0};
    int[] DY = {0, -1, 0, 1, 0};

    // 0 Right, 1 Down, 2 Left, 3 Up, 4 Stopped

    public SprMartian(int _nX, int _nY, int _nW, int _nH) {
        super(new Texture(Gdx.files.internal("martian.png")));
        setSize(_nW, _nH);
        setPosition(_nX, _nY);
        nCurrentDir = 0;
        nX = _nX;
        nY = _nY;
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

    public void WallMovement() {
        nCurrentDir = 2;
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


    public void Movement(SprHamster sprHamster) {
        Quad = new SprQuad(getX(), getY());
        if(Quad.nQuad != sprHamster.Quad.nQuad) {
           nCurrentDir = PathfindingX(sprHamster);
        } else if (Quad.nQuad == sprHamster.Quad.nQuad) {
                if((int) (Math.random() * 50 + 1) == 2) {
                  nCurrentDir = randDir();
              }
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

        System.out.println("Martian: " + Quad.nQuad);
    }

    public int randDir() {
        int nDir = (int) (Math.random() * 4);
        return nDir;
    }

    public int PathfindingX(SprHamster sprHamster) {
        if (getX() < sprHamster.getX()) {
                return 0;
        } else if (getX() > sprHamster.getX()) {
            return 2;
        } else if (getX() == sprHamster.getX()) {
           return PathfindingY(sprHamster);
        }
        return -1;
    }

    public int PathfindingY(SprHamster sprHamster) {
        if (getY() < sprHamster.getY()) {
            return 3;
        } else if (getY() > sprHamster.getY()) {
            return 1;
        } else if (getY() == sprHamster.getY()) {
            return 5;
        }
        return -1;
    }
}



