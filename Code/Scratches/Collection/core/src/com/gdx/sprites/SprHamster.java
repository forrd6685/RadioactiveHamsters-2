package com.gdx.scratches;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

public class SprHamster extends Sprite {

    int nDx, nDy;

    public SprHamster(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("hamster.png")));
        setPosition(nX, nY);
        setSize(nW, nH);
    }

    public void Movement(Sprite spr1, int nNum1) {
        int nHamVorH;
        nHamVorH = nNum1 % 2;
        if (nHamVorH == 0) {
            nDx = SprGhost.horizontal(nNum1, nDx);
            nDy = 0;
        } else {
            nDy = SprGhost.vertical(nNum1, nDy);
            nDx = 0;
        }
        spr1.setX(spr1.getX() + nDx);
        spr1.setY(spr1.getY() + nDy);
    }
        public void OOB(Sprite spr1) {
        spr1.setX(spr1.getX() - nDx);
        spr1.setY(spr1.getY() - nDy);
    }
}