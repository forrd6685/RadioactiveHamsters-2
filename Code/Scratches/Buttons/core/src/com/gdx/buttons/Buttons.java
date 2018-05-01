package com.gdx.buttons;


import com.badlogic.gdx.Game;
import com.gdx.screens.ScrPaul;
import com.gdx.screens.ScrExplosion;
import com.gdx.screens.ScrMain;
import com.gdx.screens.ScrPattern;



public class Buttons extends Game {
	ScrExplosion ScrExplosion;
	ScrMain ScrMain;
	ScrPattern ScrPattern;
	ScrPaul ScrPaul;
	int nScreen;

	public void updateState(int _nScreen) {
		nScreen = _nScreen;
		if (nScreen == 0) {
			setScreen(ScrMain);
		} else if (nScreen == 1) {
			setScreen(ScrExplosion);
		} else if (nScreen == 2) {
			setScreen(ScrPaul);
		} else if (nScreen == 3) {
			setScreen(ScrPattern);
		}
	}

	@Override
	public void create() {
		nScreen = 0;
		ScrExplosion = new ScrExplosion(this);
		ScrMain = new ScrMain(this);
		ScrPaul = new ScrPaul(this);
		ScrPattern = new ScrPattern(this);
		updateState(nScreen);
	}

	@Override
	public void render() {
		super.render();
	}
}
