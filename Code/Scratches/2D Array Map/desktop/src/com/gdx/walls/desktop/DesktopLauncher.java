package com.gdx.walls.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.hamsters.GamContainer;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = GamContainer.TITLE;
        config.width = GamContainer.SCREENWIDTH;
        config.height = GamContainer.SCREENHEIGHT;
        new LwjglApplication(new GamContainer(), config);

    }
}
