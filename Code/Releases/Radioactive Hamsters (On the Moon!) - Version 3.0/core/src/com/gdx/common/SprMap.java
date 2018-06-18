package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SprMap extends Sprite {
    public ArrayList<SprMainWall> alSprMainWalls = new ArrayList<SprMainWall>();
    public ArrayList<SprGhostHouseWall> alSprGhostHouse = new ArrayList<SprGhostHouseWall>();
    public ArrayList<SprPellet> alSprPellets = new ArrayList<SprPellet>();
    public Texture PelletTexture = new Texture("dot2.png");
    public Texture MainWallTexture = new Texture("testwall.jpg");
    public Texture GhostWallTexture = new Texture("testwall2.jpg");
    public int nScreenWidth;
    public int nScreenHeight;
    public int nTileWidth;
    public int nTileHeight;
    Scanner sIn;

    public SprMap() {
        super(new Texture(Gdx.files.internal("Maze.png")));
        setPosition(0, 0);
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setFlip(false, true);
    }

    public void mapMaker() throws FileNotFoundException {
//        0 is pellets, 1 is walls, 2 is ghost house, 3 is empty.
//        2D Array taken from: https://github.com/Code-Bullet/PacmanGame/blob/master/PacmanGame/PacmanGame.pde
        nScreenWidth = Gdx.graphics.getWidth();
        nScreenHeight = Gdx.graphics.getHeight();
        nTileWidth = nScreenWidth / 27;
        nTileHeight = nScreenHeight / 30;
        sIn = new Scanner(new FileReader("MapArray.txt"));
        int[][] nTileMap = new int[31][28];
        for(int nY = 0; nY < 31; ++nY) {
            for(int nX = 0; nX < 28; ++nX) {
                if (nY == 13 && nX == 0) {
                    alSprMainWalls.add(new SprMainWall((nX - 1) * nTileWidth, nY * nTileHeight, nTileWidth, nTileHeight, MainWallTexture));
                }
                if (nY == 15 && nX == 0) {
                    alSprMainWalls.add(new SprMainWall((nX - 1) * nTileWidth, nY * nTileHeight, nTileWidth, nTileHeight, MainWallTexture));
                }
                if (nY == 13 && nX == 27) {
                    alSprMainWalls.add(new SprMainWall((nX + 1) * nTileWidth, nY * nTileHeight, nTileWidth, nTileHeight, MainWallTexture));
                }
                if (nY == 15 && nX == 27) {
                    alSprMainWalls.add(new SprMainWall((nX + 1) * nTileWidth, nY * nTileHeight, nTileWidth, nTileHeight, MainWallTexture));
                }
                nTileMap[nY][nX] = sIn.nextInt();
                if (nTileMap[nY][nX] == 0) {
                    alSprPellets.add(new SprPellet(nX * nTileWidth + nTileWidth + 0 - 10, nY * nTileHeight + nTileHeight + 0 - 10, PelletTexture));
                } else if (nTileMap[nY][nX] == 1) {
                    alSprMainWalls.add(new SprMainWall(nX * nTileWidth, nY * nTileHeight, nTileWidth, nTileHeight, MainWallTexture));
                } else if (nTileMap[nY][nX] == 2) {
                    alSprGhostHouse.add(new SprGhostHouseWall(nX * nTileWidth, nY * nTileHeight, nTileWidth, nTileHeight, GhostWallTexture));
                }
            }
        }

    }

    public boolean gHitWall(SprMartian sprMartian, boolean bFirst) {
        int nI;
        for(nI = 0; nI < this.alSprMainWalls.size(); ++nI) {
            SprMainWall sprMainWall = (SprMainWall)this.alSprMainWalls.get(nI);
            if (sprMartian.getBoundingRectangle().overlaps(sprMainWall.getBoundingRectangle())) {
                sprMartian.outOfBounds();
                return true;
            }
        }
        if (!bFirst) {
            for(nI = 0; nI < this.alSprGhostHouse.size(); ++nI) {
                SprGhostHouseWall sprGhostWall = (SprGhostHouseWall)this.alSprGhostHouse.get(nI);
                if (sprMartian.getBoundingRectangle().overlaps(sprGhostWall.getBoundingRectangle())) {
                    sprMartian.outOfBounds();
                }
            }
        }

        return false;
    }

    public boolean hHitWall(SprHamster sprHamster) {
        //    Hamster Actual
        for (int nI = 0; nI < alSprMainWalls.size(); nI++) {
            SprMainWall sprMainWall = alSprMainWalls.get(nI);
            if (sprHamster.getBoundingRectangle().overlaps(sprMainWall.getBoundingRectangle())) {
                sprHamster.outOfBounds();
                return true;

            }
        }
        for (int nI = 0; nI < alSprGhostHouse.size(); nI++) {
            SprGhostHouseWall sprGhostWall = alSprGhostHouse.get(nI);
            if (sprHamster.getBoundingRectangle().overlaps(sprGhostWall.getBoundingRectangle())) {
                sprHamster.outOfBounds();
                return true;
            }
        }
        return false;
    }

    public void pHitWall(SprPlayableMartian sprPMartian) {
        //    Ghost
        for (int nI = 0; nI < alSprMainWalls.size(); nI++) {
            SprMainWall sprMainWall = alSprMainWalls.get(nI);
            if (sprPMartian.getBoundingRectangle().overlaps(sprMainWall.getBoundingRectangle())) {
                sprPMartian.outOfBounds();
            }

        }
    }

    public boolean bPlayCheckWall(Sprite spr, float x, float y, boolean bHam) {
        Rectangle r1 = spr.getBoundingRectangle();
        r1.setX(x);
        r1.setY(y);
        int nI;
        for(nI = 0; nI < alSprMainWalls.size(); ++nI) {
            SprMainWall sprMainWall = (SprMainWall)alSprMainWalls.get(nI);
            if (r1.overlaps(sprMainWall.getBoundingRectangle())) {
                return true;
            }
        }
        if (bHam) {
            for(nI = 0; nI < alSprGhostHouse.size(); ++nI) {
                SprGhostHouseWall sprGhostWall = (SprGhostHouseWall)alSprGhostHouse.get(nI);
                if (r1.overlaps(sprGhostWall.getBoundingRectangle())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void warpingEdge(Sprite spr1) {
        float fX = spr1.getX(), fScreenWidth = Gdx.graphics.getWidth();
        float fWidth = spr1.getWidth()/2;
        if (fX + fWidth < 0) {
            spr1.setX(fScreenWidth);
        } else if (fX - fWidth > fScreenWidth) {
            spr1.setX(0);
        }
    }
}

