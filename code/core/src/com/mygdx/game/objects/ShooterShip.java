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
		Life = 3;
		Damage = 1;
		Mask = new Rectangle (0.0f,0.0f,64.0f,32.0f);
		Texture = new Texture(Gdx.files.internal("sprites/Basic Shooter Ship.png"));
		Sprite = new Sprite(Texture,0,0,128,64);
		this.setPlace(0, 0);
		Blue=true; 
		Speed=2;
	}
}