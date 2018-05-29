package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprNuke extends Sprite {
    public boolean bShow = false;
    public SprNuke(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("nuke.png")));
        setPosition(nX, nY);
        setSize(nW, nH);
        setFlip(false, true);
    }
}