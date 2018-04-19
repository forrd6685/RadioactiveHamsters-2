package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SprMap extends Sprite {
    public ArrayList<SprMainWall> alSprMainWalls = new ArrayList<SprMainWall>();
    public ArrayList<SprGhostHouseWall> alSprGhostHouse = new ArrayList<SprGhostHouseWall>();
    public ArrayList<SprPellet> alSprPellets = new ArrayList<SprPellet>();
    public Texture PelletTexture = new Texture("dot.png");
    public Texture MainWallTexture = new Texture("testwall.jpg");
    public Texture GhostWallTexture = new Texture("testwall2.jpg");
    public int nScreenWidth = Gdx.graphics.getWidth();
    public int nScreenHeight = Gdx.graphics.getHeight();
    public int nWidth = nScreenWidth / 27;
    public int nHeight = nScreenHeight / 30;
    Scanner sIn;

    public SprMap() {
        super(new Texture(Gdx.files.internal("Maze.png")));
        setPosition(0, 0);
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setFlip(false, true);
    }
    public void mapMaker () throws FileNotFoundException {
//        0 is pellets, 1 is walls, 2 is ghost house, 3 is empty.
//        2D Array taken from: https://github.com/Code-Bullet/PacmanGame/blob/master/PacmanGame/PacmanGame.pde
        sIn = new Scanner(new FileReader("MapArray.txt"));
        int[][] nTileMap = new int[31][28];
        for (int nY = 0; nY < 31; nY++) {
            for (int nX = 0; nX < 28; nX++) {
                nTileMap[nY][nX] = sIn.nextInt();
                if (nTileMap[nY][nX] == 0) {
                    alSprPellets.add(new SprPellet(nX * nWidth + (nWidth+1 / 2)-10, nY * nHeight + (nHeight+1 / 2)-10, PelletTexture));
                } else if (nTileMap[nY][nX] == 1) {
                    alSprMainWalls.add(new SprMainWall(nX * nWidth, nY * nHeight, nWidth, nHeight, MainWallTexture));
                } else if (nTileMap[nY][nX] == 2) {
                    alSprGhostHouse.add(new SprGhostHouseWall(nX * nWidth, nY * nHeight, nWidth, nHeight, GhostWallTexture));
                }
            }
        }
    }
    public void gHitWall(SprGhost sprGhost) {
        //    Ghost
        for (int nI = 0; nI < alSprMainWalls.size(); nI++) {
            SprMainWall sprMainWall = alSprMainWalls.get(nI);
            if (sprGhost.getBoundingRectangle().overlaps(sprMainWall.getBoundingRectangle())) {
                sprGhost.OOB();
                sprGhost.pickNewDirection();
            }

        }
    }
    public void hHitWall(SprHamster sprHamster) {
        //    Hamster
        for (int nI = 0; nI < alSprMainWalls.size(); nI++) {
            SprMainWall sprMainWall = alSprMainWalls.get(nI);
            if (sprHamster.getBoundingRectangle().overlaps(sprMainWall.getBoundingRectangle())) {
                sprHamster.OOB();
            }
        }
        for (int nI = 0; nI < alSprGhostHouse.size(); nI++) {
            SprGhostHouseWall sprGhostWall = alSprGhostHouse.get(nI);
            if (sprHamster.getBoundingRectangle().overlaps(sprGhostWall.getBoundingRectangle())) {
                sprHamster.OOB();
            }
        }
    }
    public static void warpingEdge(Sprite spr1) {
        float fHalfWidth = spr1.getWidth() / 2, fX = spr1.getX(), fScreenWidth = Gdx.graphics.getWidth();
        if (fX + fHalfWidth < 0) {
            spr1.setX(fScreenWidth - fHalfWidth - 1);
        } else if (fX + fHalfWidth > fScreenWidth) {
            spr1.setX(0 - fHalfWidth + 1);
        }
    }
}

