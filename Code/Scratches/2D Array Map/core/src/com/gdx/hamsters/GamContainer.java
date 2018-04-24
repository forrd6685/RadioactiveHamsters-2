package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.map.scrMap;

public class GamContainer extends Game {

    //public static final String TITLE = "Radioactive Hamsters (On the Moon!)";
    public static final String TITLE = "ArrayListMap";
    public static final int SCREENWIDTH = 448;
    public static final int SCREENHEIGHT = 496;

    scrMap scrmap;

    int nScreen;

    public void updateState() {
        setScreen(scrmap);
    }

    @Override
    public void create() {
        scrmap = new scrMap(this);
        updateState();

    }

    @Override
    public void render() {
        super.render();
    }
}
