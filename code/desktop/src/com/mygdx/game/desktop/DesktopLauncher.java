package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=MyGdxGame.WIDTH; // sets window width
        config.height=MyGdxGame.HEIGHT;  // sets window height
		new LwjglApplication(new MyGdxGame(), config);
	}
}
