package space_studios.objects;

import space_studios.objects.BaseshipObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class RedBlockerShip extends BaseshipObject {

	public ShipTypes getType() {
		return ShipTypes.BlockerShip; 
	}
	
	public RedBlockerShip() {
		super();
	}
	
	public void Init() {
		Life = Constants.bShipLife;
		Damage = Constants.bShipDam;
		Mask = new Rectangle (0.0f,0.0f,64.0f,32.0f);
		Texture = new Texture(Gdx.files.internal("assets/sprites/Ships & Bases/Red Sprites/Ships/Red Basic Blocker.png"));
		Sprite = new Sprite(Texture,0,0,64,32);
		Blue=false; 
		Speed=1;
		this.setPlace(0, 0);
	}
	public void set() {
		Life = Constants.bShipLife;
	}
}