package space_studios.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Shipyard {
	private Sprite sprite;
	private Texture texture;
	private float X;
	private float Y;
	
	public void init(){
		texture = new Texture(Gdx.files.internal("assets/sprites/Ships & Bases/Red Sprites/Other/RedBase.png"));
		sprite = new Sprite(texture,0,0,128,128);
		sprite.setScale(Constants.ScreenScaleX());
		
	}
}
