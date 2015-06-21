package space_studios.objects;


import space_studios.objects.BaseshipObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class RedSuicideShip extends BaseshipObject {

	public ShipTypes getType() {
		return ShipTypes.SuicideShip; 
	}
	
	public RedSuicideShip() {
		super();
	}
	
	public void Init() {
		Life = Constants.sShipLife;
		Damage = Constants.sShipDam;
		Mask = new Rectangle (0.0f,0.0f,64.0f,32.0f);
		Texture = new Texture(Gdx.files.internal("assets/sprites/Ships & Bases/Red Sprites/Ships/Red Basic Suicide Drone.png"));
		Sprite = new Sprite(Texture,0,0,64,32);
		Blue=false; 
		Speed=4;
		this.setPlace(0, 0);
	}
	public void set() {
		Life = Constants.sShipLife;
	}
}