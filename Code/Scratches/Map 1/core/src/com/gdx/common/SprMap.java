package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class SprMap extends Sprite {

    public SprMap() {
        super(new Texture(Gdx.files.internal("Maze.png")));
        setPosition(0, 0);
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
