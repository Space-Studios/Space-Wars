package space_studios.objects;

import java.util.ArrayList;
import java.util.List;

import space_studios.core.SpaceWarsCore;
import space_studios.objects.Statistics;

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
	protected final int ShotTime = Constants.shotCool; //Shot cooldown
	protected int Shot; // the cooldown counter. if it equals ShotTime, it shoots.
	protected List<BaseBullet> allBullets = new ArrayList<BaseBullet>(); //bulletList
	protected List<Boom> boom = new ArrayList<Boom>();
	protected int Bullets;
	protected BaseBullet bulletFront;
	protected SoundPlayer sounds;
	
	//getters and setters for all variables
	public ShipTypes getType() {
		return ShipTypes.BaseshipObject; 
	}
	
	public BaseshipObject() {
		Created = false;
		X = 0.0f;
		Y = 0.0f;
		Shot = 0;
		Bullets = 0;
		sounds = new SoundPlayer();
		this.sounds.init();
	}
	
	public void Init() {};
	
	public void set() {}
	
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
		bulletFront = null;
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
					otherShip.takeBulletDamage(Damage);
					bulletFront.Created = false;
					boom.add(new Boom(bulletFront.X,bulletFront.Y,Constants.boomSize));
					if (!boom.isEmpty()){
						for (int d=0;d<boom.size();d++){
							boom.get(d).Init();
						}
					}
					bulletFront.setPlace(0, 0);
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
		if (X>1024*2){
			Created=false;
		}
		
		//-------Shooting------\\
		if (this.getType() == ShipTypes.ShooterShip){
			Shot+= (1);
			if (Shot >= ShotTime){
				this.sounds.playShoot();
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
			otherShip.boom.add(new Boom(otherShip.X,otherShip.Y,Constants.boomSize));
			if (!otherShip.boom.isEmpty()){
				for (int i=0;i<otherShip.boom.size();i++){
					otherShip.boom.get(i).Init();
				}
			if (this.Blue) {
				Statistics.redKills += 1;
				if (SpaceWarsCore.inSoloMode) {
					SpaceWarsCore.setLaneQuant((int)Y, false);
				}
			}
			if (otherShip.Blue) {
				Statistics.blueKills += 1;
				}
			}
			otherShip.Created = false;
			otherShip.setPlace(0, 0);
		}
		if (Life<=0){
			boom.add(new Boom(X,Y,Constants.boomSize));
			if (!boom.isEmpty()){
				for (int i=0;i<boom.size();i++){
					boom.get(i).Init();
				}
			}
			this.sounds.playBoom();
			Created = false;
			this.setPlace(0, 0);
		}
	}
	public void takeBulletDamage(int amount){
		Life-=amount;
		if (Life<=0){
			boom.add(new Boom(X,Y,Constants.boomSize));
			if (!boom.isEmpty()){
				for (int i=0;i<boom.size();i++){
					boom.get(i).Init();
				}
			}
			this.sounds.playBoom();
			Created = false;
		}
	}
	public void bulletTest(RedBase red, BlueBase blue){
		//if not shooter ship or if bulletFront is null
		if (this.getType()!=ShipTypes.ShooterShip || bulletFront == null){
			return;
		}
		
		//if bullet hits red base
		if (bulletFront.hits(red.mask)){
			this.sounds.playBoom();
			red.Life-=this.Damage;
			bulletFront.Created = false;
			boom.add(new Boom(bulletFront.X,bulletFront.Y,Constants.boomSize));
			if (!boom.isEmpty()){
				for (int d=0;d<boom.size();d++){
					boom.get(d).Init();
				}
			}
		}
		
		//if bullet hits blue base
		if (bulletFront.hits(blue.mask)){
			this.sounds.playBoom();
			blue.Life-=this.Damage;
			bulletFront.Created = false;
			boom.add(new Boom(bulletFront.X,bulletFront.Y,Constants.boomSize));
			if (!boom.isEmpty()){
				for (int d=0;d<boom.size();d++){
					boom.get(d).Init();
				}
			}
		}
	}
	
	public void draw(SpriteBatch batch){
		//update DA BOOOMZ
		if (!boom.isEmpty()){
			for (int i=0;i<boom.size();i++){
				boom.get(i).update();
				boom.get(i).show(batch);
				boom.get(i);
				if (boom.get(i).Show == false){
					boom.remove(i);
				}
			}
		}
		
		if (Created == false){
			return;
		}
		
		Sprite.draw(batch);
		int len = allBullets.size();
		for(int i = 0; i < len; i++) {
			BaseBullet b = allBullets.get(i);
			b.update();
			if (b.Created){
				b.Sprite.draw(batch);
			}
		}
		if(len >=1 && !allBullets.get(len-1).Created) {
			allBullets.remove(len-1);
		}
	}
	
	public Boolean create(float yPosition, float xPosition) {
		if (Created == false){
			sounds.init();
			Created = true;
			Shot = 0;
			if (Blue) {
				this.setPlace((Constants.display_width/4)+70, yPosition);
			} 
			if (!Blue){
				this.setPlace(((Constants.display_width/4)*3)+70, yPosition);
			}
			if (this.getType() == ShipTypes.Bullet){
				this.setPlace(xPosition, yPosition);
			}
			else{
				this.sounds.playShipLaunch();
			}
			
			this.set();
			return true;
		}
		else{
			return false;
		}
	}
}
