package com.gdx.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Map;

public class SprWall extends Sprite {
    public SprWall(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("testwall.png")));
        setPosition(nX, nY);
        setSize(nW, nH);
    }
}