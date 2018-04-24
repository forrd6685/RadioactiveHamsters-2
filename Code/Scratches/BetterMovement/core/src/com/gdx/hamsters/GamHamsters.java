package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.screens.ScrMain;
import com.gdx.screens.ScrMenu;

public class GamHamsters extends Game {

    ScrMain ScrMain;
    ScrMenu ScrMenu;
    int nScreen;
    public static final String TITLE = "Radioactive Hamsters (On the Moon!)";
    public static final int SCREENWIDTH = 448;
    public static final int SCREENHEIGHT = 496;

    public void updateState(int _nScreen) {
        nScreen = _nScreen;
        if (nScreen == 0) {
            setScreen(ScrMenu);
        } else if (nScreen == 1) {
            setScreen(ScrMain);
        }
    }

    @Override
    public void create() {
        nScreen = 0;
        ScrMenu = new ScrMenu(this);
        ScrMain = new ScrMain(this);
        updateState(nScreen);
    }

    @Override
    public void render() {
        super.render();
    }
}