package com.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AniMoonExplode extends Sprite {

    Texture txSheet;
    Sprite sprTemp;
    int nCol, nRow;
    SpriteSheetAnimator spriteSheetAnimator;
    Animation[] arAnimation;

    public AniMoonExplode(int nX, int nY) {
        super(new Texture(Gdx.files.internal("MoonExplosion.png")));
        setPosition(nX, nY);
        txSheet = new Texture("MoonExplosion.png");
        spriteSheetAnimator = new SpriteSheetAnimator(txSheet, 6, 10, 2);
        arAnimation = spriteSheetAnimator.animate();

    }

    public void reset() {
        nCol = 0;
        nRow = 0;
    }

    public Sprite animate() {
        if(nRow == 5 && nCol ==10) {
        } else if (nCol>=10) {
            nRow += 1;
            nCol = 0;
        } else {
            nCol+=1;
        }
        sprTemp = (Sprite) arAnimation[nRow].getKeyFrame(nCol, false);
        return sprTemp;
    }
}
