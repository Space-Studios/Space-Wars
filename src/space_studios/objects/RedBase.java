package space_studios.objects;


import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class RedBase {
	public Rectangle mask;
	private Sprite sprite;
	private Texture texture;
	private float X;
	private float Y;
	public int Life = Constants.baseLife;
	private static BitmapFont font;
	private static Boom boom;
	private SoundPlayer sounds;
	
	//constructor
	public RedBase(){
		sounds = new SoundPlayer();
	}
	
	public void Init(){
		//make font
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		//make mask, texture and sprite
		mask = new Rectangle (0.0f,0.0f,128.0f,128.0f);
		texture = new Texture(Gdx.files.internal("assets/sprites/Ships & Bases/Red Sprites/Other/RedBase.png"));
		sprite = new Sprite(texture,0,0,128,128);
		this.setPlace(0, 0);
		sprite.scale(0.8f);
		sounds.init();
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
				otherShip.boom.add(new Boom(otherShip.X,otherShip.Y,Constants.boomSize));
				if (!otherShip.boom.isEmpty()){
					for (int i1=0;i1<otherShip.boom.size();i1++){
						otherShip.boom.get(i1).Init();
					}
				}
			}
			if (otherShip.bulletFront != null){
			
				if (this.hits(otherShip.bulletFront.Mask) && otherShip.Blue == true && otherShip.bulletFront.Created){
					otherShip.bulletFront.Created = false;
					takeDamage(otherShip.Damage);
				}
			}
		}
		
		if (Life<=0 && boom==null){
			boom=new Boom(X+280,Y-250,Constants.boomSize+10);
			boom.Init();
		}
		if (boom!=null){
			boom.update();
		}
	}
	//sets position of the object
	public void setPlace(float xPosition,float yPosition){
		mask.x = xPosition;
		mask.y = yPosition;
		sprite.setPosition(xPosition, yPosition);
		X=xPosition;
		Y=yPosition;
	}
	//returns true if dead
	public Boolean takeDamage(int amount){
		this.sounds.playBoom();
		Life-=amount;
		if (Life<=0){
			return true;
		}
		return false;
	}
	
	public Boolean isDead(){
		if (Life<=0){
			return true;
		}
		return false;
	}
	
	public void show(SpriteBatch batch){
		if (boom!=null){
			boom.show(batch);
		}
		if (Life<=0){
			this.setPlace(0, 0);
			return;
		}
		font.draw(batch, "Life: "+Life, X, Y+128+64);
		sprite.draw(batch);
	}
	
}
