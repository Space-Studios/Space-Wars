package space_studios.screens;

import space_studios.core.SpaceWarsCore;
import space_studios.objects.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Win implements Screen{
	
	final SpaceWarsCore core;
	
	private Texture tex_BlueWins;
	private Sprite spr_BlueWins;
	private Texture tex_RedWins;
	private Sprite spr_RedWins;
	
	public static int statisticsWait;
	private static final int statisticsWaitMax = Constants.waitBeforeStatistics;
	
	private String win;
	
	public Win(final SpaceWarsCore coreInput, String winner) {
		core = coreInput;
		
		tex_BlueWins = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Win Screen/Player 1 Blue Wins Screen.png"));
		spr_BlueWins = new Sprite(tex_BlueWins,0,0,1920,1080);
		tex_RedWins = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Win Screen/Player 2 Red Wins Screen.png"));
		spr_RedWins = new Sprite(tex_RedWins,0,0,1920,1080);
		
		spr_RedWins.setSize(Constants.display_width, Constants.display_height);
		spr_BlueWins.setSize(Constants.display_width, Constants.display_height);
		win = winner;
	}
	
	@Override
	public void render(float delta) {
		core.batch.begin();
		if (win.equals("blue")) {
			spr_BlueWins.setPosition(0, 0);
			spr_BlueWins.draw(core.batch);
			core.batch.end();
		}
		if(win.equals("red")){
			spr_RedWins.setPosition(0, 0);
			spr_RedWins.draw(core.batch);
			core.batch.end();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			core.setScreen(new StatisticScreen(core, win));
			System.out.println("Screen change - Statistics screen");
		}
	}
	
	@Override
	public void dispose() {}
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
