package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.objects.BaseshipObject;

public class BlockerShip extends BaseshipObject {

	public ShipTypes getType() {
		return ShipTypes.BlockerShip; 
	}
	
	public BlockerShip() {
		super();
	}
	
	public void Init() {
		Life = 6;
		Damage = 1;
		Mask = new Rectangle (0.0f,0.0f,128.0f,64.0f);
		Texture = new Texture(Gdx.files.internal("sprites/Basic Blocker.png"));
		Sprite = new Sprite(Texture,0,0,128,64);
		this.setPlace(0, 0);
		Blue=true; 
		Speed=1;
	}
}