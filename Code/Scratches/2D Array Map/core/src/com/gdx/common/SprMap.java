package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.*;
import java.io.*;

public class SprMap extends Sprite {
    public ArrayList<SprMainWall> alSprMainWalls = new ArrayList<SprMainWall>();
    public ArrayList<SprGhostHouseWall> alSprGhostHouse = new ArrayList<SprGhostHouseWall>();
    public ArrayList<SprPellet> alSprPellets = new ArrayList<SprPellet>();
    Scanner sIn;

    public SprMap() {
        super(new Texture(Gdx.files.internal("Maze.png")));
        setPosition(0, 0);
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setFlip(false, true);
    }

    public void mapMaker () throws FileNotFoundException {
//        0 is pellets, 1 is walls, 2 is ghost house, 3 is empty.
        int nScreenWidth = Gdx.graphics.getWidth();
        sIn = new Scanner(new FileReader("MapArray.txt"));
        int nScreenHeight = Gdx.graphics.getHeight();
        int nWidth = nScreenWidth / 27;
        int nHeight = nScreenHeight / 30;
        //2D Array taken from: https://github.com/Code-Bullet/PacmanGame/blob/master/PacmanGame/PacmanGame.pde
        int[][] nTileMap = new int[31][28];
        for (int nY = 0; nY < 31; nY++) {
            for (int nX = 0; nX < 28; nX++) {
                nTileMap[nY][nX] = sIn.nextInt();
                if (nTileMap[nY][nX] == 0) {
                    alSprPellets.add(new SprPellet(nX * nWidth + (nWidth / 2), nY * nHeight + (nHeight / 2)));
                } else if (nTileMap[nY][nX] == 1) {
                    alSprMainWalls.add(new SprMainWall(nX * nWidth, nY * nHeight, nWidth, nHeight));
                } else if (nTileMap[nY][nX] == 2) {
                    alSprGhostHouse.add(new SprGhostHouseWall(nX * nWidth, nY * nHeight, nWidth, nHeight));
                }
            }
        }
    }
}

