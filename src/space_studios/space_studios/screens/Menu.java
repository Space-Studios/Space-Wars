package space_studios.screens;

import space_studios.core.SpaceWarsCore;
import space_studios.core.SpaceWarsDuel;
import space_studios.core.SpaceWarsSolo;
import space_studios.objects.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu implements Screen{
	private Texture tex_title;
	private Sprite spr_title;
	
	private Texture tex_duel;
	private Sprite spr_duel;
	
	private Texture tex_solo;
	private Sprite spr_solo;
	
	private Button duel;
	private Button solo;
	
	private OrthographicCamera cam;
	final SpaceWarsCore core;
	
	public Menu(final SpaceWarsCore coreInput) {
		core = coreInput;
		cam = new OrthographicCamera();
		cam.setToOrtho(false,1024,480);
		
		//title
		tex_title = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/Title Screen Image .png"));
		spr_title = new Sprite(tex_title,0,0,1920,1080);
		
		
		//duel start screen
		tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartDuelA.png"));
		spr_duel = new Sprite(tex_duel);
		spr_duel.setPosition(Constants.display_width/2-256,Constants.display_height/8);

		
		//solo start screen
		tex_solo = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartSoloA.png"));
		spr_solo = new Sprite(tex_solo);
		spr_solo.setPosition(Constants.display_width/2,Constants.display_height/8);
		
		//for buttons, we need a selector sprite
		Texture seltex = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/selector.png"));
		Sprite selspr = new Sprite(seltex);
		//buttons!
		solo = new Button(spr_solo,selspr);
		duel = new Button(spr_duel,selspr);
		
		solo.SetPosition(Constants.display_width/2, Constants.display_height/8);
		duel.SetPosition(Constants.display_width/2-256,Constants.display_height/8);
		
		spr_title.setSize(Constants.display_width, Constants.display_height);
	}
	
	@Override
	public void render(float delta) {
		core.batch.begin();
		spr_title.draw(core.batch);
		//spr_solo.draw(core.batch);
		//spr_duel.draw(core.batch);
		solo.draw(core.batch);
		duel.draw(core.batch);
		
		//highlightButton();
		core.batch.end();
	}
	public void updateButtons(){ //10/10 vry simple
		solo.Update();
		duel.Update();
		
		if(solo.pressed){
			core.setScreen(new SpaceWarsSolo(core));
			SpaceWarsCore.inSoloMode = true;
			dispose();
		}
		if(duel.pressed){
			core.setScreen(new SpaceWarsDuel(core));
			dispose();
		}
	}
	
	/*
	public void highlightButton() {
		if (Gdx.input.getX() > Constants.display_width/2 
			&& Gdx.input.getX() < Constants.display_width/2+256
			&& Gdx.input.getY() > 6*Constants.display_height/8 
			&& Gdx.input.getY() < 6*Constants.display_height/8+128) {
			tex_solo = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartSoloB.png"));
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				core.setScreen(new SpaceWarsSolo(core));
				SpaceWarsCore.inSoloMode = true;
				dispose();
			}
		} else {
			tex_solo = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartSoloA.png"));
		}
		if (Gdx.input.getX() > Constants.display_width/2-256 
			&& Gdx.input.getX() < Constants.display_width/2
			&& Gdx.input.getY() > 6*Constants.display_height/8 
			&& Gdx.input.getY() < 6*Constants.display_height/8+128) {
			tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartDuelB.png"));
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				core.setScreen(new SpaceWarsDuel(core));
				dispose();
			}
		} else {
			tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartDuelA.png"));
		}
		spr_solo.setTexture(tex_solo);
		spr_duel.setTexture(tex_duel);
	}
	*/
	
	@Override
	public void dispose() {
		tex_title.dispose();
		tex_solo.dispose();
		tex_duel.dispose();
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
