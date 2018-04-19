package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.screens.ScrGameOver;
import com.gdx.screens.ScrMain;
import com.gdx.screens.ScrMenu;
import com.gdx.screens.ScrWin;

public class GamHamsters extends Game {

    ScrMain ScrMain;
    ScrGameOver ScrGameOver;
    ScrMenu ScrMenu;
    ScrWin ScrWin;
    int nScreen;
    public static final String TITLE = "Radioactive Hamsters (On the Moon!)";
    public static final int SCREENWIDTH = 448;
    public static final int SCREENHEIGHT = 496;
    public static final int SCALE = 2;

    public void updateState(int _nScreen) {
        nScreen = _nScreen;
        if (nScreen == 0) {
            setScreen(ScrMenu);
        } else if (nScreen == 1) {
            setScreen(ScrMain);
        } else if (nScreen == 2) {
            setScreen(ScrGameOver);
        } else if (nScreen == 3) {
            setScreen(ScrWin);
        }
    }

    @Override
    public void create() {
        nScreen = 0;
        ScrMenu = new ScrMenu(this);
        ScrMain = new ScrMain(this);
        ScrGameOver = new ScrGameOver(this);
        ScrWin = new ScrWin(this);
        updateState(nScreen);
    }

    @Override
    public void render() {
        super.render();
    }
}