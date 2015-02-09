package com.mygdx.game.objects;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BlockerShip {
	private Rectangle mask;
	private Sprite sprite;
	private Texture texture;
	private int life;
	private float x;
	private float y;
	private final int speed = 2;
	private Boolean created;
	
	//constructor
	public BlockerShip() {
	}
	
	public void Init() {
		life = 6;
		mask = new Rectangle (0.0f,0.0f,128.0f,64.0f);
		texture = new Texture(Gdx.files.internal("sprites/Basic Blocker.png"));
		sprite = new Sprite(texture,0,0,128,64);
		this.setPlace(0, 0);
		x = 0.0f;
		y = 0.0f;
		created = false;
	}
	
	//returns if colliding
	public int hits(Rectangle r){
		if (mask.overlaps(r)){
			return 1;
		} else{
			return -1;
		}
	}
	
	//action
	public void action(int type){
		
	}
	//update
	public void update(float delta){
		if (created == true){
			//if not colliding
			if (this.hits(mask) == -1){
				//move this object
				this.setPlace(x+speed, y);
			}
			else{
				System.out.print("Suicidal collision between objects");
			}
		}

	}
	//sets position of the object
	public void setPlace(float xpo,float ypo){
		mask.x = xpo;
		mask.y = ypo;
		sprite.setPosition(xpo, ypo);
		x = xpo;
		y = ypo;
	}
	
	public void takeDamage(int amount){
		
	}
	
	public void draw(SpriteBatch batch){
		if (created == true){
			sprite.draw(batch);
		}
	}
	
	public void create(int ypo){
		created = true;
		this.setPlace(736, ypo);
	}
	
}
