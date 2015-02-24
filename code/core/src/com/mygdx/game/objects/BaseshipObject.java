package com.mygdx.game.objects;

import java.util.ArrayList;
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
	protected final int ShotTime = 60; //Shot cooldown
	protected int Shot; // the cooldown counter. if it equals ShotTime, it shoots.
	protected List<BaseBullet> allBullets = new ArrayList<BaseBullet>(); //bulletList
	protected int Bullets;
	
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
		Shot = 0;
		Bullets = 0;
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
		
		if (Mask == null){
			Mask = new Rectangle (0.0f,0.0f,32.0f,16.0f);
		}
		
		int bulletLen = allBullets.size();
		BaseBullet bulletFront = null;
		if(bulletLen >= 1) {
			bulletFront = allBullets.get(bulletLen-1);
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
			// this checks if it is colliding and the ship is of a different color and if it is created
			if (otherShip.Blue != this.Blue && otherShip.Created == true){
				if (this.hits(otherShip.Mask)){
					colliding = true;
					collider = otherShip;
					this.takeDamage(collider.Damage,collider);
					if (this.getType() == ShipTypes.SuicideShip){
						Created = false;
					}
					break;
				}
				if(bulletFront != null && otherShip.hits(bulletFront.Mask) ) {
					otherShip.takeDamage(Damage,this);
					bulletFront.Created = false;
					break;
				}
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
		if (X<-64){
			Created=false;
		}
		if (X>1024){
			Created=false;
		}
		
		//-------Shooting------\\
		if (this.getType() == ShipTypes.ShooterShip){
			Shot+= (1);
			if (Shot >= ShotTime){
				Shot=0;
				Bullets++;
				BaseBullet b = new BaseBullet();
				b.create(X, Y, 2, Speed, Blue);
				allBullets.add(b);
			}
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
	
	public void takeDamage(int amount, BaseshipObject otherShip){
		Life-=amount;
		otherShip.Life -= amount;
		if (otherShip.Life<=0){
			otherShip.Created = false;
		}
		if (Life<=0){
			Created = false;
		}
	}
	
	public void draw(SpriteBatch batch){
		if (Created == false){
			return;
		}
		
		Sprite.draw(batch);
		int len = allBullets.size();
		for(int i = 0; i < len; i++) {
			BaseBullet b = allBullets.get(i);
			b.update();
			b.Sprite.draw(batch);
		}
		if(len >=1 && !allBullets.get(len-1).Created) {
			allBullets.remove(len-1);
		}
	}
	
	public Boolean create(float yPosition, float xPosition) {
		if (Created == false){
			Created = true;
			if (Blue) {
				this.setPlace(128, yPosition+48);
			} 
			if (!Blue){
				this.setPlace(736, yPosition+48);
			}
			if (this.getType() == ShipTypes.Bullet){
				this.setPlace(xPosition, yPosition);
			}
			return true;
		}
		else{
			return false;
		}
	}
}
