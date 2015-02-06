package com.mygdx.game.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BlueBase {
	private Rectangle mask;
	private Sprite sprite;
	private Texture texture;
	private int action;
	private int xVel;
	private int life = 20;
	
	//constructor
	public BlueBase() {
		
	}
	public void Init(){
		mask = new Rectangle (0.0f,0.0f,128.0f,128.0f);
		texture = new Texture(Gdx.files.internal("sprites/BlueBase.png"));
		sprite = new Sprite(texture,0,0,128,128);
		this.setPlace(0, 0);
	}
	
	//returns if front is colliding
	public int hits(Rectangle r){
		if (mask.overlaps(r)){
		return 1;
		}
		else{
		return -1;
		}
	}
	
	//action
	public void action(int type){
		
	}
	
	//update
	public void update(float delta){
		
	}
	
	//sets position of the object
	public void setPlace(float x,float y){
		mask.x = x;
		mask.y = y;
		sprite.setPosition(x, y);
	}
	
	public void takeDamage(int amount){
		
	}
	
	public void show(SpriteBatch batch){
		sprite.draw(batch);
	}
	
}
