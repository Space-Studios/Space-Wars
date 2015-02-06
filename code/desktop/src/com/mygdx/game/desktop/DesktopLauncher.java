package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//this sets the text of the top of the window (window caption)
		config.title = "SPACE IS EPIC";
		//ummmm.. I do not know what this does, It may disable openGL 30. But the video says to do it.
		config.useGL30=false;
		//These lines set the width and the height of the window
		config.width = 1024;
		config.height = 480;
		//This line creates the window after all the variables have been set
		new LwjglApplication(new MyGdxGame(), config);
		
	}
}
