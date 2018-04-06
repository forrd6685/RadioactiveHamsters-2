package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gdx.common.SprPellet;

import java.util.ArrayList;

public class SprMap extends Sprite {
    ArrayList<SprWall> alSprWalls = new ArrayList<SprWall>();
    public ArrayList<SprPellet> alSprPellets = new ArrayList<SprPellet>();

    public SprMap() {
        super(new Texture(Gdx.files.internal("Maze.png")));
        setPosition(0, 0);
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void wallMaker() {
        // Left Side
        alSprWalls.add(new SprWall(0, 0, 12, 240)); // 0
        alSprWalls.add(new SprWall(0, 270, 12, 210)); // 1
        alSprWalls.add(new SprWall(0, 176, 126, 65)); // 2
        alSprWalls.add(new SprWall(0, 270, 126, 65)); // 3
        alSprWalls.add(new SprWall(168, 269, 27, 112)); // 4
        alSprWalls.add(new SprWall(168, 314, 95, 20)); // 5
        alSprWalls.add(new SprWall(0, 84, 58, 17)); // 6
        alSprWalls.add(new SprWall(54, 129, 72, 19)); // 7
        alSprWalls.add(new SprWall(100, 84, 25, 64)); // 8
        alSprWalls.add(new SprWall(0, 0, 638, 12)); // 9
        alSprWalls.add(new SprWall(54, 38, 209, 17)); // 10
        alSprWalls.add(new SprWall(168, 40, 28, 62)); // 11
        alSprWalls.add(new SprWall(54, 362, 72, 19)); // 12
        alSprWalls.add(new SprWall(168, 175, 27, 66)); // 13
        alSprWalls.add(new SprWall(54, 409, 72, 33)); // 14
        alSprWalls.add(new SprWall(168, 409, 95, 33)); // 15
        alSprWalls.add(new SprWall(168, 128, 95, 20)); // 16
        //Right Side (Flipped Left)
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth(), 0, -12, 240)); // 17
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth(), 270, -12, 210)); // 18
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth(), 176, -126, 65)); // 19
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth(), 270, -126, 65)); // 20
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 170, 269, -26, 113)); // 21
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 170, 314, -95, 20)); // 22
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth(), 84, -58, 17)); // 23
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 56, 129, -72, 19)); // 24
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 102, 84, -25, 64)); // 25
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth(), 0, -638, 12)); // 26
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 56, 38, -209, 17)); // 27
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 170, 40, -28, 62)); // 28
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 56, 362, -72, 19)); // 29
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 170, 175, -27, 66)); // 30
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 56, 409, -72, 33)); // 31
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 170, 409, -95, 33)); // 32
        alSprWalls.add(new SprWall(Gdx.graphics.getWidth() - 170, 128, -95, 20)); // 33
        //Middle Stuff
        alSprWalls.add(new SprWall(237, 361, 164, 20)); // 34
        alSprWalls.add(new SprWall(237, 83, 164, 18)); // 35
        alSprWalls.add(new SprWall(237, 176, 164, 20)); // 36
        alSprWalls.add(new SprWall(307, 316, 24, 64)); // 37
        alSprWalls.add(new SprWall(307, 130, 24, 64)); // 38
        alSprWalls.add(new SprWall(307, 38, 24, 64)); // 39
        alSprWalls.add(new SprWall(307, 409, 24, 71)); // 40
        alSprWalls.add(new SprWall(0, Gdx.graphics.getHeight(), 638, -12)); // 41
        alSprWalls.add(new SprWall(238, 224, 13, 63)); // 42
        alSprWalls.add(new SprWall(237, 278, 60, 8)); // 43
        alSprWalls.add(new SprWall(340, 278, 59, 9)); // 44
        alSprWalls.add(new SprWall(386, 222, 15, 64)); // 45
        alSprWalls.add(new SprWall(237, 222, 162, 14)); // 46
    }

    public void pelletPlacer() {
        alSprWalls.add(new SprWall(237, 221, 163, 65));
        alSprWalls.add(new SprWall(513, 242, 124, 31));
        alSprWalls.add(new SprWall(0, 241, 124, 35));
        alSprWalls.add(new SprWall(238, 99, 146, 29));
        int nY, nX;
        for (nY = 0; nY < Gdx.graphics.getHeight(); nY += 10) {
            for (nX = 0; nX < Gdx.graphics.getWidth(); nX += 10) {
                alSprPellets.add(new SprPellet(nX, nY));
                for (int nI = 0; nI < alSprWalls.size(); nI++) {
                    SprPellet sprPellet = alSprPellets.get(alSprPellets.size() - 1);
                    SprWall sprWall = alSprWalls.get(nI);
                    if (sprPellet.getBoundingRectangle().overlaps(sprWall.getBoundingRectangle())) {
                        alSprPellets.remove(alSprPellets.size() - 1);
                        break;
                    }
                }
            }
        }
        alSprWalls.remove(new SprWall(237, 221, 163, 65));
        alSprWalls.remove(new SprWall(513, 242, 124, 31));
        alSprWalls.remove(new SprWall(0, 241, 124, 35));
        alSprWalls.remove(new SprWall(238, 99, 146, 29));
    }
//    public void gHitWall(SprGhost sprGhost) {
//        for (nI = 0; nI < alSprWalls.size(); nI++) {
//            Sprite sprWall = alSprWalls.get(nI);
//            while (sprGhost.getBoundingRectangle().overlaps(sprWall.getBoundingRectangle())) {
//                sprGhost.OOB();
//                sprGhost.GhostDirection();
//                sprGhost.Movement();
//            }
//        }
//    }

//    public void hHitWall(SprHamster sprHamster) {
//        for (nI = 0; nI < alSprWalls.size(); nI++) {
//            Sprite sprWall = alSprWalls.get(nI);
//            if (sprHamster.getBoundingRectangle().overlaps(sprWall.getBoundingRectangle())) {
//                sprHamster.OOB();
//            }
//        }
//    }
}

