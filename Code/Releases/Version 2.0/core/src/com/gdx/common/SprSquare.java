package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprSquare extends Sprite {
    int nX, nY, nW, nH;

    public SprSquare(int _nX, int _nY, int _nH, int _nW) {
        super(new Texture(Gdx.files.internal("martian.png")));
        setSize(_nW, _nH);
        setPosition(_nX, _nY);
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;
    }

    public void update(Sprite spr, int nDiffX, int nDiffY) {
        setPosition(spr.getX() + nDiffX, spr.getY() + nDiffY);
    }
}
