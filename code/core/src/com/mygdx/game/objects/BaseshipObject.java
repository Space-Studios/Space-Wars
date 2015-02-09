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
	
	//getters and setters for all variables
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
	}

	public void Init() {};
	
	public int hits(Rectangle r) {
		if (Mask.overlaps(r)) {
			return 1;
		} 
		else {
			return -1;
		}
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
		//this piece of code checks if it is colliding with a ship. that ship is called the collider
		Boolean colliding = false; //if it is colliding
		BaseshipObject collider = null; //what it is colliding with
		//this goes through all the ships and checks if they are colliding.
		for(int len = otherShips.size(), i = 0; i < len; i++) {
			//this sets the ship variable to the current one
			BaseshipObject otherShip = otherShips.get(i);
			// this checks if it is colliding and the ship is of a different color
			if (this.hits(otherShip.Mask) == -1 && otherShip.Blue!=this.Blue){
				colliding = true;
				collider = otherShip;
				break;
			}
		}
		//--------Moving-----------\\
		//if not colliding, move this object
		if (colliding == false){
			if (Blue == true){
				this.setPlace(X + Speed, Y);
			}
			else{
				this.setPlace(X - Speed, Y);
			}
		}
		//--------Melee Damaging------\\
		if (collider!=null){
			this.takeDamage(collider.Damage);
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
	
	public void create(int yPosition) {
		Created = true;
		this.setPlace(736-128, yPosition);
	}
}
