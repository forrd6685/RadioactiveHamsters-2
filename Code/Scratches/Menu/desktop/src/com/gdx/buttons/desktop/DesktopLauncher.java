package com.gdx.buttons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.buttons.Buttons;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Buttons.TITLE;
		config.width = Buttons.SCREENWIDTH;
		config.height = Buttons.SCREENHEIGHT;
		config.resizable = false;
		new LwjglApplication(new Buttons(), config);
	}
}
