package com.gdx.hamsters.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.hamsters.GamHamsters;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = GamHamsters.TITLE;
		config.width = GamHamsters.SCREENWIDTH;
		config.height = GamHamsters.SCREENHEIGHT;
		config.resizable = false;
		new LwjglApplication(new GamHamsters(), config);

	}
}
