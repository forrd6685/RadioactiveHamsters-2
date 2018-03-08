package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.walls.Walls;

public class Container extends Game {
    
    //public static final String TITLE = "Radioactive Hamsters (On the Moon!)";
    public static final String TITLE = "Walls Test";
    Walls walls;
    int nScreen;

    public void updateState() {
            setScreen(walls);
    }

    @Override
    public void create() {
        walls = new Walls(this);
        updateState();
        
    }

    @Override
    public void render() {
        super.render();
    }
}
