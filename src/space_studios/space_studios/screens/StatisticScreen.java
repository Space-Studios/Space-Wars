package space_studios.screens;

import space_studios.core.SpaceWarsCore;
import space_studios.objects.Constants;
import space_studios.objects.Statistics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class StatisticScreen implements Screen{
	final SpaceWarsCore core;
	
	private Texture tex_StatisticsBLU;
	private Sprite spr_StatisticsBLU;
	private Texture tex_StatisticsRED;
	private Sprite spr_StatisticsRED;
	
	private String win;
	
	public StatisticScreen(final SpaceWarsCore coreInput, String winner) {
		core = coreInput;
		
		//statistics
		tex_StatisticsBLU = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Statistics Screen/Statistics Screen BLUEWON.png"));
		spr_StatisticsBLU = new Sprite(tex_StatisticsBLU,0,0,1920,1080);
		tex_StatisticsRED = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Statistics Screen/Statistics Screen REDWON.png"));
		spr_StatisticsRED = new Sprite(tex_StatisticsRED,0,0,1920,1080);
		
		spr_StatisticsBLU.setSize(Constants.display_width, Constants.display_height);
		spr_StatisticsRED.setSize(Constants.display_width, Constants.display_height);
		
		win = winner;
	}
	
	@Override
	public void render(float delta) {
		core.batch.begin();
		spr_StatisticsBLU.setPosition(0,0);
		spr_StatisticsRED.setPosition(0,0);
		if (win.equals("red")) {
			spr_StatisticsRED.draw(core.batch);
		}
		else if (win.equals("blue")){
			spr_StatisticsBLU.draw(core.batch);
		}
		core.font.draw(core.batch, "Total Money Earned:$"+Statistics.totalInGameMoneyEarned, (Constants.room_width * 860)/1920, (Constants.room_height * (1080-565))/1080);
		core.font.draw(core.batch, "Suicide Ships Created: "+Statistics.blueSuicideShipCreation, (Constants.room_width * 325)/1920, (Constants.room_height * (1080-425))/1080);
		core.font.draw(core.batch, "Shooter Ships Created: "+Statistics.blueShooterShipCreation, (Constants.room_width * 325)/1920, (Constants.room_height * (1080-510))/1080);
		core.font.draw(core.batch, "Blocker Ships Created: "+Statistics.blueBlockerShipCreation, (Constants.room_width * 325)/1920, (Constants.room_height * (1080-602))/1080);
		core.font.draw(core.batch, "Red Ships Destroyed: "+Statistics.blueKills, (Constants.room_width * 325)/1920, (Constants.room_height * (1080-700))/1080);
		core.font.draw(core.batch, "Suicide Ship Creation: "+Statistics.redSuicideShipCreation, (Constants.room_width * 1267)/1920, (Constants.room_height * (1080-413))/1080);
		core.font.draw(core.batch, "Shooter Ship Creation: "+Statistics.redShooterShipCreation, (Constants.room_width * 1267)/1920, (Constants.room_height * (1080-510))/1080);
		core.font.draw(core.batch, "Blocker Ship Creation: "+Statistics.redBlockerShipCreation, (Constants.room_width * 1267)/1920, (Constants.room_height * (1080-602))/1080);
		core.font.draw(core.batch, "Blue Ships Destroyed: "+Statistics.redKills, (Constants.room_width * 1267)/1920, (Constants.room_height * (1080-710))/1080);
		core.batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			if (ScreenManager.credits == null) {
				ScreenManager.credits = new Credits(core);
				core.setScreen(ScreenManager.credits);
			} else {
				core.setScreen(ScreenManager.credits);
			}
		}
	}
	
	@Override
	public void dispose() {
		tex_StatisticsBLU.dispose();
		tex_StatisticsRED.dispose();
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resize(int arg0, int arg1) {}

	@Override
	public void resume() {}

	@Override
	public void show() {}
}
