package space_studios.objects;


import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BlueBase {
	public Rectangle mask;
	private Sprite sprite;
	private Sprite yard;
	private Texture texture;
	private Texture ships;
	public int Life = Constants.baseLife;
	private float X;
	private float Y;
	private static BitmapFont font;
	private static Boom boom;
	private SoundPlayer sounds = new SoundPlayer();
	
	//constructor
	public BlueBase() {
	}
	public void Init(){
		//make font
		//font = new BitmapFont();
		//font.setColor(Color.WHITE);
		//font.getData().setScale(Constants.FontScale());
		font = Constants.font;
		mask = new Rectangle (0.0f,0.0f,128.0f*Constants.ScreenScaleX(),128.0f*Constants.ScreenScaleY());
		texture = new Texture(Gdx.files.internal("assets/sprites/Ships & Bases/Blue Sprites/Other/BlueBase.png"));
		ships = new Texture(Gdx.files.internal("assets/sprites/Ships & Bases/Shipyards/P2 Shipyard.png"));
		sprite = new Sprite(texture,0,0,128,128);
		yard = new Sprite (ships,0,0,128,64);
		this.setPlace(0, 0);
		sprite.scale(0.8f*Constants.ScreenScaleX());
		yard.scale(0.8f*Constants.ScreenScaleX());
		X=0;
		Y=0;
		sounds.init();
	}
	
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
			if (this.hits(otherShip.Mask) && otherShip.Blue == false && otherShip.Created){
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
				
				if (this.hits(otherShip.bulletFront.Mask) && otherShip.Blue == false && otherShip.bulletFront.Created){
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
		X = xPosition;
		Y = yPosition;
		yard.setPosition(xPosition, yPosition-((128+32)*Constants.ScreenScaleY()));
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
			this.setPlace(99999, 99999);
			return;
		}
		font.draw(batch, "Life: "+Life, X-16*Constants.ScreenScaleX(), Y+110*Constants.ScreenScaleX());
		sprite.draw(batch);
		yard.draw(batch);

	}
	
}
