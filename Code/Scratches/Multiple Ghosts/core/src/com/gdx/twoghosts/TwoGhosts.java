package com.gdx.twoghosts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.gdx.sprites.SprGhost;

public class TwoGhosts extends Game {
	SpriteBatch batch;
	int nGhostdX, nGhostdY;
	boolean bMovement;
	OrthographicCamera ocCam;
	SprGhost arGhost[] = new SprGhost[4];

	@Override
	public void create() {
		ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		ocCam.update();
		batch = new SpriteBatch();
		arGhost[0] = new SprGhost(375, 300, 30, 30);
		arGhost[1] = new SprGhost(100, 250, 30, 30);
		arGhost[2] = new SprGhost(50, 250, 30, 30);
		arGhost[3] = new SprGhost(200,50,30,30);
		nGhostdX = 0;
		nGhostdY = 0;
		bMovement = false;
		ocCam = new OrthographicCamera();
	}

	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for (int nNum = 0; nNum <= 3; nNum++) {
			arGhost[nNum].Movement();
			arGhost[nNum].isOut = isOutOfBounds(arGhost[nNum]);
		}
		batch.begin();
		for (int nNum2 = 0; nNum2 <= 3; nNum2++) {
			arGhost[nNum2].draw(batch);
		}
		batch.end();
		// nNum and nNum2 are placeholders for the loop
	}

	public static boolean isOutOfBounds(Sprite spr) {
		if (0 < spr.getX() && spr.getX() + spr.getWidth() < Gdx.graphics.getWidth() && 0 < spr.getY() && spr.getY() + spr.getHeight() < Gdx.graphics.getHeight()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void resize(int i, int i1) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose(){
		batch.dispose();
	}
}

