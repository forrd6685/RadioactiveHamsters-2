package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SprButton extends Sprite implements InputProcessor {
    int nX, nY, nW, nH;

    public SprButton(int _nX, int _nY, int _nW, int _nH, String s) {
        super(new Texture(s));  //For example "SignB.jpg"
        setPosition(_nX, _nY);
        setSize(_nW, _nH);
        Gdx.input.setInputProcessor(this);
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;
    }

    public boolean isClicked(int nScreenX, int nScreenY) {
        if (nScreenX > nX && nScreenX < (nX + nW) && nScreenY < nY + nH && nScreenY > nY) {
            return true;
        }
        return false;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
