package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.map.Map;

public class Container extends Game {

    //public static final String TITLE = "Radioactive Hamsters (On the Moon!)";
    public static final String TITLE = "Map Test";
    Map map;
    int nScreen;

    public void updateState() {
        setScreen(map);
    }

    @Override
    public void create() {
        map = new Map(this);
        updateState();

    }

    @Override
    public void render() {
        super.render();
    }
}
