package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.objects.BaseshipObject;

public class ShooterShip extends BaseshipObject {

	public ShipTypes getType() {
		return ShipTypes.ShooterShip; 
	}
	
	public ShooterShip() {
		super();
	}
	
	public void Init() {
		Life = Constants.shShipLife;
		Damage = Constants.shShipDam;
		Mask = new Rectangle (0.0f,0.0f,64.0f,32.0f);
		Texture = new Texture(Gdx.files.internal("sprites/Basic Shooter Ship.png"));
		Sprite = new Sprite(Texture,0,0,64,32);
		Blue=true; 
		Speed=2;
		this.setPlace(1024, 480);
	}
	public void set() {
		Life = Constants.shShipLife;
	}
}