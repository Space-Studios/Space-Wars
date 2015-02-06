package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.BlockerShip;
import com.mygdx.game.objects.BlueBase;
import com.mygdx.game.objects.RedBase;
import com.mygdx.game.objects.ShooterShip;
import com.mygdx.game.objects.SuicideShip;

public class MyGdxGame extends ApplicationAdapter {
		//camera633
		private OrthographicCamera camera;
		//spritebatch for drawing the sprites on
		private SpriteBatch batch;
		//----TEXTURES AND SPRITES DEFINED (ALL ARE MADE BY LEON LEIBMANN)----\\
		//suicide drone: I called it boomship because it booms
		private SuicideShip mboomship00 = new SuicideShip();
		//shooter ship: i called it shootship because it shoots
		private ShooterShip mshootship00 = new ShooterShip();
		//blocker ship: I called it brickship because it is a brick
		private BlockerShip mbrickship00 = new BlockerShip();
		//base
		private RedBase mRedbase = new RedBase();
		private BlueBase mBluebase = new BlueBase();
		//background(hope you like it!!!)
		private Texture tex_space;
		private Sprite spr_space;
		//lanes
		private int lane1 = 480-32;//y value
		private int lane2 = 480; //y value
		private int lane3 = 480+32;//y value
		//Make Ship Variable:
		
		
		
		
		
		// EPIC TIP: 0,0 is the lower left hand corner
		@Override
		public void create () {
			//call the Init function for all the class variables
			mboomship00.Init();
			mshootship00.Init();
			mbrickship00.Init();
			mRedbase.Init();
			mBluebase.Init();
			
			camera=new OrthographicCamera();
			camera.setToOrtho(false,640,480);
			batch=new SpriteBatch();
	
			//background for the game
			tex_space = new Texture(Gdx.files.internal("sprites/SPACE!!!!!.png"));
			spr_space = new Sprite(tex_space,0,0,1024,480);
			//sets position for stationary things
			spr_space.setPosition(0, 0);
			//-----Location Work------\\
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
			
			//batch things
			
			//put sprites you want to draw in here, the ones drawn first are behind the others
			batch.begin();
			//reset the color, just in case
			batch.setColor(0, 0, 0, 1);
			//draw the background
			spr_space.draw(batch);
			//draw the bases
			mRedbase.show(batch); 
			mBluebase.show(batch);
			batch.end();
			
			//Updates
			
			//Controls
		}
}
