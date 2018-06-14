package com.gdx.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SprWall extends Sprite {
    public SprWall(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("wall.jpeg")));
        setPosition(nX, nY);
        setSize(nW, nH);
        setFlip(false, true);
    }
}