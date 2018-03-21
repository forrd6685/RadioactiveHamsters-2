package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprMap extends Sprite {

    int nWidth, nHeight;

    public SprMap() {
        super(new Texture(Gdx.files.internal("Maze.png")));
        setPosition(0, 0);
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void Walls(SprWall sprWall[]) {
        sprWall[0] = new SprWall(0, 0, 12, 240);
        sprWall[1] = new SprWall(0, 270, 12, 210);
        sprWall[2] = new SprWall(0, 176, 126, 65);
        sprWall[3] = new SprWall(0, 270, 126, 65);
        sprWall[4] = new SprWall(Gdx.graphics.getWidth(), 0, -12, 240);
        sprWall[5] = new SprWall(Gdx.graphics.getWidth(), 270, -12, 210);
        sprWall[6] = new SprWall(0, 84, 58, 17);
        sprWall[7] = new SprWall(54, 129, 72, 19);
        sprWall[8] = new SprWall(100, 84, 25, 64);
        sprWall[9] = new SprWall(0, 0, 638, 12);
        sprWall[10] = new SprWall(54, 39, 205, 17);
        sprWall[11] = new SprWall(168, 40, 25, 62);
        sprWall[12] = new SprWall(54, 362, 72, 19);
        sprWall[13] = new SprWall(168, 175, 26, 66);
    }
}
