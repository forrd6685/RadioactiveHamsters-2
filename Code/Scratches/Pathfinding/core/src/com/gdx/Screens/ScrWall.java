package com.gdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.pathfinding.Pathfinding;
import com.gdx.sprites.SprMartian;
import com.gdx.sprites.SprWall;

import java.util.ArrayList;

public class ScrWall implements Screen {
    public ArrayList<SprWall> alSprWalls = new ArrayList<SprWall>();
    SprMartian sprMartian;
    Pathfinding pathfinding;
    SpriteBatch batch;


    public ScrWall(Pathfinding _pathfinding) {
        pathfinding = _pathfinding;
        alSprWalls.add(new SprWall(100, 0, 50, 250));
        alSprWalls.add(new SprWall(100, 300, 50, 200));
        alSprWalls.add(new SprWall(200, 0, 50, 150));
        alSprWalls.add(new SprWall(200, 200, 50, 300));
        sprMartian = new SprMartian(Gdx.graphics.getWidth() - 40, Gdx.graphics.getHeight()/2, 20,20);
        batch = new SpriteBatch();
    }

    public boolean isOutOfBounds(Sprite spr) {
        if (0 < spr.getX() && spr.getX() + spr.getWidth() < Gdx.graphics.getWidth() && 0 < spr.getY() && spr.getY() + spr.getHeight() < Gdx.graphics.getHeight()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sprMartian.WallMovement();
        sprMartian.isOut = isOutOfBounds(sprMartian);
        if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            pathfinding.updateState(0);
        }

        batch.begin();
        for(int nI = 0; nI < alSprWalls.size(); nI++) {
            alSprWalls.get(nI).draw(batch);
        }
        sprMartian.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}