package space_studios.core;
//import list and arraylist
import java.util.ArrayList;
import java.util.List;











import space_studios.objects.BaseshipObject;
import space_studios.objects.BlockerShip;
import space_studios.objects.BlueBase;
import space_studios.objects.Constants;
import space_studios.objects.MusicPlayer;
import space_studios.objects.RedBase;
import space_studios.objects.RedBlockerShip;
import space_studios.objects.RedShooterShip;
import space_studios.objects.RedSuicideShip;
import space_studios.objects.ShipTypes;
import space_studios.objects.ShooterShip;
import space_studios.objects.SoundPlayer;
import space_studios.objects.Statistics;
import space_studios.objects.SuicideShip;


import space_studios.screens.Menu;
import space_studios.screens.ScreenManager;


//import libgdx game stuff
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import baseShip class
//import bases
//import enum shipTypes
//import blue ships
//import red ships
//import Music
//import Statistics

public class SpaceWarsCore extends Game {
		
		//spritebatch for drawing the sprites on
		public SpriteBatch batch;
		
		//font
		public BitmapFont font;
		
		//music
		private static MusicPlayer music;
		
		//sounds
		protected SoundPlayer sounds;
		
		public static boolean inSoloMode = false;
		
		// EPIC TIP: 0,0 is the lower left hand corner
		//@Override
		public void create () {
			//allows you to use constants
			Constants.setSize(Gdx.graphics.getDesktopDisplayMode().width,Gdx.graphics.getDesktopDisplayMode().height);
			//FULLSCREEN LINE, this enables fullscreen for your computer\\
			Gdx.graphics.setDisplayMode(Constants.display_width, Constants.display_height, true);
			
			//music\\
			music=new MusicPlayer();
			music.init();
			
			//sounds\\
			sounds=new SoundPlayer();
			sounds.init();
			
			//make font
			font = new BitmapFont();
			
			//sets font color
			font.setColor(Color.WHITE);
			
			batch=new SpriteBatch();
			if (ScreenManager.menu == null) {
				ScreenManager.menu = new Menu(this);
				this.setScreen(ScreenManager.menu);
			} else {
				this.setScreen(ScreenManager.menu);
			}
		}
		
		@Override
		public void dispose(){
			//disposes all the game textures and objects
			batch.dispose();
			music.dispose();
			sounds.dispose();
			font.dispose();
			super.dispose();
		}

		@Override
		public void render () {
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				dispose();
				System.exit(0);
			}
			super.render();
		}
		
		public void commonInit() {
			
		}
		
		public void commonRender() {
			
		}
}
