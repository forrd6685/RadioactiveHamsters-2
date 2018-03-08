package com.gdx.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

public class SprWall extends Sprite {
    public SprWall(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("testwall.jpg")));
        setPosition(nX, nY);
        setSize(nW, nH);
    }
}