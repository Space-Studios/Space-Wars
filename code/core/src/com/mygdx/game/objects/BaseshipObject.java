package com.mygdx.game.objects;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BaseshipObject {
	
	protected Rectangle Mask;
	protected Sprite Sprite;
	protected Texture Texture;
	protected int Life;
	protected float X;
	protected float Y;
	protected int Speed;
	protected Boolean Created;
	protected int Damage;
	protected Boolean Blue;
	protected int ShotTime = 60; //Shot cooldown
	protected float Shot; // the cooldown counter. if it equals ShotTime, it shoots.
	
	//getters and setters for all variables
	public ShipTypes getType() {
		return ShipTypes.BaseshipObject; 
	}
	
	public Rectangle getMask() {
		return Mask;
	}

	public void setMask(Rectangle mask) {
		Mask = mask;
	}

	public Sprite getSprite() {
		return Sprite;
	}

	public void setSprite(Sprite sprite) {
		Sprite = sprite;
	}

	public Texture getTexture() {
		return Texture;
	}

	public void setTexture(Texture texture) {
		Texture = texture;
	}

	public int getLife() {
		return Life;
	}

	public void setLife(int life) {
		Life = life;
	}

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}

	public int getSpeed() {
		return Speed;
	}

	public void setSpeed(int speed) {
		Speed = speed;
	}

	public Boolean getCreated() {
		return Created;
	}

	public void setCreated(Boolean created) {
		Created = created;
	}

	public int getDamage() {
		return Damage;
	}

	public void setDamage(int damage) {
		Damage = damage;
	}

	public BaseshipObject() {
		Created = false;
		X = 0.0f;
		Y = 0.0f;
		Shot = 0.0f;
	}

	public void Init() {};
	
	public Boolean hits(Rectangle r) {
		Boolean rc = Mask.overlaps(r);
		return rc;
	}
	
	//action
	public void action(int type){
		
	}
	
	//update
	public void update(float delta, List<BaseshipObject> otherShips){
		//if not created, instantly exit
		if (Created == false){
			return;
		
		}
		//Goal: 30 fps
		delta /= 30;
		//this piece of code checks if it is colliding with a ship. that ship is called the collider
		Boolean colliding = false; //if it is colliding
		BaseshipObject collider = null; //what it is colliding with
		//this goes through all the ships and checks if they are colliding.
		for(int len = otherShips.size(), i = 0; i < len; i++) {
			//this sets the ship variable to the current one
			BaseshipObject otherShip = otherShips.get(i);
			// this checks if it is colliding and the ship is of a different color
			if (this.hits(otherShip.Mask) && otherShip.Blue != this.Blue){
				colliding = true;
				collider = otherShip;
				break;
			}
		}
		//--------Moving-----------\\
		//if not colliding, move this object
		if (colliding == false){
			if (Blue == true){
				this.setPlace(X + (Speed), Y);
			}
			else{
				this.setPlace(X - (Speed), Y);
			}
		}
		//if out of the play area
		if (X<0){
			Created=false;
		}
		if (X>1024){
			Created=false;
		}
		//--------Melee Damaging------\\
		if (collider!=null){
			this.takeDamage(collider.Damage);
			if (this.getType() == ShipTypes.SuicideShip){
				Created = false;
			}
		}
		
		//-------Shooting------\\
		Shot+= (1);
		if (Shot >= 60){
			Shot=0;
			//Code still to be added: making the bullet by the color of the ship.
		}
	}
	
	//sets position of the object
	public void setPlace(float xPosition,float yPosition){
		Mask.x = xPosition;
		Mask.y = yPosition;
		Sprite.setPosition(xPosition, yPosition);
		X = xPosition;
		Y = yPosition;
	}
	
	public void takeDamage(int amount){
		Life-=amount;
		if (Life<=0){
			Created = false;
		}
	}
	
	public void draw(SpriteBatch batch){
		if (Created == false){
			return;
		}
		
		Sprite.draw(batch);
	}
	
	public Boolean create(int yPosition) {
		if (Created == false){
			Created = true;
			if (Blue) {
				this.setPlace(128, yPosition+48);
			}
			else{
				this.setPlace(736, yPosition+48);
			}
			return true;
		}
		else{
			return false;
		}
	}
}
