package com.mygdx.game.objects;


import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class RedBase {
	private Rectangle mask;
	private Sprite sprite;
	private Texture texture;
	public int Life = Constants.baseLife;
	private static BitmapFont font;
	//constructor
	public RedBase(){
	}
	
	public void Init(){
		//make font
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		//make mask, texture and sprite
		mask = new Rectangle (0.0f,0.0f,128.0f,128.0f);
		texture = new Texture(Gdx.files.internal("sprites/RedBase.png"));
		sprite = new Sprite(texture,0,0,128,128);
		this.setPlace(0, 0);
	}
	//returns colliding
	//returns if front is colliding
	public Boolean hits(Rectangle r){
		if (mask.overlaps(r)){
		return true;
		}
		else{
		return false;
		}
	}
	
	//action
	public void action(int type){
		
	}
	
	//update
	public void update(List<BaseshipObject> otherShips){
		for(int len = otherShips.size(), i = 0; i < len; i++) {
			BaseshipObject otherShip = otherShips.get(i);
			if (this.hits(otherShip.Mask) && otherShip.Blue == true && otherShip.Created){
				otherShip.Created = false;
				this.takeDamage(otherShip.Damage);
			}
			if (otherShip.bulletFront != null){
			
				if (this.hits(otherShip.bulletFront.Mask) && otherShip.Blue == true && otherShip.bulletFront.Created){
					otherShip.bulletFront.Created = false;
					this.takeDamage(1);
				}
			}
		}
	}
	//sets position of the object
	public void setPlace(float xPosition,float yPosition){
		mask.x = xPosition;
		mask.y = yPosition;
		sprite.setPosition(xPosition, yPosition);
	}
	//returns true if dead
	public Boolean takeDamage(int amount){
		Life-=amount;
		if (Life<=0){
			return true;
		}
		return false;
	}
	public void show(SpriteBatch batch){
		font.draw(batch, "Life: "+Life, 736, (178+160));
		sprite.draw(batch);
	}
	
}
