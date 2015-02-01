package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
    public static final String TITLE="Game Project"; 
    public static final int WIDTH=800,HEIGHT=480; // used later to set window size
    
    @Override
    public void create() {
            setScreen(new Splash());
    }
}