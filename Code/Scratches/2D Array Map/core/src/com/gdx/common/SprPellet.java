/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author forrd6685
 */
public class SprPellet extends Sprite {

    public boolean isEaten;

    public SprPellet(int nX, int nY, Texture texture) {
        super(texture);
        setPosition(nX, nY);
        setSize(5, 5);
        setFlip(false, true);
    }
}
