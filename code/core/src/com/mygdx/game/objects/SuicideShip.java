package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SuicideShip extends BaseshipObject {

	public SuicideShip() {
		super();
	}
	
	public void Init() {
		Life = 2;
		Damage = 3;
		Mask = new Rectangle (0.0f,0.0f,128.0f,64.0f);
		Texture = new Texture(Gdx.files.internal("sprites/Basic Suicide Drone.png"));
		Sprite = new Sprite(Texture,0,0,128,64);
		this.setPlace(0, 0);
	}
}