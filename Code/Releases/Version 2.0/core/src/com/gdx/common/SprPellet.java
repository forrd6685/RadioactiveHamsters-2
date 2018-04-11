package com.gdx.common;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author forrd6685
 */
public class SprPellet extends Sprite {

    public static boolean isDead = false;

     public SprPellet(int nX, int nY) {
        super(new Texture(Gdx.files.internal("dot.png")));
        setPosition(nX, nY);
        setSize(10, 10);        
    }
     
  }

