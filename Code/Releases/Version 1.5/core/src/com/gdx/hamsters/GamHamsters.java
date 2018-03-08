package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.screens.ScrGameOver;
import com.gdx.scratches.ScrSwitch1;
import com.gdx.scratches.ScrSwitch2;
import com.gdx.screens.ScrMain;
import com.gdx.screens.ScrMenu;
import com.gdx.screens.ScrWin;

public class GamHamsters extends Game {

    ScrMain scrMain;
    ScrSwitch1 ScrSwitch1;
    ScrSwitch2 ScrSwitch2;
    ScrGameOver ScrGameOver;
    ScrMenu ScrMenu; 
    ScrWin ScrWin;
    int nScreen;

    public void updateState(int _nScreen) {
        nScreen = _nScreen;
        if (nScreen == 0) {
            setScreen(ScrSwitch1);
        } else if (nScreen == 1) {
            setScreen(ScrSwitch2);
        } else if (nScreen == 2) {
            setScreen(scrMain);
        } else if (nScreen == 3) {
            setScreen(ScrGameOver);
        } else if (nScreen == 5) {
            setScreen(ScrMenu);
        } else if (nScreen == 6) {
            setScreen(ScrWin);
        }
    }

    @Override
    public void create() {
        nScreen = 5;
        ScrMenu = new ScrMenu(this);
        ScrSwitch1 = new ScrSwitch1(this);
        ScrSwitch2 = new ScrSwitch2(this);
        scrMain = new ScrMain(this);
        ScrGameOver = new ScrGameOver(this);
        ScrWin = new ScrWin(this);
        updateState(nScreen);
    }

    @Override
    public void render() {
        super.render();
    }
}
