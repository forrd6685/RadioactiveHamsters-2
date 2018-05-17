package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprPlayableMartian extends Sprite{
    int nDx, nDy, nCurrentDir, nPos, nOrigX, nOrigY;
    boolean bFirst = true;
    public Animation arAnimation[];
    Texture txSheet;
    boolean bFlip;
    SpriteSheetAnimator spriteSheetAnimator;
    public Sprite sprTemp;
    public SprPlayableMartian(int nX, int nY, int nW, int nH){
        
    super(new Texture(Gdx.files.internal("MartianAni.png")));
        txSheet = new Texture("MartianAni.png");
        spriteSheetAnimator = new SpriteSheetAnimator(txSheet, 3, 8, 1);
        arAnimation = spriteSheetAnimator.animate();
        setSize(nW, nH);
        setPosition(nX, nY);
        nOrigX = nX;
        nOrigY = nY;
        nDx = 0;
        nDy = 0;
        nCurrentDir = -1;
}
}
