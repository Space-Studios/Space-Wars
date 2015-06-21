package space_studios.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import space_studios.core.SpaceWarsCore;
import space_studios.objects.Constants;

public class DesktopLauncher {
	public static void main (String[] args) {
		//define config
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//this sets the text of the top of the window (window caption)
		config.title = Constants.title;
		//ummmm.. I do not know what this does, It may disable openGL 30. But the video says to do it.
		config.useGL30=false;
		//These lines set the width and the height of the window
		config.width = Constants.room_width;
		config.height = Constants.room_height;
		//this line sets fullscreen
		config.fullscreen=Constants.fullscreen;
		//This line creates the window after all the variables have been set
		new LwjglApplication(new SpaceWarsCore(), config);
	}
}
