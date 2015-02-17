package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.BaseshipObject;
import com.mygdx.game.objects.BlockerShip;
import com.mygdx.game.objects.BlueBase;
import com.mygdx.game.objects.RedBase;
import com.mygdx.game.objects.ShipTypes;
import com.mygdx.game.objects.ShooterShip;
import com.mygdx.game.objects.SuicideShip;

public class MyGdxGame extends ApplicationAdapter {
		
		//camera633
		private OrthographicCamera camera;
		//spritebatch for drawing the sprites on
		private SpriteBatch batch;
		//----TEXTURES AND SPRITES DEFINED----\\
		//base
		private RedBase mRedbase = new RedBase();
		private BlueBase mBluebase = new BlueBase();
		//background(hope you like it!!!)
		private Texture tex_space;
		private Sprite spr_space;
		//lanes
		private static int lane1 = 480-64;//y value
		private static int lane2 = 480; //y value
		private static int lane3 = 480+64;//y value
		private static int blueSelected;
		private static int redSelected;
		//keypushes
		private static Boolean Q = false;
		private static Boolean W = false;
		private static Boolean E = false;
		private static Boolean num1 = false;
		private static Boolean num2 = false;
		private static Boolean num3 = false;
		private static Boolean numpadnum1 = false;
		private static Boolean numpadnum2 = false;
		private static Boolean numpadnum3 = false;
		private static Boolean numpadnum4 = false;
		private static Boolean numpadnum5 = false;
		private static Boolean numpadnum6 = false;
		private List<BaseshipObject> allShips = new ArrayList<BaseshipObject>();
		
		
		// EPIC TIP: 0,0 is the lower left hand corner
		@Override
		public void create () {
			
			// Create all ships in the game
			for(int i = 0; i<10; i++) {
				allShips.add(new SuicideShip());
			}

			for(int i = 0; i<5; i++) {
				allShips.add(new ShooterShip());
			}
			
			for(int i = 0; i<3; i++) {
				allShips.add(new BlockerShip());
			}
			
			// init the ships
			for(int len = allShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allShips.get(i);
				ship.Init();
			}
			// init the bases
			mRedbase.Init();
			mBluebase.Init();
			
			//set camera
			camera=new OrthographicCamera();
			camera.setToOrtho(false,640,480);
			batch=new SpriteBatch();
	
			//background for the game
			tex_space = new Texture(Gdx.files.internal("sprites/SPACE!!!!!.png"));
			spr_space = new Sprite(tex_space,0,0,1024,480);
			
			//sets position for stationary things
			spr_space.setPosition(0, 0);
			//blue stuff
			mBluebase.setPlace(736, 178);
			//red stuff
			mRedbase.setPlace(128, 178);
			
		}
		@Override
		public void dispose(){
			//disposes all the game textures and objects
			batch.dispose();
			tex_space.dispose();
		}

		@Override
		public void render () {
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			

			///drawing. The ones drawn first are behind the others
			batch.begin();
			//reset the color, just in case
			batch.setColor(0, 0, 0, 1);
			//draw the background
			spr_space.draw(batch);
			//draw the bases
			mRedbase.show(batch); 
			mBluebase.show(batch);
			//draw the ships
			drawShips(allShips,batch);
			//end the drawing
			batch.end();
			
			//Updates
			updateShips(allShips);
			//Controls
			updateKeys();
			//get right selected variable
			getLane();
			//----create ships----\\
			//suicide ship
			if (Q==true){
				for(int len = allShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allShips.get(i);
					if (ship.getType().equals(ShipTypes.SuicideShip)){
						if (ship.create(blueSelected)==true){
							break;
						}
					}
				}
			}
			//shooter ship
			if (W==true){
				for(int len = allShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allShips.get(i);
					if (ship.getType().equals(ShipTypes.ShooterShip)){
						if (ship.create(blueSelected)==true){
							break;
						}
					}
				}
			}
			//blocker ship
			if (E==true){
				for(int len = allShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allShips.get(i);
					if (ship.getType().equals(ShipTypes.BlockerShip)){
						if (ship.create(blueSelected)==true){
							break;
						}
					}
				}
			}
			
		}
		
		
		
		
		//-------FUNCTIONS-------\\
		private static void updateShips(List<BaseshipObject> allShips){
			for(int len = allShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allShips.get(i);
				ship.update(Gdx.graphics.getRawDeltaTime(), allShips);
			}
		}
		private static void drawShips(List<BaseshipObject> allShips, SpriteBatch batch){
			for(int len = allShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allShips.get(i);
				ship.draw(batch);
			}
		}
		private static void getLane(){
			if (num1){
				blueSelected = lane1;
			}
			if (num2){
				blueSelected = lane2;
			}
			if (num3){
				blueSelected = lane3;
			}
			if (numpadnum4){
				redSelected = lane1;
			}
			if (numpadnum5){
				redSelected = lane2;
			}
			if (numpadnum6){
				redSelected = lane3;
			}
		}
		private static void updateKeys(){
			//updates all keys 
			if(Gdx.input.isKeyPressed(Input.Keys.Q)){
				 Q = true;
			 }
			 else{
				 Q = false;
			 }
			
			 if(Gdx.input.isKeyPressed(Input.Keys.Q)){
				 Q = true;
			 }
			 else{
				 Q = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.W)){
				 W = true;
			 }
			 else{
				 W = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.E)){
				 E = true;
			 }
			 else{
				 E = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
				 num1 = true;
			 }
			 else{
				 num1 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
				 num2 = true;
			 }
			 else{
				 num2 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
				 num3 = true;
			 }
			 else{
				 num3 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
				 numpadnum1 = true;
			 }
			 
			 else{
				 numpadnum1 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)){
				 numpadnum2 = true;
			 }
			 else{
				 numpadnum2 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)){
				 numpadnum3 = true;
			 }
			 else{
				 numpadnum3 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)){
				 numpadnum4 = true;
			 }
			 else{
				 numpadnum4 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5)){
				 numpadnum5 = true;
			 }
			 else{
				 numpadnum5 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)){
				 numpadnum6 = true;
			 }
			 else{
				 numpadnum6 = false;
			 }
		}
}
