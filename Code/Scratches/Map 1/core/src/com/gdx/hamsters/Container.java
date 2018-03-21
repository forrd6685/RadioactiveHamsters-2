package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.map.Map;

public class Container extends Game {
    
    //public static final String TITLE = "Radioactive Hamsters (On the Moon!)";
    public static final String TITLE = "Walls Test";
    Map walls;
    int nScreen;

    public void updateState() {
            setScreen(walls);
    }

    @Override
    public void create() {
        walls = new Map(this);
        updateState();
        
    }

    @Override
    public void render() {
        super.render();
    }
}
