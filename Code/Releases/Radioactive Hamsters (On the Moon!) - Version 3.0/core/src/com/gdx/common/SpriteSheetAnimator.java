package com.gdx.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SpriteSheetAnimator {
    private Sprite TempSpriteSheet;
    private Texture TextureSheet;
    private Animation arAnimation[];
    private int fW, fH, fX, fY; // height and width of TempSpriteSheet image - and the starting upper coordinates on the Sprite Sheet
    private int nRows, nColumns;
    private float fChangeRate;

    public SpriteSheetAnimator(Texture TextureSheet, int nRows, int nColumns, int fChangeRate) {
        this.TextureSheet = TextureSheet;
        this.nRows = nRows;
        this.nColumns = nColumns;
        arAnimation = new Animation[nRows];
        this.fChangeRate = fChangeRate;
    }
    public Animation[] animate () {
        fW = TextureSheet.getWidth() / nColumns;
        fH = TextureSheet.getHeight() / nRows;
        for (int i = 0; i < nRows; i++) {
            Sprite[] arTempSprites = new Sprite[nColumns];
            for (int j = 0; j < nColumns; j++) {
                fX = j * fW;
                fY = i * fH;
                TempSpriteSheet = new Sprite(TextureSheet, fX, fY, fW, fH);
                arTempSprites[j] = new Sprite(TempSpriteSheet);
            }
            arAnimation[i] = new Animation(fChangeRate, arTempSprites);
        }
        return arAnimation;
    }
}
