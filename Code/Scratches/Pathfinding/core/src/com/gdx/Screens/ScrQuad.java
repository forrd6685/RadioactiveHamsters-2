package com.gdx.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.pathfinding.Pathfinding;
import com.gdx.sprites.SprHamster;
import com.gdx.sprites.SprMartian;

public class ScrQuad implements Screen, InputProcessor {
    int nWantMove;
    Pathfinding pathfinding;
    SpriteBatch batch;
    public SprHamster sprHamster;
    SprMartian sprMartian;

    public ScrQuad(Pathfinding _pathfinding) {
        pathfinding = _pathfinding;
        batch = new SpriteBatch();
        sprHamster = new SprHamster(215, 369, 25, 25);
        sprMartian = new SprMartian(215, 232, 25, 25);
    }



    public void show () {

        Gdx.input.setInputProcessor(this);
    }


    public void render (float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sprHamster.move(nWantMove);
        sprMartian.Movement(sprHamster);
        sprMartian.isOut = isOutOfBounds(sprMartian);

        if(isOutOfBounds(sprHamster)) {
            sprHamster.outOfBounds();
        }
        //if(sprHamster.Quad != sprMartian.Quad) {
        //	System.out.println("Where are you?");
        if(sprHamster.Quad.nQuad == sprMartian.Quad.nQuad) {
            System.out.println("There you are!");
        }
        if(Gdx.input.isKeyPressed( Input.Keys.SPACE)) {
            pathfinding.updateState(1);
        }
        batch.begin();
        sprMartian.draw(batch);
        sprHamster.draw(batch);
        batch.end();
    }



    public static boolean isOutOfBounds(Sprite spr1){
        if(0<spr1.getX()&&spr1.getX()+spr1.getWidth()<Gdx.graphics.getWidth()&&0<spr1.getY()&&spr1.getY()+spr1.getHeight()<Gdx.graphics.getHeight()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            nWantMove = 1;
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            nWantMove = 2;
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            nWantMove = 3;
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            nWantMove = 4;
        }
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

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {

    }
}
