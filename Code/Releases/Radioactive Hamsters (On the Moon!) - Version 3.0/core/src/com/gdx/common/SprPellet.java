package com.gdx.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class SprPellet extends Sprite {

    public boolean isEaten = false;

    public SprPellet(int nX, int nY, Texture texture) {
        super(texture);
        setPosition(nX, nY);
        setSize(4, 4);
        setFlip(false, true);
    }

    public void updateStatus(SprHamster sprHamster){
        if(sprHamster.getBoundingRectangle().overlaps(this.getBoundingRectangle())) {
            isEaten = true;
        }
    }
}
