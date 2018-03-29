package com.gdx.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class SprWall extends Sprite {
    public SprWall(int nX, int nY, int nW, int nH) {
        super(new Texture(Gdx.files.internal("testwall.png")));
        setPosition(nX, nY);
        setSize(nW, nH);
    }
    public static ArrayList wallMaker(ArrayList<SprWall> Walls) {
        // Left Side
        Walls = new ArrayList<SprWall>();
        Walls.add(new SprWall(0, 0, 12, 240));
        Walls.add(new SprWall(0, 270, 12, 210));
        Walls.add(new SprWall(0, 176, 126, 65));
        Walls.add(new SprWall(0, 270, 126, 65));
        Walls.add(new SprWall(168, 269, 26, 112));
        Walls.add(new SprWall(168, 314, 95, 20));
        Walls.add(new SprWall(0, 84, 58, 17));
        Walls.add(new SprWall(54, 129, 72, 19));
        Walls.add(new SprWall(100, 84, 25, 64));
        Walls.add(new SprWall(0, 0, 638, 12));
        Walls.add(new SprWall(54, 38, 209, 17));
        Walls.add(new SprWall(168, 40, 28, 62));
        Walls.add(new SprWall(54, 362, 72, 19));
        Walls.add(new SprWall(168, 175, 27, 66));
        Walls.add(new SprWall(54, 409, 72, 33));
        Walls.add(new SprWall(168, 409, 95, 33));
        Walls.add(new SprWall(168, 128, 95, 20));
        //Right Side (Flipped Left)
        Walls.add(new SprWall(Gdx.graphics.getWidth(), 0, -12, 240));
        Walls.add(new SprWall(Gdx.graphics.getWidth(), 270, -12, 210));
        Walls.add(new SprWall(Gdx.graphics.getWidth(), 176, -126, 65));
        Walls.add(new SprWall(Gdx.graphics.getWidth(), 270, -126, 65));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 170, 269, -26, 113));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 170, 316, -95, 18));
        Walls.add(new SprWall(Gdx.graphics.getWidth(), 84, -58, 17));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 56, 129, -72, 19));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 102, 84, -25, 64));
        Walls.add(new SprWall(Gdx.graphics.getWidth(), 0, -638, 12));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 56, 38, -209, 17));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 170, 40, -28, 62));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 56, 362, -72, 19));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 170, 175, -27, 66));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 56, 409, -72, 33));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 170, 409, -95, 33));
        Walls.add(new SprWall(Gdx.graphics.getWidth() - 170, 128, -95, 20));
        //Middle Stuff
        Walls.add(new SprWall(237, 361, 164, 20));
        Walls.add(new SprWall(237, 83, 164, 18));
        Walls.add(new SprWall(237, 176, 164, 20));
        Walls.add(new SprWall(307, 317, 24, 64));
        Walls.add(new SprWall(307, 130, 24, 64));
        Walls.add(new SprWall(307, 38, 24, 64));
        Walls.add(new SprWall(307, 409, 24, 71));
        Walls.add(new SprWall(0, Gdx.graphics.getHeight(), 638, -12));
        Walls.add(new SprWall(238, 224, 13, 63));
        Walls.add(new SprWall(237, 278, 60, 8));
        Walls.add(new SprWall(340, 278, 59, 9));
        Walls.add(new SprWall(386, 222, 15, 64));
        Walls.add(new SprWall(237, 222, 162, 14));
//        if(Map.bMoveOut(true) {
//
//        }
        return Walls;
    }
}