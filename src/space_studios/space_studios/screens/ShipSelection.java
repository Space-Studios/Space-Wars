package space_studios.screens;

import space_studios.core.SpaceWarsCore;
import space_studios.objects.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ShipSelection implements Screen{
	private Texture tex_title;
	private Sprite spr_title;
	
	private Texture tex_myships;
	private Sprite spr_myships;
	
	private Texture tex_selector;
	private Sprite spr_selector;
	
	private Texture tex_rarrow;
	private Texture tex_larrow;
	private Sprite spr_rarrow;
	private Sprite spr_larrow;
	
	//All the ship textures and sprites....
	private Texture[] tex_suicideShips;
	private Sprite[] spr_suicideShips;
	private Texture[] tex_shooterShips;
	private Sprite[] spr_shooterShips;
	private Texture[] tex_blockerShips;
	private Sprite[] spr_blockerShips;
	
	//scroll vars
	public int scrollX1 = 0;
	public int scrollX2 = 0;
	public int scrollX3 = 0;
	
	
	
	final SpaceWarsCore core;
	public ShipSelection (SpaceWarsCore gamecore){
		this.core = gamecore;
		
		//create the sprites!
		//tex_title = new Texture
		//spr_title = new Sprite (tex_title);
		
		tex_myships = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Ship Selection/leftbar.png"));
		spr_myships = new Sprite(tex_myships,0,0,300,800);
		spr_myships.setScale(Constants.ScreenScaleX(),Constants.ScreenScaleY());
		
		tex_selector = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Ship Selection/selector.png"));
		spr_selector = new Sprite(tex_myships,0,0,128,128);
		spr_selector.setScale(Constants.ScreenScaleX()/2); //this sprite will be for buttons to referance!
		
		tex_rarrow  = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Ship Selection/arrow1.png"));
		spr_rarrow = new Sprite(tex_myships,0,0,128,128);
		spr_rarrow.setScale(Constants.ScreenScaleX()/2);
		
		tex_larrow  = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Ship Selection/arrow2.png"));
		spr_rarrow = new Sprite(tex_myships,0,0,128,128);
		spr_rarrow.setScale(Constants.ScreenScaleX()/2);
		
		//generate these arrays!
		tex_suicideShips = new Texture[Constants.suicideShipPaths.length];
		spr_suicideShips = new Sprite[Constants.suicideShipPaths.length];
		
		tex_shooterShips = new Texture[Constants.shooterShipPaths.length];
		spr_shooterShips = new Sprite[Constants.shooterShipPaths.length];
		
		tex_blockerShips = new Texture[Constants.blockerShipPaths.length];
		spr_blockerShips = new Sprite[Constants.blockerShipPaths.length];
		
		for (int i = 0; i < tex_suicideShips.length;i++){
			tex_suicideShips[i] = new Texture(Gdx.files.internal(Constants.suicideShipPaths[i]));
			spr_suicideShips[i] = new Sprite (tex_suicideShips[i]);
		}
		for (int i = 0; i < tex_shooterShips.length;i++){
			tex_shooterShips[i] = new Texture(Gdx.files.internal(Constants.shooterShipPaths[i]));
			spr_shooterShips[i] = new Sprite (tex_shooterShips[i]);
		}
		for (int i = 0; i < tex_suicideShips.length;i++){
			tex_blockerShips[i] = new Texture(Gdx.files.internal(Constants.blockerShipPaths[i]));
			spr_blockerShips[i] = new Sprite (tex_blockerShips[i]);
		}
		//now that the arrays are generated, we can finally move the sprites to where they should belong
		//0,0 = bottom left!
		spr_myships.setPosition(0, 0);
		//spr_title.setPosition(x, y);
		
		//make buttons based on arrows
		Button r1 = new Button(spr_rarrow,new Sprite(spr_selector));
		Button l1 = new Button(spr_larrow,new Sprite(spr_selector));
		Button r2 = new Button(spr_rarrow,new Sprite(spr_selector));
		Button l2 = new Button(spr_larrow,new Sprite(spr_selector));
		Button r3 = new Button(spr_rarrow,new Sprite(spr_selector));
		Button l3 = new Button(spr_larrow,new Sprite(spr_selector));
		
		//set their positions
		r1.SetPosition(900*Constants.ScreenScaleX(), 750*Constants.ScreenScaleY());
		l1.SetPosition(340*Constants.ScreenScaleX(), 750*Constants.ScreenScaleY());
		r2.SetPosition(900*Constants.ScreenScaleX(), 480*Constants.ScreenScaleY());
		l2.SetPosition(340*Constants.ScreenScaleX(), 480*Constants.ScreenScaleY());
		r3.SetPosition(900*Constants.ScreenScaleX(), 190*Constants.ScreenScaleY());
		l3.SetPosition(340*Constants.ScreenScaleX(), 190*Constants.ScreenScaleY());
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}