package space_studios.screens;

import space_studios.core.SpaceWarsCore;
import space_studios.objects.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Credits implements Screen{
	
	final SpaceWarsCore core;
	
	private Texture tex_Credits;
	private Sprite spr_Credits;
	
	private static Boolean creditsMoving;
	private static int creditsYPosition;
	
	public Credits(final SpaceWarsCore coreInput) {
		core = coreInput;
		
		tex_Credits = new Texture(Gdx.files.internal("assets/sprites/Credits.png"));
		spr_Credits = new Sprite(tex_Credits,0,0,1920,3420);
		
		spr_Credits.setSize(Constants.display_width, Constants.display_height*4);
		
		creditsMoving = false;
		
		creditsYPosition = Constants.display_height*(-3);
	}
	
	@Override
	public void render(float delta) {
		core.batch.begin();
		spr_Credits.setPosition(0, creditsYPosition);
		spr_Credits.draw(core.batch);
		//if moving has started, always move the credits down until they are over, and then the game stops
		if (creditsMoving){
			if (creditsYPosition < 0){
				creditsYPosition ++;
			}
			else {
				//if credits are done, make player wait until they push escape
				creditsMoving = false;
			}
		}
		// if the credits have not yet been set to their initial position, the credits will start moving next step
		else {
			creditsMoving = true;
		}
		core.batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			core.setScreen(ScreenManager.menu);
		}
	}
	
	@Override
	public void dispose() {
		tex_Credits.dispose();
	}
	@Override
	public void hide() {}
	@Override
	public void pause() {}
	@Override
	public void resize(int width, int height) {}
	@Override
	public void resume() {}
	@Override
	public void show() {}
}
