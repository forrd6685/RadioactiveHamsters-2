package com.gdx.hamsters;

import com.badlogic.gdx.Game;
import com.gdx.walls.Walls;

public class Container extends Game {
    
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
