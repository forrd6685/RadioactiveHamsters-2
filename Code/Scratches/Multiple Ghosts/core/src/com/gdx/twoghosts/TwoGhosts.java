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
	int nGhostDirOld, nGhostDirNew, nGhost2DirOld, nGhost2DirNew, nGhostdX, nGhostdY, nRanGhostMove;
	boolean bMovement, bGhostOOB, bGhost2OOB;
	OrthographicCamera ocCam;
	SprGhost sprGhost, sprGhost2;

	@Override
	public void create() {
		ocCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		ocCam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		ocCam.update();
		batch = new SpriteBatch();
		sprGhost = new SprGhost(375, 300, 30, 30);
		sprGhost2 = new SprGhost(100, 250, 30, 30);
		nGhostdX = 0;
		nGhostdY = 0;
		bMovement = false;
		ocCam = new OrthographicCamera();
	}

	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		nRanGhostMove = (int) (Math.random() * 50 + 1);

		if (nRanGhostMove == 1) {
			nGhostDirOld = nGhostDirNew;
			nGhostDirNew = sprGhost.GhostDirection(nGhostDirOld, nGhostDirNew);
		}

		if (nRanGhostMove == 2) {
			nGhost2DirOld = nGhost2DirNew;
			nGhost2DirNew = sprGhost2.GhostDirection(nGhost2DirOld, nGhost2DirNew);
		}
		sprGhost.Movement(nGhostDirNew);
		sprGhost2.Movement(nGhost2DirNew);
		bGhostOOB = isOutOfBounds(sprGhost);
		while (bGhostOOB == true) {
			sprGhost.OOB();
			nGhostDirOld = nGhostDirNew;
			nGhostDirNew = sprGhost.GhostDirection(nGhostDirOld, nGhostDirNew);
			sprGhost.Movement(nGhostDirNew);
			bGhostOOB = isOutOfBounds(sprGhost);
		}
		bGhost2OOB = isOutOfBounds(sprGhost2);
		while (bGhost2OOB == true) {
			sprGhost2.OOB();
			nGhost2DirOld = nGhost2DirNew;
			nGhost2DirNew = sprGhost2.GhostDirection(nGhost2DirOld, nGhost2DirNew);
			sprGhost2.Movement(nGhost2DirNew);
			bGhost2OOB = isOutOfBounds(sprGhost2);
		}

		batch.begin();
		sprGhost.draw(batch);
		sprGhost2.draw(batch);
		batch.end();
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

