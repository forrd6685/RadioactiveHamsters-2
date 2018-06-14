package com.gdx.pathfinding;

import com.badlogic.gdx.*;
import com.gdx.Screens.ScrQuad;
import com.gdx.Screens.ScrWall;


public class Pathfinding extends Game {
	ScrQuad scrQuad;
	ScrWall scrWall;
	int nScreen;

	public void updateState(int _nScreen) {
		nScreen = _nScreen;
		if (nScreen == 0) {
			setScreen(scrQuad);
		} else if (nScreen == 1) {
			setScreen(scrWall);
		}
	}

	@Override
	public void create() {
		nScreen = 0;
		scrQuad = new ScrQuad(this);
		scrWall = new ScrWall(this);
		updateState(nScreen);
	}

	@Override
	public void render() {
		super.render();
	}
}

