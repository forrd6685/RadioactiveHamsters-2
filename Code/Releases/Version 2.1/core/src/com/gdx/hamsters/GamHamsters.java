package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.screens.*;

public class GamHamsters extends Game {

    ScrPlay ScrPlay;
    ScrGameOver ScrGameOver;
    ScrMenu ScrMenu;
    ScrWin ScrWin;
    ScrOptions ScrOptions;
    ScrPlayers ScrPlayers;
    int nScreen;
    public static final String TITLE = "Radioactive Hamsters (On the Moon!)";
    public static final int SCREENWIDTH = 448;
    public static final int SCREENHEIGHT = 496;


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
        } else if (nScreen == 4) {
            setScreen(ScrOptions);
        } else if (nScreen == 5) {
            setScreen(ScrPlayers);
        }
    }

    @Override
    public void create() {
        nScreen = 0;
        ScrMenu = new ScrMenu(this);
        ScrPlay = new ScrPlay(this);
        ScrGameOver = new ScrGameOver(this);
        ScrWin = new ScrWin(this);
        ScrOptions = new ScrOptions(this);
        ScrPlayers = new ScrPlayers(this);
        updateState(nScreen);
    }

    @Override
    public void render() {
        super.render();
    }
}