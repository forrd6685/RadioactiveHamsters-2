package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SprGhostHouseWall extends Sprite {
    public SprGhostHouseWall(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("testwall2.jpg")));
        setPosition(nX, nY);
        setSize(nW, nH);
        setFlip(false, true);
    }
}