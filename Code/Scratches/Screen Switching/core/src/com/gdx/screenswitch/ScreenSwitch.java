package com.gdx.screenswitch;



import com.badlogic.gdx.Game;


public class ScreenSwitch extends Game {

    
    ScrSwitch1 ScrSwitch1;
    ScrSwitch2 ScrSwitch2;
    
    int nScreen;

    @Override
    public void create() {
        nScreen = 1;
        ScrSwitch1 = new ScrSwitch1(this);
        ScrSwitch2 = new ScrSwitch2(this);
        updateState(nScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    void updateState(int _nScreen) {
         nScreen = _nScreen;
        if (nScreen == 1) {
            setScreen(ScrSwitch1);
        } else if (nScreen == 2) {
            setScreen(ScrSwitch2);
        } 
    }
}