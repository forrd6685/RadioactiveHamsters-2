package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.screens.ScrGameOver;
import com.gdx.screens.ScrPlay;
import com.gdx.screens.ScrMenu;
import com.gdx.screens.ScrWin;

public class GamHamsters extends Game {

    ScrPlay ScrPlay;
    ScrGameOver ScrGameOver;
    ScrMenu ScrMenu; 
    ScrWin ScrWin;
    int nScreen;

    public void updateState(int _nScreen) {
        nScreen = _nScreen;
        if (nScreen == 0) {
            setScreen(ScrMenu);
        } else if (nScreen == 1) {
            setScreen(ScrPlay);
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
        ScrPlay = new ScrPlay(this);
        ScrGameOver = new ScrGameOver(this);
        ScrWin = new ScrWin(this);
        updateState(nScreen);
    }

    @Override
    public void render() {
        super.render();
    }
}
