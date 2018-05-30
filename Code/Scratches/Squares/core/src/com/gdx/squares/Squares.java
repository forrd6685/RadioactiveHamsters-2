package com.gdx.squares;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.sprites.SprMartian;
import com.gdx.sprites.SprSquare;
import com.gdx.sprites.SprWall;

import java.util.ArrayList;

public class Squares extends ApplicationAdapter {
	public ArrayList<SprWall> alSprWalls = new ArrayList<SprWall>();
	SprMartian sprMartian;
	SpriteBatch batch;
	Texture tx;


	@Override
	public void create () {
		tx = new Texture("Comp Sci.png");

		alSprWalls.add(new SprWall(100, 0, 50, 250));
		alSprWalls.add(new SprWall(100, 300, 50, 200));
		alSprWalls.add(new SprWall(200, 0, 50, 150));
		alSprWalls.add(new SprWall(200, 200, 50, 300));
		sprMartian = new SprMartian(150, Gdx.graphics.getHeight() - 50, 20,20);
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sprMartian.Movement();
		sprMartian.isOut = isOutOfBounds(sprMartian);

		if(SquareHit(sprMartian.squareL)) {
			System.out.println("Out");
			sprMartian.nCurrentDir = 2;
			sprMartian.Movement();
		}
		batch.begin();
		for(int nI = 0; nI < alSprWalls.size(); nI++) {
			alSprWalls.get(nI).draw(batch);
		}
		sprMartian.draw(batch);
		batch.draw(tx, 100, -500, 1000, 1000);
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) ) {
			sprMartian.squareL.draw(batch);
			sprMartian.squareR.draw(batch);
		}
		batch.end();
	}

	public boolean isOutOfBounds(Sprite spr) {
		if (0 < spr.getX() && spr.getX() + spr.getWidth() < Gdx.graphics.getWidth() && 0 < spr.getY() && spr.getY() + spr.getHeight() < Gdx.graphics.getHeight()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean SquareHit(SprSquare s) {
		for(int nI = 0; nI < alSprWalls.size(); nI++) {
			if (s.getBoundingRectangle().overlaps(alSprWalls.get(nI).getBoundingRectangle())) {
				return false;
			}

		}
		return true;
		}
	}

