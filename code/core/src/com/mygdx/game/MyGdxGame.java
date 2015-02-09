package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.BaseshipObject;
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
		//----TEXTURES AND SPRITES DEFINED----\\
		//suicide drone: I called it boomship because it booms
		private SuicideShip mboomship00 = new SuicideShip();
		private SuicideShip mboomship01 = new SuicideShip();
		private SuicideShip mboomship02 = new SuicideShip();
		private SuicideShip mboomship03 = new SuicideShip();
		private SuicideShip mboomship04 = new SuicideShip();
		private SuicideShip mboomship05 = new SuicideShip();
		private SuicideShip mboomship06 = new SuicideShip();
		private SuicideShip mboomship07 = new SuicideShip();
		private SuicideShip mboomship08 = new SuicideShip();
		private SuicideShip mboomship09 = new SuicideShip();
		//shooter ship: i called it shootship because it shoots
		private ShooterShip mshootship00 = new ShooterShip();
		private ShooterShip mshootship01 = new ShooterShip();
		private ShooterShip mshootship02 = new ShooterShip();
		private ShooterShip mshootship03 = new ShooterShip();
		private ShooterShip mshootship04 = new ShooterShip();
		//blocker ship: I called it brickship because it is a brick
		private BlockerShip mbrickship00 = new BlockerShip();
		private BlockerShip mbrickship01 = new BlockerShip();
		private BlockerShip mbrickship02 = new BlockerShip();
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
		//keypushes
		private Boolean Q = false;
		private Boolean W = false;
		private Boolean E = false;
		private Boolean num1 = false;
		private Boolean num2 = false;
		private Boolean num3 = false;
		private Boolean numpadnum1 = false;
		private Boolean numpadnum2 = false;
		private Boolean numpadnum3 = false;
		private Boolean numpadnu4 = false;
		private Boolean numpadnum5 = false;
		private Boolean numpadnum6 = false;
		
		
		// EPIC TIP: 0,0 is the lower left hand corner
		@Override
		public void create () {
			//call the Init function for all the class variables
			//suicide ships
			List<BaseshipObject> allShips = new ArrayList<BaseshipObject>();
			
			// Create all ships in the game
			for(int i = 0; i<10; i++) {
				allShips.add(new SuicideShip());
			}

			for(int i = 0; i<5; i++) {
				allShips.add(new ShooterShip());
			}
			
			for(int i = 0; i<10; i++) {
				allShips.add(new SuicideShip());
			}
			
			// init the ships
			for(int len = allShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allShips.get(i);
				ship.Init();
			}
			
			
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
		private static void updateShips(){
			
		}
}
